<template>
  <header>
    <div class="logo">
      <router-link to="/">
        <img
          src="../assets/images/site/open-book.svg"
          alt="UrBook logo"
          width="150px"
          height="auto"
        />
      </router-link>
      <router-link to="/"><h1>Urbook</h1> </router-link>
    </div>
    <div class="searchBox">
      <header-search-bar></header-search-bar>
      <header-dropdown-menu></header-dropdown-menu>
    </div>

    <div class="CartLogin">
      <div class="CartLogin">
        <ul>
          <router-link to="/cart" tag="li">
            <img
              src="../assets/images/site/shopping-cart.svg"
              art="ShoppingCart"
            />
            <span id="badge">{{
              $store.state.cart.numberOfItems
            }}</span></router-link
          >
          <router-link to="#" tag="li"
            ><img src="../assets/images/site/login.png" alt="LoginIcon" />
          </router-link>
        </ul>
      </div>
    </div>
    <!--      <router-link to="/">-->
    <!--        <img-->
    <!--          src="@/assets/images/site/bookstore-logo.png"-->
    <!--          alt="Another Bookstore Logo"-->
    <!--          width="150px"-->
    <!--          height="auto"-->
    <!--        />-->
    <!--      </router-link>-->
    <!--    </section>-->
    <!--    <section class="title-and-search-bar">-->
    <!--      <router-link to="/" tag="h1" class="text-logo">-->
    <!--        Another Bookstore-->
    <!--      </router-link>-->
    <!--      <form action="category.html">-->
    <!--        <input type="text" class="search-bar" /><br />-->
    <!--        <input type="submit" class="button search-button" value="Search" />-->
    <!--      </form>-->
    <!--    </section>-->
    <!--    <section class="header-dropdown-and-cart">-->
    <!--      <button class="button">cart (0)</button>-->
    <!--      <button class="button">login</button>-->
  </header>
</template>
<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
import HeaderSearchBar from "@/components/HeaderSearchBar";
export default {
  name: "AppHeader",
  components: { HeaderSearchBar, HeaderDropdownMenu },
  component: {
    HeaderDropdownMenu,
  },
};
</script>

<style scoped>
header {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  background-color: #fff3ca;
  /* -webkit-box-align: center;
  -ms-flex-align: center; */
  align-items: center;
  /* -webkit-box-pack: justify;
  -ms-flex-pack: justify; */
  justify-content: space-between;
  /* -webkit-box-shadow: 3px 3px 5px 1px #706c6c; */
  width: 100%;

  /** Logo+Text **/
  /** searchBox+searchIcon **/
  /** cart+login **/
}

header div.logo {
  display: flex;
  /* -webkit-box-align: center;
  -ms-flex-align: center; */
  align-items: center;
}

/*header div.logo {*/
/*  transition: transform 1s;*/
/*}*/

/*header div.logo:hover {*/
/*  transform: rotate(360deg);*/
/*}*/

header div.logo a {
  text-decoration: none;
  color: red;
}

header div.logo img {
  width: 10vw;
  height: auto;
}

header div.CartLogin ul {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  list-style-type: none;
  /* -webkit-box-pack: justify;
  -ms-flex-pack: justify; */
  justify-content: space-between;
}

header div.CartLogin ul a {
  text-decoration: none;
}

header div.CartLogin img {
  width: 5rem;
  height: 5rem;
  margin-top: 2rem;
  margin-right: 2rem;
  cursor: pointer;
}

header #badge {
  width: 2vw;
  height: 2vw;
  line-height: 2vw;
  text-align: center;
  background-color: red;
  color: white;
  font-size: 20px;
  font-weight: bolder;
  border-radius: 50%;
  position: relative;
  bottom: 6rem;
  right: -2.2rem;
  padding: 0.3rem 0.6rem;
}

header span {
  cursor:pointer;
}
/*header {*/
/*  background: var(--secondary-background-color);*/
/*  display: flex;*/
/*  flex-direction: row;*/
/*  padding: 1em;*/
/*  gap: 1em;*/
/*}*/

/*.text-logo {*/
/*  font-family: var(--title-font-family);*/
/*}*/

/*.search-bar {*/
/*  padding: 0.5em;*/
/*}*/

/*@media (max-width: 600px) {*/
/*  header {*/
/*    flex-direction: column;*/
/*  }*/
/*}*/
</style>
