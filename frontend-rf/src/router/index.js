import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import EntregaForm from '../views/EntregaForm.vue'

const routes = [
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/cadastrar-entrega',
    name: 'EntregaForm',
    component: EntregaForm
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
