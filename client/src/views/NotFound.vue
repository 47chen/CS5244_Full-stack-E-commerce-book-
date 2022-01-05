<template>
  <main>
    <section class="mainBackgroundImage">
      <div class="filter">
  <div class="container">
      <router-link :to="'/category/Travel'">
        <button class="button-shop-now">GO SHOP!</button>
      </router-link>
    <h1>404</h1>
    <p>We're sorry that page cannot be found on this site.</p>
  </div>
      </div>
    </section>
  </main>
</template>

<script>

export default {
  name: "NotFound",
};
</script>

<style scoped>
main section.mainBackgroundImage {
  height: auto;
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
  justify-content: space-between;
}

main div.filter {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(238, 236, 236, 0.4);
  width: 100%;
  height: auto;
  min-height: 50vw;

  top: 0;
  z-index: -1;
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

p {
  padding: 1em;
  font-size: 18px;
}
h1 {
  padding: 1em;
}

</style>