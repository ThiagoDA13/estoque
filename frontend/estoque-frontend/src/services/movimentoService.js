import api from "./api";

const MovimentoService = {
  criar: (movimento) => api.post("/movimentos", movimento),
  listarPorProduto: (produtoId) => api.get(`/movimentos/produto/${produtoId}`),
  listarTodos: () => api.get("/movimentos"),
  consultarPorTipo: (tipo) => api.get(`/movimentos/tipo/${tipo}`),
  calcularLucroPorProduto: () => api.get("/movimentos/lucro"),
};

export default MovimentoService;
