<template>
  <div>
    <app-navbar/>
    <router-view/>
  </div>
</template>

<script>

import 'normalize.css'
import AppNavbar from './components/AppNavbar.vue'

export default {
  name: 'App',
  components: {
    AppNavbar
  },
  data() {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style>

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  html, body {
    margin: 0; /* Remove margens padrão do navegador */
    padding: 0; /* Remove padding padrão */
    /* overflow: hidden; Bloqueia o scroll horizontal */
    width: 100%; /* Garante que o conteúdo fique limitado à largura da viewport */
  }

  nav {
    margin: 0;
    padding: 0;
  }

</style>
