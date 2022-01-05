<template>
  <div class="header-dropdown">
    <button class="dropButton">
      Category<i class="fas fa-chevron-circle-down"></i>
    </button>
    <ul class="dropdown-content">
      <template v-for="category in $store.state.categories">
        <router-link
          :to="'/category/' + category.name"
          :key="category.categoryId"
          tag="li"
        >
          {{ category.name }}</router-link>
        <!--      <template v-for="category in categories">-->
        <!--        <li :key="category.categoryId" class="dropdown-content">-->
        <!--          <router-link to="'/category/' + category.name">-->
        <!--            {{ category.name }}-->
        <!--          </router-link>-->
        <!--below is my first try for fetch -->
        <!--      <li><router-link to="../category/Travel">Travel</router-link></li>-->
        <!--      <li><router-link to="../category/Drama">Drama</router-link></li>-->
        <!--      <li><router-link to="../category/Mystery">Mystery</router-link></li>-->
        <!--      <li><router-link to="../category/Fiction">Fiction</router-link></li>-->
        <!--      <li><router-link to="../category/Children">Children</router-link></li>-->
        <!--        </li>-->
        <!--      </template>-->
      </template>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
};
</script>

<style scoped>
header div.header-dropdown {
  /* -webkit-box-pack: flex-star;
  -ms-flex-pack: flex-star; */
  justify-content: flex-start;
  position: relative;
  display: inline-block;
}
header ul.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  width: 100%;
  /* -webkit-box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2); */
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

header ul.dropdown-content li {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  cursor: pointer;
}

header ul.dropdown-content li:hover {
  background-color: rgb(143, 141, 141);
  color: white;
}

header button.dropButton {
  padding: 0.5rem 3rem;
  font-size: 1.5rem;
  border-radius: 50px;
  background-color: black;
  color: white;
  cursor: pointer;
}

header .dropButton i {
  padding-left: 0.5rem;
}

header button.dropButton:hover:hover {
  color: orange;
}

header .header-dropdown:hover:hover .dropdown-content {
  display: block;
  z-index: 2;
}

/*header .header-dropdown li:hover:hover .dropButton {*/
/*  background-color: black;*/
/*}*/

/*header .header-dropdown:hover:hover .dropButton li a {*/
/*  color: white;*/
/*}*/
</style>
