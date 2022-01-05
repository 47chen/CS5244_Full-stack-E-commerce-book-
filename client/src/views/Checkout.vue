<template>
  <main>
    <section class="mainBackgroundImage">
      <div class="filter">
        <div class="checkout-page">
          <section class="checkout-page-body-empty" v-if="cart.empty">
            <h1>You need to add an item to your cart for checkout!</h1>
            <br />
            <router-link :to="'/category/Travel'">
              <button class="button continue-button">Continue Shopping</button>
            </router-link>
          </section>

          <section class="checkout-page-body" v-else>
            <form @submit.prevent="submitOrder">
              <!--prevent will submit the whole page-->
              <div>
                <label for="name">Name</label>
                <input
                  type="text"
                  size="36"
                  id="name"
                  name="name"
                  v-model.lazy="$v.name.$model"
                />
              </div>
              <template v-if="$v.name.$error">
                <span class="error" v-if="!$v.name.required"
                  >Name is required</span
                >
                <span class="error" v-else-if="!$v.name.minLength">
                  Name must have at least
                  {{ $v.name.$params.minLength.min }} letters.
                </span>
                <span class="error" v-else-if="!$v.name.maxLength">
                  Name can have at most
                  {{ $v.name.$params.maxLength.max }} letters.
                </span>
                <span class="error" v-else>
                  An unexpected error occurred.
                </span>
              </template>
              <div>
                <label for="address">Address</label>
                <input
                  type="text"
                  size="36"
                  id="address"
                  name="address"
                  v-model.lazy="$v.address.$model"
                />
              </div>
              <template v-if="$v.address.$error">
                <span class="error" v-if="!$v.address.required"
                  >Address is required</span
                >
                <span class="error" v-else-if="!$v.address.minLength"
                  >Address must have at least
                  {{ $v.address.$params.minLength.min }} letters.</span
                >
                <span class="error" v-else-if="!$v.address.maxLength"
                  >Address can have at most
                  {{ $v.address.$params.maxLength.max }} letters.</span
                >
                <span class="error" v-else>An unexpected error occurred.</span>
              </template>
              <div>
                <label for="phone">Phone</label>
                <input
                  class="textField"
                  type="text"
                  size="36"
                  id="phone"
                  name="phone"
                  v-model.lazy="$v.phone.$model"
                />
              </div>
              <template v-if="$v.phone.$error">
                <span class="error" v-if="!$v.phone.required"
                  >Phone is required</span
                >
                <span class="error" v-else-if="!$v.phone.isPhone"
                  >Please enter a valid phone number.</span
                >
                <span class="error" v-else>An unexpected error occurred.</span>
              </template>

              <div>
                <label for="email">Email</label>
                <input
                  type="text"
                  size="36"
                  id="email"
                  name="email"
                  v-model.lazy="$v.email.$model"
                />
              </div>
              <template v-if="$v.email.$error">
                <span class="error" v-if="!$v.email.required"
                  >Email is required</span
                >
                <span class="error" v-else-if="!$v.email.isEmail"
                  >Please enter a valid email.</span
                >
                <span class="error" v-else>An unexpected error occurred.</span>
              </template>

              <div>
                <label for="ccNumber">Credit card</label>
                <input
                  type="text"
                  size="36"
                  id="ccNumber"
                  name="ccNumber"
                  v-model.lazy="$v.ccNumber.$model"
                />
              </div>
              <template v-if="$v.ccNumber.$error">
                <span class="error" v-if="!$v.ccNumber.required"
                  >Credit card number is required</span
                >
                <span class="error" v-else-if="!$v.ccNumber.creditCard"
                  >Please enter a valid credit card number.</span
                >
                <span class="error" v-else>An unexpected error occurred.</span>
              </template>

              <div>
                <label>Exp Date</label>
                <select v-model="ccExpiryMonth" class="expiry-form">
                  <option
                    v-for="(month, index) in months"
                    :key="index"
                    :value="index + 1"
                  >
                    {{ month }} ({{ index + 1 }})
                  </option>
                </select>
                <br />
                <select v-model="ccExpiryYear" class="expiry-form">
                  <!--when you are using index in v-for, it may count from 0 to x, which x will be the number to end up the for loop-->
                  <option v-for="(year, index) in 15" :key="index">
                    {{ yearFrom(index) }}
                  </option>
                </select>
              </div>
              <span class="error" v-if="!$v.ccExpiryMonth.required"
                >Expiry month is required</span
              >
              <span class="error" v-if="!$v.ccExpiryYear.required"
                >Expiry year is required</span
              >

              <!--            <div-->
              <!--                style="-->
              <!--          border: 1px solid black;-->
              <!--          padding: 1em;-->
              <!--          margin-left: 1em;-->
              <!--          text-align: left;-->
              <!--        "-->
              <!--            >-->
              <!--              <tree-view-->
              <!--                  :data="$v"-->
              <!--                  :options="{ rootObjectKey: '$v', maxDepth: 1 }"-->
              <!--              ></tree-view>-->
              <!--            </div>-->
              <!-- TODO: Display the cart total, subtotal and surcharge.  ----- DONE!-->
              <!-- TODO: adjust the cart total, subtotal and surcharge in CSS-->

              <p class="checkout-description total">
                Your credit card will be charged
                <Price :amount="cart.subtotal + cart.surcharge"></Price>
              </p>
              <div>
                <p class="checkout-description sub">
                  (<Price :amount="cart.subtotal"></Price> subtotal +
                  <Price :amount="cart.surcharge"></Price> shipping)
                </p>
              </div>
              <input
                type="submit"
                name="submit"
                class="button"
                id="btn"
                :disabled="checkoutStatus == 'PENDING'"
                value="Complete Purchase"
              />
            </form>
            <!--            (<Price :amount="cart.subtotal"></Price> + <Price :amount="cart.surcharge"></Price> shipping)-->
          </section>
        </div>
        <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
          <div v-if="checkoutStatus == 'ERROR'">
            <h3>Error: Please fix the problems above and try again.</h3>
          </div>

          <div v-else-if="checkoutStatus == 'PENDING'">
            <p class="error-message">Processing...</p>
          </div>

          <div v-else-if="checkoutStatus == 'OK'">
            <p class="error-message">Order placed...</p>
          </div>

          <div v-else>
            <!-- "checkoutStatus == 'SERVER_ERROR'" -->
            An unexpected error occurred, please try again.
          </div>
          <div class="lds-roller">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
          </div>
        </section>
        <!--        <router-link :to="'/cart'">-->
        <!--          <button class="button-back-to-cart">Back to Cart</button>-->
        <!--        </router-link>-->
      </div>
    </section>
  </main>
