<template>
  <div>
    <div class="center-box">
      <div class="box">
        <p>Rota <span>{{ codigoRota }}</span> cadastrada com sucesso.</p>
        <p>O que deseja fazer ?</p>

        <router-link :to="`/visualizar-rota/${codigoRota}`" class="nav-box">
          <h2>Visualizar rota</h2>
          <i class="bi bi-eye"></i>
        </router-link>

        <router-link to="/" class="nav-box">
          <h2>Menu princial</h2>
          <i class="bi bi-box-arrow-left"></i>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ResultadoCriacao',

  data() {
    return {
      codigoRota: this.$route.params.codigoRota,
    };
  },

  mounted() {
  }
}
</script>

<style scoped>
    i {
        font-size: 40px;
        margin-left: 10px;
        color: white;
    }

    p {
      color: white;
      font-size: 20px;
      font-weight: bold;
    }

    span {
      color: #32BC9B;
      font-size: 22px;
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