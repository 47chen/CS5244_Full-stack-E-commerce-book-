<template>
  <main>
    <section class="mainBackgroundImage">
      <div class="filter">
        <div id="confirmationView">
          <section
          v-if="$store.state.orderDetails === null"
          class="empty-confirmation-page"
          >
            <h3>You don't have any order so far, ready to shop?</h3>
            <router-link :to="'/category/Travel'">
              <button class="button-shop-now">GO SHOP!</button>
            </router-link>
          </section>
          <section v-else>
          <ul>
            <h2>Order Detail</h2>
            <li><p>#Comfirmation Number :</p> {{ orderDetails.order.confirmationNumber }}</li>
            <li><p>#Order on</p>{{ new Date(orderDetails.order.dateCreated) }}</li>
          </ul>
          <ul>
            <h2>Customer Information</h2>
            <li><p>Your Name:</p>{{ $store.state.orderDetails.customer.customerName }}</li>
            <li><p>Shipping Address:</p>{{ $store.state.orderDetails.customer.address }}</li>
            <li><p>Your Email:</p>{{$store.state.orderDetails.customer.email}}</li>
            <li><p>Your Phone:</p>{{ phoneFilter(orderDetails.customer.phone) }}</li>
            <li><p>Your Credit Card Number:</p>{{ creditCardFilter(orderDetails.customer.ccNumber, orderDetails.customer.ccExpDate) }}</li>
          </ul>
          <confirmation-table></confirmation-table>
          <div class="checkout-description">
          <p class="checkout-description total">
            Your credit card will be charged
            <Price :amount="orderDetails.order.amount"></Price>
          </p>
            </div>
          <div class="checkout-description">
            <p class="checkout-description sub">
              (<Price :amount="orderDetails.order.amount - 500"></Price> subtotal +
              <Price :amount="cart.surcharge"></Price> shipping)
            </p>
          </div>
          </section>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import {mapState, mapGetters} from "vuex"
import ConfirmationTable from "@/components/ConfirmationTable";
import Price from "@/components/Price";
export default {
  name: "Confirmation",
  components: {ConfirmationTable, Price},

  computed: {
    ...mapState(["orderDetails", "cart", "selectedCategoryName"]),
    ...mapGetters(["categoryName"])
  },

  methods: {
    creditCardFilter: function (ccNumber, ccExpDate) {
      ccNumber = ccNumber.toString();
      ccExpDate = new Date(ccExpDate);
      let date = `/ Expiry Date: ${ccExpDate.getMonth() < 9 ? "0" : ""}${
          ccExpDate.getMonth() + 1
      }-${ccExpDate.getFullYear()}`;
      ccNumber = ccNumber.replaceAll(" ", "");
      ccNumber = ccNumber.replaceAll("-", "");
      if (ccNumber.length === 14) {
        return `**** ****** ${ccNumber.substring(10, 14)} ${date}`;
      } else if (ccNumber.length === 15) {
        return `**** ****** ${ccNumber.substring(10, 15)} ${date}`;
      } else if (ccNumber.length === 16) {
        return `**** **** **** ${ccNumber.substring(12, 16)} ${date}`;
      }
    },

    phoneFilter: function (phone) {
      phone = phone.toString();
      phone = phone.replaceAll(" ", "");
      phone = phone.replaceAll("-", "");
      phone = phone.replaceAll("\\(", "");
      phone = phone.replaceAll("\\)", "");
      let phoneNumber = `(${phone.substring(0, 3)})-${phone.substring(3, 6)}-${phone.substring(6, 10)}`;
      return phoneNumber
    }
  },
};

</script>
<style scoped>
main section.mainBackgroundImage {
  height: auto;
  min-height: 50vw;
  width: 100%;
  background-image: url("../assets/images/site/ＷelcomePage_BackgroundImage-2.png");
  background-size: cover;
  background-position: center;
  position: relative;
  z-index: 1;
  opacity: 1;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  align-items: center;
  flex-direction: column;
  text-align: center;

  justify-content: space-between;
}

main div.filter {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(238, 236, 236, 0.4);
  width: 100%;
  height: auto;
  min-height: 50vw;

  top: 0;
  z-index: -1;
}


#confirmationView {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 1em auto;
}

ul {
  text-align: left;
}

ul > li {
  margin: 0.25em;
}


li {
  display: flex;
  font-size: 1.5em;
  font-weight: bolder;
}

li > p {
  padding-right: 0.7em;
  color: darkblue;
}

h2 {
  padding: 0.5em 0;
  font-size: 2em;
  color: black;
}

.checkout-description > p {
  font-size: 1.5em;
  padding-left: 13em;
}

.button-shop-now {
  background-color: #fd9330;
  padding: 1rem 1.5rem;
  color: black;
  border-radius: 1rem;
  font-size: large;
  font-weight: 500;
  border: 3px solid black;
  cursor: pointer;
}

.button-shop-now:hover {
  color: white;
}

.empty-confirmation-page h3{
  font-size: 40px;
}

</style>