<template>
  <main>
    <section class="mainBackgroundImage">
      <div class="filter">
        <div>
          <category-nav></category-nav>
        </div>
        <div>
          <category-book-list :books="$store.state.selectedCategoryBooks">
          </category-book-list>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },

  created: function () {
    if (this.$route.params.name !== this.$store.state.selectedCategoryName) {
      this.$store.dispatch("selectCategory", this.$route.params.name);
      this.$store.dispatch("fetchSelectedCategoryBooks").catch(()=>{
        this.$router.push({name: "notFound"}) // "/404" triggers NotFound
      });
    }
  },
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
</style>
