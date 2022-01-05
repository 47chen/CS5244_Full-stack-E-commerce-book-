<template>
  <ul>
    <router-link to="../category/Travel" tag="li">
      <img
        src="@/assets/images/categories/TravelBookStyle.jpg"
        alt="Travel category"
      />
      <div>Travel</div>
    </router-link>
    <router-link to="../category/Drama" tag="li">
      <img
        src="@/assets/images/categories/DramaBookStyle.jpg"
        alt="fantasy category"
      />
      <div>Drama</div>
    </router-link>
    <router-link to="../category/Mystery" tag="li">
      <img
        src="@/assets/images/categories/MysteryBookStyle.jpg"
        alt="Mystery category"
      />
      <div>Mystery</div>
    </router-link>
    <router-link to="../category/Fiction" tag="li">
      <img
        src="@/assets/images/categories/fictionBookStyle.jpg"
        alt="Fiction category"
      />
      <div>Fiction</div>
    </router-link>
    <router-link to="../category/Children" tag="li">
      <img
        src="@/assets/images/categories/childrenBookStyle.jpg"
        alt="Children category"
      />
      <div>Children</div>
    </router-link>
  </ul>
</template>

<script>
export default {
  name: "HomeCategoryList",
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
}

li {
  text-align: center;
  cursor: pointer;
}

li img {
  width: 180px;
  height: 180px;
  transition: width 1s, height 1s;
}

li div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}

li div:hover {
  color: orange;
}

li img:hover {
  width: 230px;
  height: 230px;
}
</style>
