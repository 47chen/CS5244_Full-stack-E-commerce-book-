<template>
  <li class="book-box">
    <div class="book-image">
      <img
        class="book_img"
        :src="require('@/assets/images/books/' + book.imageFile)"
        :alt="book.title"
      />
    </div>
    <button class="button-read" v-if="book.isPublic">Read Now</button>
    <div class="book-info">
      <div class="book-title">{{ book.title }}</div>
      <div class="book-author">{{ book.author }}</div>
      <!--      <div class="book-price">${{ book.price.toFixed(2) / 100 }}</div>-->
      <div class="book-price"><Price :amount="book.price"></Price></div>
      <button class="button-add" @click="addToCart(book)">Add to Cart</button>
    </div>
  </li>
</template>

<script>
import Price from "@/components/Price";
export default {
  name: "categoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  components: {
    Price,
  },

  methods: {
    addToCart(book) {
      this.$store.dispatch("addToCart", book); // dispatch -> update store sync
      // this.$store.state.cart.addItem(book,1); // bad, because it will directly change the cart, but not through the store
    },
  },
};
</script>
<style scoped>
.book-box {
  display: flex;
  flex-direction: row;
  background-color: rgb(246, 246, 207);
  padding: 1em;
  gap: 0.25em;
  width: 400px;
}

.book-image {
  width: 144px;
  height: 186px;
}

.book_img {
  width: 144px;
  height: 186px;
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}

.book-price {
  font-weight: bold;
}

.book-info {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-between;
  width: 235px;
  height: 186px;
  align-items: flex-start;
  text-align: left;
}

.button-add {
  display: inline-block;
  vertical-align: top;
  text-transform: uppercase;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  background: #fc5959;
  cursor: pointer;
  width: 8.5em;
}

.button-add:hover {
  color: white;
}

.button-read {
  background-color: dimgrey;
  color: white;
  font-weight: bold;
  padding: 0.1rem 0.5rem;
  border: 1px solid black;
  border-radius: 10px;
  width: auto;
  cursor: pointer;
  position: absolute;
  margin-top: 9.9em;
  margin-left: 1.2em;
}

.button-read:hover {
  background-color: white;
  color: black;
}
</style>