</template>

<script>
import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isCreditCard from "validator/lib/isCreditCard";
import isMobilePhone from "validator/lib/isMobilePhone";
import Price from "@/components/Price";

const isPhone = (value) => isMobilePhone(value, "en-US"); // return value -> true or false

const creditCard = (value) => isCreditCard(value);

export default {
  components: { Price },
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      isPhone,
    },
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    email: {
      required,
      isEmail: email, // email -> boolean value, return ture & false
    },

    ccNumber: {
      required,
      creditCard,
    },

    ccExpiryMonth: {
      required,
    },

    ccExpiryYear: {
      required,
    },
    // TODO: Add more validations for the other fields that need validation.
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    },
  },

  methods: {
    submitOrder() {
      console.log("Submit order");
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$invalid) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.$store
            .dispatch("placeOrder", {
              name: this.name,
              address: this.address,
              phone: this.phone,
              email: this.email,
              ccNumber: this.ccNumber,
              ccExpiryMonth: this.ccExpiryMonth,
              ccExpiryYear: this.ccExpiryYear,
            })
            .then(() => {
              this.checkoutStatus = "OK";
              this.$router.push({ name: "confirmation" });
            })
            .catch((reason) => {
              this.checkoutStatus = "SERVER_ERROR";
              console.log("Error placing order", reason);
            });
        }, 2000);
      }
    },

    /* NOTE: For example yearFrom(0) == 2021 */
    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
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
  text-align: right;

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

/*.button-back-to-cart {*/
/*  color: black;*/
/*  font-weight: bold;*/
/*  padding: 0.1rem 0.5rem;*/
/*  border: 1px solid black;*/
/*  border-radius: 10px;*/
/*  background: #fc5959;*/
/*  cursor: pointer;*/
/*  font-size: 23px;*/
/*}*/

.checkout-page {
  background: #fcfce6ff;
  color: black;
  opacity: 0.8;
}
.checkout-page-body {
  display: flex;
  flex-direction: column;
  padding: 1em;
}

form {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: flex-end;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

label {
  font-weight: bolder;
}

form > div > input,
form > div > select {
  background-color: white;
  margin-left: 0.5em;
}

form > .error {
  color: red;
  text-align: right;
  font-size: 1.1em;
  font-style: italic;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.button {
  color: black;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  background: #fd9330;
  cursor: pointer;
  font-size: 23px;
  width: 15rem;
  margin-left: 9em;
}

.button:hover {
  color: white;
}

.continue-button {
  gap: 2em;
  background-color: #fc5959;
}

.checkout-page-body-empty {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.checkout-description {
  font-size: 1.5em;
}

h3 {
  font-size: 2em;
  color: red;
}

p.error-message {
  font-size: 5em;
  color: black;
}

option {
  width: 30em;
}

.lds-roller {
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
}
.lds-roller div {
  animation: lds-roller 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
  transform-origin: 40px 40px;
}
.lds-roller div:after {
  content: " ";
  display: block;
  position: absolute;
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: darkorange;
  margin: -4px 0 0 -4px;
}
.lds-roller div:nth-child(1) {
  animation-delay: -0.036s;
}
.lds-roller div:nth-child(1):after {
  top: 63px;
  left: 63px;
}
.lds-roller div:nth-child(2) {
  animation-delay: -0.072s;
}
.lds-roller div:nth-child(2):after {
  top: 68px;
  left: 56px;
}
.lds-roller div:nth-child(3) {
  animation-delay: -0.108s;
}
.lds-roller div:nth-child(3):after {
  top: 71px;
  left: 48px;
}
.lds-roller div:nth-child(4) {
  animation-delay: -0.144s;
}
.lds-roller div:nth-child(4):after {
  top: 72px;
  left: 40px;
}
.lds-roller div:nth-child(5) {
  animation-delay: -0.18s;
}
.lds-roller div:nth-child(5):after {
  top: 71px;
  left: 32px;
}
.lds-roller div:nth-child(6) {
  animation-delay: -0.216s;
}
.lds-roller div:nth-child(6):after {
  top: 68px;
  left: 24px;
}
.lds-roller div:nth-child(7) {
  animation-delay: -0.252s;
}
.lds-roller div:nth-child(7):after {
  top: 63px;
  left: 17px;
}
.lds-roller div:nth-child(8) {
  animation-delay: -0.288s;
}
.lds-roller div:nth-child(8):after {
  top: 56px;
  left: 12px;
}
@keyframes lds-roller {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
