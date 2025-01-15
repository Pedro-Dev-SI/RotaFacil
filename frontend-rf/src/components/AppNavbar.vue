<template>
  <nav class="bar">
    <router-link class="logo-router" to="/"><h1>Rota<b class="span-word">Fácil</b></h1></router-link>
  </nav>
</template>

<script>
export default {
    name: 'AppNavbar',
}
</script>

<style scoped lang="scss">

.bar {
  background-color: #32BC9B;
  margin: 0;
  padding: 0;
  display: flex; /* Usar Flexbox para controlar melhor o layout */
  align-items: center; /* Alinha o conteúdo verticalmente */
  justify-content: space-between; /* Garante que o conteúdo seja distribuído */
  height: 90px; /* Defina a altura da navbar */
  margin-bottom: 50px;
}

h1 {
  margin-left: 20px;
}

.span-word {
  color: #5AC8FA;
}

.logo-router {
  text-decoration: none;
  color: white;
}

</style>