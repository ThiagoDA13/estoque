import { createRouter, createWebHistory } from "vue-router";
import ProdutosView from "../views/ProdutosView.vue";
import MovimentosView from "../views/MovimentosView.vue";
import RelatoriosView from "../views/RelatoriosView.vue";

const routes = [
  {
    path: "/",
    redirect: "/produtos",
  },
  {
    path: "/produtos",
    name: "produtos",
    component: ProdutosView,
  },
  {
    path: "/movimentos",
    name: "movimentos",
    component: MovimentosView,
  },
  {
    path: "/relatorios",
    name: "relatorios",
    component: RelatoriosView,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
