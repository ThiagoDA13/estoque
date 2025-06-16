<template>
    <table>
        <thead>
            <tr>
                <th>Produto</th>
                <th>Tipo</th>
                <th>Valor Venda</th>
                <th>Quantidade</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="movimento in movimentos" :key="movimento.id">
                <td>{{ movimento.produtoDescricao }}</td>
                <td>{{ movimento.tipo }}</td>
                <td>{{ formatarMoeda(movimento.valorVenda) }}</td>
                <td>{{ movimento.quantidade }}</td>
                <td>{{ formatarData(movimento.dataMovimentacao) }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>
export default {
    props: {
        movimentos: {
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
        },
        formatarData(data) {
            return new Date(data).toLocaleString()
        }
    }
}
</script>