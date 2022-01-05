<template>
  <div class="searchBox">
    <form action="category.html"></form>
    <input
      type="text"
      class="searchBlank"
      placeholder="Start your journey..."
    />
    <button class="searchButton">
      <i class="fas fa-search"></i>
    </button>
  </div>
  <!--  <div class="searchBar">-->
  <!--    <form>-->
  <!--      <input-->
  <!--        type="text"-->
  <!--        class="searchBlank"-->
  <!--        placeholder="Start your journey..."-->
  <!--      />-->
  <!--      <input type="submit" value="Search" />-->
  <!--      <button class="searchButton"><i class="fas fa-search"></i>></button>-->
  <!--    </form>-->
  <!--  </div>-->
</template>

<script>
export default {
  name: "HeaderSearchBar",
};
</script>

<style scoped>
/*input[type="text"] {*/
/*  margin: 0.5em 0;*/
/*  width: 12em;*/
/*}*/
header div.searchBox input.searchBlank {
  font-family: "Noto Sans TC", sans-serif;
  font-size: 25px;
  color: black;
}

header div.searchBox button.searchButton {
  background-color: #f8a557;
  border: 2px solid black;
  padding: 0.8rem 1rem;
  margin-left: -1em;
  border-radius: 50%;
  display: inline-block;
  cursor: pointer;
  /* -webkit-transition: all 0.3s; */
  transition: all 0.3s;
  font-size: 16px;
}
</style>
