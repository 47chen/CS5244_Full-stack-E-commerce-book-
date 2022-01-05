<template>
  <ul>
    <!--    <category-book-list-item :book="books[0]"></category-book-list-item>-->
    <!--    <category-book-list-item :book="books[1]"></category-book-list-item>-->
    <!--    <category-book-list-item :book="books[2]"></category-book-list-item>-->
    <!--    <category-book-list-item :book="books[3]"></category-book-list-item>-->
    <template v-for="book in $store.state.selectedCategoryBooks">
      <li :key="book.bookId">
        <category-book-list-item :book="book"></category-book-list-item>
      </li>
    </template>
  </ul>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  props: {
    books: {
      type: Array,
      required: true,
    },
  },
  components: {
    CategoryBookListItem,
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 1em;
  gap: 1em;
}
</style>
