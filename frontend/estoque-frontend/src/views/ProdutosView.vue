<template>
    <div>
        <h1>Produtos</h1>
        <button @click="abrirFormularioNovoProduto">Adicionar Produto</button>

        <ProdutoForm v-if="showForm" :produto="produtoEdit" @save="salvarProduto" @cancel="cancelarEdicao" />

        <ProdutoList :produtos="produtos" @edit="editarProduto" @delete="deletarProduto" />
    </div>
</template>

<script>
import ProdutoList from '../components/ProdutoList.vue'
import ProdutoForm from '../components/ProdutoForm.vue'
import ProdutoService from '../services/produtoService'

export default {
    components: {
        ProdutoList,
        ProdutoForm
    },
    data() {
        return {
            produtos: [],
            showForm: false,
            produtoEdit: null
        }
    },
    mounted() {
        this.carregarProdutos()
    },
    methods: {
        abrirFormularioNovoProduto() {
            this.produtoEdit = {
                codigo: '',
                descricao: '',
                tipo: 'ELETRONICO',
                valorFornecedor: 0,
                quantidadeEstoque: 0
            }
            this.showForm = true
        },
        async carregarProdutos() {
            try {
                const response = await ProdutoService.listarTodos()
                this.produtos = response.data
                console.log(this.produtos)
            } catch (error) {
                console.error('Erro ao carregar produtos:', error)
                alert('Erro ao carregar produtos')
            }
        },
        editarProduto(produto) {
            this.produtoEdit = { ...produto }
            this.showForm = true
        },
        async salvarProduto(produto) {
            try {
                if (produto.id) {
                    await ProdutoService.atualizar(produto.id, produto)
                } else {
                    await ProdutoService.criar(produto)
                }
                this.showForm = false
                this.produtoEdit = null
                this.carregarProdutos()
            } catch (error) {
                console.error('Erro ao salvar produto:', error)
                alert('Erro ao salvar produto: ' + error.message)
            }
        },
        async deletarProduto(id) {
            if (confirm('Tem certeza que deseja excluir este produto?')) {
                try {
                    console.log(id)
                    await ProdutoService.deletar(id)
                    this.carregarProdutos()
                } catch (error) {
                    console.error('Erro ao excluir produto:', error)
                    alert('Erro ao excluir produto pois já foram feitas movimentações de entrada/saída')
                }
            }
        },
        cancelarEdicao() {
            this.showForm = false
            this.produtoEdit = null
        }
    }
}
</script>