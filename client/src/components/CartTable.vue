<template>
  <div class="cart-table">
    <!--here is your CSS grid, just like flex-container-->
    <!--each list represent row table-->
    <ul>
      <li class="cart-heading">
        <div class="cart-heading-book">Book</div>
        <div class="cart-heading-price">Price</div>
        <div class="cart-heading-quantity">Quantity</div>
        <div class="cart-heading-subtotal">Amount</div>
      </li>
      <li v-for="item in $store.state.cart.items" :key="item.book.bookId">
        <div class="cart-book-image">
          <img
            :src="require('@/assets/images/books/' + item.book.imageFile)"
            :alt="item.book.title"
            width="100px"
            height="auto"
          />
        </div>
        <div class="cart-book-title">{{ item.book.title }}</div>
        <div class="cart-book-price">
          ${{ item.book.price.toFixed(2) / 100 }}
        </div>
        <div class="cart-book-quantity">
          <span class="row-book-quantity">{{ item.quantity }}</span>
          <button
            class="icon-button inc-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-plus-circle"></i>
          </button>
          <button
            class="icon-button dec-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-minus-circle"></i>
          </button>
        </div>
        <div class="subtotal">
          <!--          ${{ (item.quantity * item.book.price.toFixed(2)) / 100 }}-->
          <Price :amount="item.quantity * item.book.price"></Price>
        </div>
        <div class="row-sep"></div>
      </li>
    </ul>
  </div>
</template>

<script>
import Price from "@/components/Price";
export default {
  name: "CartTable",
  components: {
    Price,
  },

  methods: {
    addToCart(book) {
      this.$store.dispatch("addToCart", book); // dispatch -> update store sync ( by sending a message to store)
      // this.$store.state.cart.addItem(book,1); // bad, because it will directly change the cart, but not through the store
    },
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity }); // payload usually pass one params, put {{}} to push both book and quantity
    },
  },
};
</script>

<!--repeat(5, auto) = auto auto auto auto auto, represent each column you want it auto-->

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  background-color: #fcfce6ff;
  margin: 0 auto;
  padding: 1em;
}

ul,
li {
  display: contents;
}

.row-sep {
  grid-column: 1 / -1;
  background-color: gray;
  height: 2px;
}

.cart-heading {
  background-color: black;
  color: white;
}

.cart-heading > * {
  background-color: black;
}

.cart-heading-book {
  grid-column: 1 / 3;
  padding: 0 1em;
}

.cart-heading-price {
  grid-column: 3/4;
  padding: 0 1em;
}

.cart-heading-quantity {
  grid-column: 4 / 5;
  padding: 0 1em;
}

.cart-heading-subtotal {
  grid-column: 5 / 6;
  padding: 0 1em;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-title {
  padding: 0 1em;
}

.cart-book-price {
  padding: 0 1em;
}

.cart-book-quantity {
  padding: 0 1em;
}

.subtotal {
  padding: 0 1em;
}

.icon-button:hover {
  color: #f8a557;
  cursor: pointer;
}

.icon-button {
  background-color: transparent;
  color: #fd9330;
  font-size: 1rem;
  border: none;
}

.inc-button {
  margin-left: 0.5em;
}

.dec-button {
  margin-left: 0.2em;
  color: gray;
}

.dec-button:hover {
  color: dimgray;
}
</style>
