<template>
    <div>
        <select v-model="tipoSelecionado" @change="carregarDados">
            <option value="ELETRONICO">Eletrônico</option>
            <option value="ELETRODOMESTICO">Eletrodoméstico</option>
            <option value="MOVEL">Móvel</option>
        </select>

        <table>
            <thead>
                <tr>
                    <th>Tipo</th>
                    <th>Quantidade Saída</th>
                    <th>Quantidade Disponível</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in dados" :key="item.tipo">
                    <td>{{ item.tipo }}</td>
                    <td>{{ item.quantidadeSaida }}</td>
                    <td>{{ item.quantidadeDisponivel }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import MovimentoService from '@/services/movimentoService'

export default {
    data() {
        return {
            tipoSelecionado: 'ELETRONICO',
            dados: []
        }
    },
    mounted() {
        this.carregarDados()
    },
    methods: {
        async carregarDados() {
            try {
                const response = await MovimentoService.consultarPorTipo(this.tipoSelecionado)
                this.dados = response.data
            } catch (error) {
                console.error('Erro ao carregar relatório por tipo:', error)
                alert('Erro ao carregar relatório')
            }
        }
    }
}
</script>