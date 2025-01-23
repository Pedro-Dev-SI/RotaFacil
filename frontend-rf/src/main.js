import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap-icons/font/bootstrap-icons.css';
import BootstrapVue3 from 'bootstrap-vue-3';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import Notifications from '@kyvg/vue3-notification';
import VueTheMask from 'vue-the-mask';
import ApiService from './services/api.service';

ApiService.init('http://localhost:8080/api');

const app = createApp(App);
app.use(router);
app.use(BootstrapVue3);
app.use(Notifications);
app.use(VueTheMask);
app.config.globalProperties.$apiService = ApiService;
app.mount('#app');