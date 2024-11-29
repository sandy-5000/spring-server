import { createRouter, createWebHistory } from "vue-router"

import Home from "/src/components/Home.vue"
import Framework from "/src/components/Framework.vue"

const routes = [
  { path: "/", component: Home },
  { path: "/home", component: Home },
  { path: "/framework", component: Framework },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
