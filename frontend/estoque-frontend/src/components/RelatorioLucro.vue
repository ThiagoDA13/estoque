<template>
    <table>
        <thead>
            <tr>
                <th>Produto</th>
                <th>Quantidade Saída</th>
                <th>Tipo</th>
                <th>Lucro Total</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in dados" :key="item.produtoId">
                <td>{{ item.produtoDescricao }}</td>
                <td>{{ item.quantidadeSaida }}</td>
                <td>{{ item.tipoProduto }}</td>
                <td>{{ formatarMoeda(item.lucroTotal) }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>
import MovimentoService from '@/services/movimentoService'

export default {
    data() {
        return {
            dados: []
        }
    },
    mounted() {
        this.carregarDados()
    },
    methods: {
        async carregarDados() {
            try {
                const response = await MovimentoService.calcularLucroPorProduto()
                this.dados = response.data
            } catch (error) {
                console.error('Erro ao carregar relatório de lucro:', error)
                alert('Erro ao carregar relatório')
            }
        },
        formatarMoeda(valor) {
            return new Intl.NumberFormat('pt-BR', {
                style: 'currency',
                currency: 'BRL'
            }).format(valor)
        }
    }
}
</script>