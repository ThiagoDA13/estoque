<template>
    <div class="modal">
        <h2>{{ produto && produto.id ? 'Editar' : 'Adicionar' }} Produto</h2>
        <form @submit.prevent="salvar">
            <div class="form-group">
                <label>Código</label>
                <input v-model="produto.codigo" required>
            </div>

            <div class="form-group">
                <label>Descrição</label>
                <input v-model="produto.descricao" required>
            </div>

            <div class="form-group">
                <label>Tipo</label>
                <select v-model="produto.tipo" required>
                    <option value="ELETRONICO">Eletrônico</option>
                    <option value="ELETRODOMESTICO">Eletrodoméstico</option>
                    <option value="MOVEL">Móvel</option>
                </select>
            </div>

            <div class="form-group">
                <label>Valor Fornecedor</label>
                <input v-model="produto.valorFornecedor" type="number" step="0.01" required>
            </div>

            <div class="form-group">
                <label>Quantidade em Estoque</label>
                <input v-model="produto.quantidadeEstoque" type="number" required>
            </div>

            <button type="submit">Salvar</button>
            <button type="button" @click="cancelar">Cancelar</button>
        </form>
    </div>
</template>

<script>
export default {
    props: {
        produto: {
            type: Object,
            default: () => ({
                codigo: '',
                descricao: '',
                tipo: 'ELETRONICO',
                valorFornecedor: 0,
                quantidadeEstoque: 0
            })
        }
    },
    methods: {
        salvar() {
            this.$emit('save', { ...this.produto })
        },
        cancelar() {
            this.$emit('cancel')
        }
    }
}
</script>