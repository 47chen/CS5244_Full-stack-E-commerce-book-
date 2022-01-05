<template>
  <div class="confirmation-table">
    <ul>
      <li class="confirmation-heading">
        <div class="confirmation-heading-book">Your book</div>
        <div class="confirmation-heading-price">price</div>
        <div class="confirmation-heading-quantity">Quantity</div>
        <div class="confirmation-heading-amount">Amount</div>
      </li>
      <li
          v-for="(item, index) in this.$store.state.orderDetails.lineItems"
          :key="item.productId"
      >
        <div class="confirmation-book-image">
          <img
              class="book_img"
              :src="require('@/assets/images/books/' +
              $store.state.orderDetails.books[index].imageFile)"
              :alt="$store.state.orderDetails.books[index].title"
              width="100px"
              height="auto"
          />
        </div>
        <div class="confirmation-book-title">{{ $store.state.orderDetails.books[index].title }}</div>
        <div class="confirmation-book-price">${{ $store.state.orderDetails.books[index].price.toFixed(2) / 100 }}</div>
        <div class="confirmation-book-quantity">{{item.quantity}}</div>
        <div class="confirmation-book-amount">
          <span>
            <Price :amount="item.quantity * orderDetails.books[index].price"></Price>
          </span>
        </div>
        <div class="row-sep"></div>
      </li>
    </ul>
  </div>
</template>

<script>
import {mapGetters, mapState} from "vuex"
import Price from "@/components/Price";

export default {
  name: "ConfirmationTable",
  components: {Price},
  computed: {
    ...mapState(["orderDetails"]),
    ...mapGetters(["categoryName"])
  }

};
</script>

<style scoped>

.confirmation-table {
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

.confirmation-heading {
  background-color: black;
  color: white;
}

.confirmation-heading > * {
  background-color: black;
  text-align: center;
}

.confirmation-heading-book {
  grid-column: 1 / 3 ;
  padding: 0 1em;
}

.confirmation-heading-price {
  grid-column: 3/4;
  padding: 0 1em;
}

.confirmation-heading-quantity {
  grid-column: 4 / 5;
  padding: 0 1em;
}

.confirmation-heading-amount {
  grid-column: 5 / 6;
  padding: 0 4em;
}

.confirmation-book-title {
  padding: 0 1em;
}

.confirmation-book-image {
  padding: 0 1em;
}

.confirmation-book-price {
  padding: 0 1em;
}

.confirmation-book-quantity {
  padding: 0 1em;
}

</style>
