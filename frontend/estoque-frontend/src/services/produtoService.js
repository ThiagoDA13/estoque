import api from "./api";

const ProdutoService = {
  listarTodos: () => api.get("/produtos"),
  buscarPorId: (id) => api.get(`/produtos/${id}`),
  criar: (produto) => api.post("/produtos", produto),
  atualizar: (id, produto) => api.put(`/produtos/${id}`, produto),
  deletar: (id) => api.delete(`/produtos/${id}`),
};

export default ProdutoService;
