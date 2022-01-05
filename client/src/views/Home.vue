<template>
  <main>
    <section class="mainBackgroundImage">
      <div class="filter">
        <div class="WelcomeAndStartButton">
          <h3 style="color: black">
            Ready to enjoy Ur Bookstore?<br />
            Start to find your favorite books
          </h3>
          <br />
          <router-link to="/category/Travel"
            ><button class="start">Shop now</button></router-link
          >
        </div>
        <div class="BestSelling">
          <br />
          <h3 style="color: rgb(194, 7, 80)">Best Selling</h3>
          <br />
          <home-category-list></home-category-list>
        </div>
      </div>
    </section>
  </main>

  <!--  <div class="home-page">-->
  <!--    <section class="welcome-text flow-content container dark-background">-->
  <!--      <h2>Welcome to Another Bookstore</h2>-->
  <!--      <p>-->
  <!--        Another Bookstore is a client-side application written using Vue-->
  <!--        components.-->
  <!--      </p>-->
  <!--      <p>-->
  <!--        It includes a home page and a category page. Both pages have the same-->
  <!--        header and footer.-->
  <!--      </p>-->
  <!--      <p>-->
  <!--        You can navigate to the category page by choosing any category from the-->
  <!--        dropdown menu in the header. You can navigate to the home page by-->
  <!--        clicking on the logo in the header.-->
  <!--      </p>-->
  <!--      <p>Take some time and explore the code.</p>-->
  <!--    </section>-->
  <!--    <section class="category-images container">-->

  <!--    </section>-->
  <!--  </div>-->
</template>

<script>
import HomeCategoryList from "@/components/HomeCategoryList";
export default {
  name: "Home",
  components: { HomeCategoryList },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans TC", sans-serif;
}
main section.mainBackgroundImage {
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
  text-align: center;

  justify-content: center;
}

main div.filter {
  background-color: rgba(252, 248, 248, 0.4);
  width: 100%;
  min-height: 50vw;
  top: 0;
  z-index: -1;
  justify-content: center;
}

main .WelcomeAndStartButton h3 {
  padding-top: 3em;
  font-size: 3.5rem;
  color: black;
}

main div.BestSelling h3 {
  font-size: 3rem;
}

div.BestSelling {
  display: flex;
  flex-direction: column;
  padding: 1em;
  align-items: center;
}

main button.start {
  background-color: #fd9330;
  padding: 1rem 1.5rem;
  color: black;
  border-radius: 1rem;
  font-weight: 500;
  border: 3px solid black;
  font-size: 30px;
  cursor: pointer;
}

main button.start:hover {
  color: white;
}
</style>
