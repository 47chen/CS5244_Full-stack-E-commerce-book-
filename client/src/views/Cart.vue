<template>
  <section class="mainBackgroundImage">
    <div class="filter">
      <div class="cart-page">
        <section
          v-if="$store.state.cart.numberOfItems === 0"
          class="empty-cart-page"
        >
          <h3>Your cart is empty, ready to shop?</h3>
          <router-link :to="'/category/Travel'">
            <button class="button-shop-now">GO SHOP!</button>
          </router-link>
        </section>
        <section v-else class="non-empty-cart-page">
          <div class="cart-buttons">
            <template v-if="$store.state.selectedCategoryName === ''">
              <router-link :to="'/category/Travel'">
                <button
                  class="button-continuous-shopping"
                  @click="nullCategoryName"
                >
                  CONTINUE SHOPPING
                </button>
              </router-link>
            </template>
            <template v-else>
              <router-link
                :to="'/category/' + $store.state.selectedCategoryName"
              >
                <button class="button-continuous-shopping">
                  CONTINUE SHOPPING
                </button>
              </router-link>
            </template>
            <!--            <p>Cart total: $ {{ cart.total }}</p>-->
            <router-link :to="'/checkout/'">
              <button class="button-checkout">PROCEED TO CHECKOUT</button>
            </router-link>
          </div>
          <cart-table></cart-table>
          <div class="cart-description">
            <p v-if="$store.state.cart.numberOfItems === 1">
              Your cart contains 1 book.
            </p>
            <p v-else>
              Your cart contains {{ $store.state.cart.numberOfItems }} books.
            </p>
            <!--            <p>The subtotal is ${{ $store.state.cart.subtotal }}</p>-->
            <p>
              The subtotal is
              <Price :amount="$store.state.cart.subtotal"></Price>
            </p>
            <button class="button link-like-button" @click="clearCart()">
              CLEAR THE CART
            </button>
          </div>
        </section>
      </div>
    </div>
  </section>
</template>

<script>
import CartTable from "@/components/CartTable";
import Price from "@/components/Price";

export default {
  name: "Cart",
  components: { Price, CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },

    nullCategoryName() {
      this.$store.dispatch(
        "selectCategory",
        this.$store.state.selectedCategoryName
      );
    },
  },
};
</script>

<style scoped>
section.mainBackgroundImage {
  min-height: 50vw;
  width: 100%;
  background-image: url("../assets/images/site/ＷelcomePage_BackgroundImage-2.png");
  background-size: cover;
  background-position: center;
  position: relative;
  z-index: 1;
  opacity: 1;
  display: flex;
  align-items: center;
  flex-direction: column;
  align-content: center;
}

div.filter {
  background-color: rgba(252, 248, 248, 0.4);
  width: 100%;
  min-height: 50vw;
  top: 0;
  z-index: -1;
}

h3 {
  text-align: center;
}

.cart-page {
  padding: 1em;
}

.non-empty-cart-page {
  display: flex;
  flex-direction: column;
  gap: 1em;
  justify-content: center;
}

.cart-description {
  display: flex;
  flex-direction: column;
  gap: 0.5em;
  text-align: center;
}

.cart-buttons {
  display: flex;
  justify-content: space-around;
}

.link-like-button {
  align-self: center;
  color: white;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  background: dimgrey;
  cursor: pointer;
  font-size: 20px;
}

.link-like-button:hover {
  color: black;
  background-color: white;
}

.button-continuous-shopping {
  color: black;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  background: #fc5959;
  cursor: pointer;
  font-size: 23px;
}

.button-continuous-shopping:hover,
.button-checkout:hover {
  color: white;
}

.button-checkout {
  color: black;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  background: #fd9330;
  cursor: pointer;
  font-size: 23px;
}

.cart-page h3 {
  font-size: 60px;
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

.empty-cart-page {
  display: flex;
  gap: 2em;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

p {
  font-size: 30px;
}
</style>
