
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.order.*;

import java.sql.Connection;
import java.util.List;

public class ApplicationContext {


    private BookDao bookDao;
    private CategoryDao categoryDao;
    private LineItemDao lineItemDao;
    private CustomerDao customerDao;
    private OrderDao orderDao;

    private final OrderService orderService;


    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {

        categoryDao = new CategoryDaoJdbc();
        bookDao = new BookDaoJdbc();
        lineItemDao = new LineItemDaoJdbc();
        customerDao = new CustomerDaoJdbc();
        orderDao = new OrderDaoJdbc();


        orderService = new DefaultOrderService();
        ((DefaultOrderService)orderService).setBookDao(bookDao);
        ((DefaultOrderService)orderService).setCustomerDao(customerDao);
        ((DefaultOrderService)orderService).setOrderDao(orderDao);
        ((DefaultOrderService)orderService).setLineItemDao(lineItemDao);

    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public BookDao getBookDao() { return bookDao; }

    public LineItemDao getLineItemDao() { return  lineItemDao; }
    public CustomerDao getCustomerDao() { return customerDao; }
    public OrderDao getOrderDao () { return orderDao; }

    public OrderService getOrderService (){
        return orderService;
    }
}
