<template>
    <div class="modal">
        <h2>Nova Movimentação</h2>
        <form @submit.prevent="salvar">
            <div class="form-group">
                <label>Produto</label>
                <select v-model="movimento.produtoId" required>
                    <option v-for="produto in produtos" :key="produto.id" :value="produto.id">
                        {{ produto.descricao }} (Estoque: {{ produto.quantidadeEstoque }})
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label>Tipo</label>
                <select v-model="movimento.tipo" required>
                    <option value="ENTRADA">Entrada</option>
                    <option value="SAIDA">Saída</option>
                </select>
            </div>
            <div class="form-group">
                <label>Valor de Venda</label>
                <input v-model="movimento.valorVenda" type="number" step="0.01" required>
            </div>
            <div class="form-group">
                <label>Quantidade</label>
                <input v-model="movimento.quantidade" type="number" required>
            </div>
            <button type="submit">Salvar</button>
            <button type="button" @click="cancelar">Cancelar</button>
        </form>
    </div>
</template>

<script>
export default {
    props: {
        produtos: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            movimento: {
                produtoId: null,
                tipo: 'ENTRADA',
                valorVenda: 0,
                quantidade: 1
            }
        }
    },
    methods: {
        salvar() {
            this.$emit('save', { ...this.movimento })
        },
        cancelar() {
            this.$emit('cancel')
        }
    }
}
</script>