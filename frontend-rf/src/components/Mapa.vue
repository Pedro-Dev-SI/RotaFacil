<template>
  <div class="map-container">
    <l-map class="map" :zoom="zoom" :center="center" :zoom-control="true" :min-zoom="5" :max-zoom="18">
      <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
      <l-marker :lat-lng="markerLatLng"></l-marker>
      <l-marker
        v-for="entrega in entregas"
        :key="entrega.id"
        :lat-lng="[entrega.latitude, entrega.longitude]"
        :icon="getIcon(entrega.id)"
      >
        <l-popup>{{ entrega.nomeCliente }} {{entrega.codigo}}</l-popup>
      </l-marker>
    </l-map>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LPopup } from 'vue3-leaflet';
import L from 'leaflet';

export default {
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
  },
  props: {
    entregas: {
      type: Array,
      required: true,
    },
    origem: {
      type: Object,
      required: true,
    },
  },
  mounted() {
    console.log('Entregas:', this.entregas);
    console.log('Origem:', this.origem);
  },
  data() {
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 18,
      center: [this.origem.latitude, this.origem.longitude],
      markerLatLng: [this.origem.latitude, this.origem.longitude],
    };
  },
  methods: {
    getIcon(id) {
      const colors = ['red', 'blue', 'green', 'orange', 'purple'];
      const color = colors[id % colors.length];
      return L.icon({
        iconUrl: `http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|abcdef&chf=a,s,ee00FFFF`,
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41],
      });
    },
  },
};
</script>

<style scoped>
.map-container {
  width: 80%;
  margin: 20px auto;
  height: 500px;
  border-radius: 15px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  overflow: hidden;
}

.map {
  width: 100%;
  height: 100%;
  border-radius: 15px;
}
</style>
