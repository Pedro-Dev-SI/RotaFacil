<template>
  <div id="map" style="height: 500px;"></div>
</template>

<script>
import L from 'leaflet';

export default {
  name: 'Mapa',
  props: {
    entregas: {
      type: Array,
      required: true
    }
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      const map = L.map('map').setView([51.505, -0.09], 13); // Definir uma localização inicial

      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      }).addTo(map);

      // Adiciona os pontos de entrega
      this.entregas.forEach(entrega => {
        const { latitude, longitude } = entrega; // Supondo que você tem essas coordenadas na entrega
        L.marker([latitude, longitude]).addTo(map)
          .bindPopup(`<b>Entrega</b><br>${entrega.endereco}`);
      });

      // Se necessário, traçar uma rota (exemplo simples: linha entre os pontos)
      const latLngs = this.entregas.map(entrega => [entrega.latitude, entrega.longitude]);
      L.polyline(latLngs, { color: 'blue' }).addTo(map);
    }
  }
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 400px;
}
</style>
