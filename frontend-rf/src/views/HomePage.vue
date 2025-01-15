<template>
  <div>
    <div class="center-box">
      <div class="box">
        <router-link to="/cadastrar-entrega" class="nav-box">
          <h2>Cadastrar Entrega</h2>
          <i class="bi bi-truck"></i>
        </router-link>

        <div class="nav-box">
          <h2>Visualizar Entregas</h2>
          <i class="bi bi-eye"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import 'normalize.css'

export default {
  name: 'HomePage',
  components: {
    
  },
  data() {
  }
}
</script>

<style scoped lang="scss">

  i {
    font-size: 40px;
    margin-left: 10px;
  }

  .center-box {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
  }

  .box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 90px;
    width: 700px;
    height: 600px;
    background-color: #5AC8FA;
    border-radius: 20px;
  }

  .nav-box {
    background-color: #32BC9B;
    height: 110px;
    width: 70%; /* Ajusta a largura das caixas para ocupar mais espaço */
    border-radius: 20px;
    margin: 40px 0; /* Adiciona um pequeno espaço vertical entre as caixas */
    transition: transform 0.3s ease, background-color 0.3s ease;
    cursor: pointer;
    text-decoration: none;
  }

  .nav-box:hover {
    transform: scale(1.02); /* Aumenta ligeiramente o tamanho */
    background-color: #218b71; /* Torna a cor ligeiramente mais escura */
  }

  /* Adicione centralização ao conteúdo interno, caso necessário */
  .nav-box {
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
  }

</style>
