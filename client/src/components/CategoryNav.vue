<template>
  <nav class="categoryNav">
    <ul class="buttonCategoryList">
      <template v-for="category in $store.state.categories"> <!-- state inside the store -->
        <router-link
          v-if="category.name === $store.state.selectedCategoryName"
          :to="'/category/' + category.name"
          :key="category.categoryId"
          tag="li"
          class="selected-category-button"
        >
          | {{ category.name }}
        </router-link>
        <router-link
          v-else
          :to="'/category/' + category.name"
          tag="li"
          class="button unselected-category-button"
          :key="category.categoryId"
        >
          | {{ category.name }}
        </router-link>
      </template>
      <!--      <li class="selected-category-button">Travel</li>-->
      <!--      <router-link-->
      <!--        to="../category/drama"-->
      <!--        tag="li"-->
      <!--        class="unselected-category-button"-->
      <!--      >-->
      <!--        Drama-->
      <!--      </router-link>-->
      <!--      <router-link-->
      <!--        to="category"-->
      <!--        tag="li"-->
      <!--        class="button unselected-category-button"-->
      <!--      >-->
      <!--        Mystery-->
      <!--      </router-link>-->
      <!--      <router-link-->
      <!--        to="../category/drama"-->
      <!--        tag="li"-->
      <!--        class="unselected-category-button"-->
      <!--      >-->
      <!--        Fiction-->
      <!--      </router-link>-->
      <!--      <router-link-->
      <!--        to="../category/children"-->
      <!--        tag="li"-->
      <!--        class="unselected-category-button"-->
      <!--      >-->
      <!--        Children-->
      <!--      </router-link>-->
    </ul>
  </nav>
</template>

<script>

export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.categoryNav {
  flex: 2 1 50px;
}

ul.buttonCategoryList {
  display: flex;
  flex-wrap: wrap;
  list-style-type: none;
}

.buttonCategoryList li {
  display: inline-block;
  vertical-align: top;
  text-transform: uppercase;
  font-weight: bold;
  font-size: 30px;
  padding: 0.1rem 0.5rem;
  border-radius: 10%;
  color: black;
  background: transparent;
  cursor: pointer;
}

li.selected-category-button {
  background-color: rgb(250, 189, 126);
}

/* these purely stylize the submit input */

li.unselected-category-button:hover {
  color: black;
  background-color: rgb(248, 223, 197);
}

/*.category-buttons {*/
/*  display: flex;*/
/*  flex-direction: row;*/
/*  text-align: center;*/
/*  background-color: var(--neutral-color);*/
/*}*/

/*.button.selected-category-button {*/
/*  background-color: var(--primary-color);*/
/*}*/

/*.button.unselected-category-button,*/
/*.button.unselected-category-button:visited {*/
/*  background-color: var(--neutral-color);*/
/*}*/

/*.button.unselected-category-button:hover,*/
/*.button.unselected-category-button:active {*/
/*  background-color: var(--primary-color);*/
/*}*/
</style>
