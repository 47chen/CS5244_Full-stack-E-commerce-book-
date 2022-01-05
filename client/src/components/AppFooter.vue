<template>
  <footer>
    <section class="copyrightText">
      <h4>
        copyright<img
          src="../assets/images/site/copyright.svg"
          alt="copyright"
        />2021 ShihChi
      </h4>
    </section>
    <section class="TextAboutDirectionContact">
      <div class="container1>">
        <router-link to="/">About</router-link>
        <router-link to="/">Contact</router-link>
        <router-link to="/">Directions</router-link>
      </div>
    </section>
    <section class="SocialMediaIcon">
      <router-link to="#"
        ><img src="../assets/images/site/facebook.svg" alt="Facebook_icon"
      /></router-link>
      <router-link to="#"
        ><img src="../assets/images/site/instagram.svg" alt="Instagram_icon"
      /></router-link>
      <router-link to="#"
        ><img src="../assets/images/site/twitter.svg" alt="Twitter_icon"
      /></router-link>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  display: flex;
  background-color: #fccbc0;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 100%;
  height: auto;
}

footer section.copyrightText {
  padding: 1rem;
  width: 20%;
}

footer section.copyrightText h4 {
  color: black;
  font-style: italic;
}

footer section.copyrightText img {
  width: 1rem;
  height: 1rem;
}

footer section.TextAboutDirectionContact {
  /*display: flex;*/
  font-style: italic;
  width: 25%;
}

footer section.TextAboutDirectionContact a {
  padding: 0 2em;
  color: black;
  font-weight: bolder;
  font-size: 1rem;
}

footer section.TextAboutDirectionContact a:hover {
  color: white;
}

footer section.SocialMediaIcon {
  /*display: -webkit-box;*/
  /*display: -ms-flexbox;*/
  width: auto;
  height: 3rem;
  display: flex;
  /* -webkit-box-align: center;
  -ms-flex-align: center; */
  align-items: center;
}

footer section.SocialMediaIcon img {
  width: 5em;
  height: 2.5rem;
  padding: 0vw 1vw;
  transition: width 0.5s, height 0.5s;
}

footer section.SocialMediaIcon img:hover {
  width: 10em;
  height: 6rem;
}
</style>
