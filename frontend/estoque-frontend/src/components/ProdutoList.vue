<template>
    <table>
        <thead>
            <tr>
                <th>Código</th>
                <th>Descrição</th>
                <th>Tipo</th>
                <th>Valor Fornecedor</th>
                <th>Quantidade</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="produto in produtos" :key="produto.id">
                <td>{{ produto.codigo }}</td>
                <td>{{ produto.descricao }}</td>
                <td>{{ produto.tipo }}</td>
                <td>{{ formatarMoeda(produto.valorFornecedor) }}</td>
                <td>{{ produto.quantidadeEstoque }}</td>
                <td>
                    <button @click="$emit('edit', produto)">Editar</button>
                    <button class="danger" @click="$emit('delete', produto.id)">Excluir</button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script>
export default {
    props: {
        produtos: {
            type: Array,
            required: true
        }
    },
    methods: {
        formatarMoeda(valor) {
            return new Intl.NumberFormat('pt-BR', {
                style: 'currency',
                currency: 'BRL'
            }).format(valor)
        }
    }
}
</script>