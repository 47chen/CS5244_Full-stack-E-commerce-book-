package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.category.Category;
import business.category.CategoryDao;
import business.customer.CustomerDao;
import business.customer.CustomerForm;
import business.customer.Customer;
import business.order.OrderDetails;
import business.order.LineItem;
import business.order.LineItemDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

//order here is an interface
//DefaultOrderService here is an implementation


public class DefaultOrderService implements OrderService {

    private BookDao bookDao;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private LineItemDao lineItemDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        // NOTE: THIS METHOD PROVIDED NEXT PROJECT
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.getCustomerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

        validateCustomer(customerForm);
        validateCart(cart);

        try (Connection connection = JdbcUtils.getConnection()) {
            Date date = getDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    date, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        }
    }

    private Date getDate(String monthString, String yearString) {
        int month = Calendar.JANUARY;
        switch (Integer.parseInt(monthString)) {
            case 2:
                month = Calendar.FEBRUARY;
                break;
            case 3:
                month = Calendar.MARCH;
                break;
            case 4:
                month = Calendar.APRIL;
                break;
            case 5:
                month = Calendar.MAY;
                break;
            case 6:
                month = Calendar.JUNE;
                break;
            case 7:
                month = Calendar.JULY;
                break;
            case 8:
                month = Calendar.AUGUST;
                break;
            case 9:
                month = Calendar.SEPTEMBER;
                break;
            case 10:
                month = Calendar.OCTOBER;
                break;
            case 11:
                month = Calendar.NOVEMBER;
                break;
            case 12:
                month = Calendar.DECEMBER;
                break;
        }
        int year = Integer.parseInt(yearString);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        return calendar.getTime();

    } //TODO: Implement this correctly

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);

            // create row in the customer table and put information in
            // that long integer is the primary key for customer table, and we want to use them in order table
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);

            // customer_order_line_item -> primary key is a jointed table
            // since it doesn't create a primary key
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection, customerOrderId,
                        item.getBookId(), item.getQuantity());
            }
            connection.commit();
            // if there is exception, then won't come to connection.commit()
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback(); // why rolling back even not commit yet? -> problem happens -> rollback
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    private int generateConfirmationNumber() {
        Random random = new Random();
        return random.nextInt(999999999);
    }


    private void validateCustomer(CustomerForm customerForm) {


        if (!nameIsValid(customerForm.getName())) {
            throw new ApiException.InvalidParameter("Invalid name field");
        }

        if (!phoneIsValid(customerForm.getPhone())) {
            throw new ApiException.InvalidParameter("Invalid phone field");
        }

        if (!addressIsValid(customerForm.getAddress())) {
            throw new ApiException.InvalidParameter("Invalid address field");
        }

        if (!emailIsValid(customerForm.getEmail())) {
            throw new ApiException.InvalidParameter("Invalid email field");
        }

        if (!ccNumberIsValid(customerForm.getCcNumber())) {
            throw new ApiException.InvalidParameter("Invalid creat card number");
        }


        if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
            throw new ApiException.InvalidParameter("Invalid expiry date");
        }
    }

    private boolean ccNumberIsValid(String ccNumber) {
        ccNumber = ccNumber.replaceAll(" ", "");
        ccNumber = ccNumber.replaceAll("-", "");
        if (ccNumber == null) return false;
        if (ccNumber.equals("")) return false;
        if (!(ccNumber.length() <= 16)) return false;
        if (!(ccNumber.length() >= 14)) return false;
        return true;
    }


    private boolean addressIsValid(String address) {
        if (address == null) return false;
        if (address.equals("")) return false;
        if (address.length() < 4) return false;
        if (address.length() > 45) return false;
        return true;
    }

    private boolean nameIsValid(String name) {
        if (name == null) return false;
        if (name.equals("")) return false;
        if (name.length() < 4) return false;
        if (name.length() > 45) return false;

//		return name == null || name.equals("") || name.length() > 45;
        return true;
    }

    // 4 kinds of invalid message -> invalid \ valid \ empty \ missing value

    private boolean emailIsValid(String email) {
        if (email == null) return false;
        if (email.equals("")) return false;
        if (email.contains(" ")) return false;
        if (!email.contains("@")) return false;

//		if(email.substring(email.length()-1) == "\\.") return false; //can't work and don't know why
        if (email.endsWith(".")) return false;
        return true;
    }

    private boolean phoneIsValid(String phone) {
        if (phone == null) return false;
        if (phone.equals("")) return false;
        //valid phone number shows below
        //(703)555-1212
        //703-555-1212
        //703 555 1212
        //7035551212
        //get rid of parens, spaces, and dashes
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("-", "");
        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        //should have a number (no letters) with exactly 10 digits
        if (!phone.matches("[\\d]+")) return false;
        if (phone.length() != 10) return false;
        return true;
    }


    private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {

//		if(ym.isBefore(YearMonth.parse(ccExpiryMonth))) return true;
//		if(ym.isBefore(YearMonth.parse(ccExpiryYear))) return true;

//		if(YearMonth.now().isBefore(YearMonth.parse(ccExpiryMonth))) return true;
//		if(YearMonth.now().isBefore(YearMonth.parse(ccExpiryYear))) return true;

//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM");
//		String dateString = ccExpiryYear + "-" + ccExpiryMonth;
//		YearMonth date = YearMonth.parse(dateString, dateTimeFormatter);

//		int ccExpiryMonthInt = Integer.parseInt(ccExpiryMonth);
//		int ccExpiryYearInt = Integer.parseInt(ccExpiryYear);

//		String s = ym.format(DateTimeFormatter.ofPattern("MM yyyy"));
//		if(Integer.parseInt(ccExpiryMonth) < Integer.parseInt(s)) return true;
//		if(Integer.parseInt(ccExpiryYear) < Integer.parseInt(s)) return true;

        YearMonth ymCC = YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth));

        if (!YearMonth.now().equals(ymCC) && !YearMonth.now().isBefore(ymCC)) return true;
        //equals(ymCC) -> curTime = ccM + ccY
        else if (Integer.parseInt(ccExpiryMonth)>12 && Integer.parseInt(ccExpiryMonth)<1) return false;
        else if (ccExpiryYear == null || ccExpiryMonth == null) return false;
        else if (ccExpiryYear == "" || ccExpiryMonth == "") return false;

        return false;

    }

    private void validateCart(ShoppingCart cart) {

        if (cart.getItems().size() <= 0) {
            throw new ApiException.InvalidParameter("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            if (item.getQuantity() < 0 || item.getQuantity() > 99) {
                throw new ApiException.InvalidParameter("Invalid quantity");
            }

            Book databaseBook = bookDao.findByBookId(item.getBookId());

            if (item.getBookForm().getPrice() != databaseBook.getPrice()) {
                throw new ApiException.InvalidParameter("Invalid price matching");
            }
            if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
                throw new ApiException.InvalidParameter("Invalid CategoryId matching");
            }
        });


    }

}
