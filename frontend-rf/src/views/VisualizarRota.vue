<template>
  <div>
    <h2>Mapa de Entregas</h2>
    <p>Rota: {{ rota }}</p>
    <p v-if="localizacaoAtual">Sua localização: {{ localizacaoAtual.latitude }}, {{ localizacaoAtual.longitude }}</p>
    <p v-else>Aguardando permissão para acessar a localização...</p>

    <div v-if="localizacaoAtual">
        <Mapa :entregas="entregas" :origem="localizacaoAtual" />
    </div>

    <router-link to="/">Voltar</router-link>
  </div>
</template>

<script>
import Mapa from "@/components/Mapa"; // Ajuste para o caminho correto do seu componente.
import EntregaService from '@/services/entrega.service';

export default {
  name: "VisualizarRota",
  components: { Mapa },
  data() {
    return {
      rota: null,
      entregas: [],
      localizacaoAtual: null, // Guardará as coordenadas do usuário.
    };
  },
  async mounted() {
    // Obtém o código da rota passado pela navegação.
    this.rota = this.$route.params.codigoRota;

    // Obtém as entregas associadas à rota (simulação ou backend).
    await this.fetchEntregas(this.rota);

    console.log(this.entregas);

    // Obtém a localização atual do usuário.
    this.obterLocalizacaoAtual();
  },
  methods: {
    async fetchEntregas(codigoRota) {
        this.entregas = await EntregaService.getEntregasByCodigoRota(codigoRota);
    },
    
    obterLocalizacaoAtual() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
            (position) => {
                this.localizacaoAtual = {
                latitude: position.coords.latitude,
                longitude: position.coords.longitude,
                precisão: position.coords.accuracy, // Precisão da localização em metros
                };
            },
            (error) => {
                console.error("Erro ao obter localização:", error);
                alert("Não foi possível acessar sua localização.");
            },
            {
                enableHighAccuracy: true, // Solicita uma precisão mais alta
                timeout: 5000, // Tempo limite de 5 segundos para obter a localização
                maximumAge: 0, // Força a solicitação de uma nova localização, sem usar dados em cache
            }
            );
        } else {
            alert("Geolocalização não é suportada neste navegador.");
        }
    },
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
  margin-top: 20px;
}

p {
  text-align: center;
  font-size: 1.2rem;
  color: #555;
}

router-link {
  display: block;
  text-align: center;
  margin-top: 20px;
  text-decoration: none;
  color: #5ac8fa;
}

router-link:hover {
  text-decoration: underline;
}
</style>
