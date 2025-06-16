<template>
    <div>
        <h1>Movimentações de Estoque</h1>
        <button @click="showForm = true">Nova Movimentação</button>
        <MovimentoForm v-if="showForm" :produtos="produtos" @save="salvarMovimento" @cancel="cancelarEdicao" />


        <h4>Histórico de movimentações por produto</h4>

        <div class="filtros">
            <select v-model="produtoSelecionado" @change="carregarMovimentos">
                <option v-for="produto in produtos" :key="produto.id" :value="produto.id">
                    {{ produto.descricao }} ({{ produto.codigo }})
                </option>
            </select>
        </div>


        <MovimentoList :movimentos="movimentos" />
    </div>
</template>

<script>
import MovimentoList from '@/components/MovimentoList.vue'
import MovimentoForm from '@/components/MovimentoForm.vue'
import MovimentoService from '@/services/movimentoService'
import ProdutoService from '@/services/produtoService'

export default {
    components: {
        MovimentoList,
        MovimentoForm
    },
    data() {
        return {
            movimentos: [],
            produtos: [],
            produtoSelecionado: '',
            showForm: false
        }
    },
    async mounted() {
        await this.carregarProdutos()
        if (this.produtos.length > 0) {
            this.produtoSelecionado = this.produtos[0].id
            this.carregarMovimentos()
        }
    },
    methods: {
        async carregarMovimentos() {

            let response
            if (this.produtoSelecionado) {
                response = await MovimentoService.listarPorProduto(this.produtoSelecionado)
            }
            this.movimentos = response.data

        },
        async carregarProdutos() {
            try {
                const response = await ProdutoService.listarTodos()
                this.produtos = response.data
            } catch (error) {
                console.error('Erro ao carregar produtos:', error)
                alert('Erro ao carregar produtos')
            }
        },
        async salvarMovimento(movimento) {
            try {
                await MovimentoService.criar(movimento)
                this.showForm = false
                this.carregarMovimentos()
                this.carregarProdutos()
            } catch (error) {
                console.error('Erro ao salvar movimento:', error)
                alert('Erro ao salvar movimento: ' + error.message)
            }
        },
        cancelarEdicao() {
            this.showForm = false
        }
    }
}
</script>

<style scoped>
.filtros {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
    align-items: center;
}

select {
    padding: 8px;
    border-radius: 4px;
    border: 1px solid #ddd;
    min-width: 200px;
}

button {
    padding: 8px 16px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #369f6b;
}
</style>