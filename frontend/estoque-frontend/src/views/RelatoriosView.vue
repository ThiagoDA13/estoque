<template>
    <div>
        <h1>Relatórios</h1>

        <div class="report-section">
            <h2>Produtos por Tipo</h2>
            <RelatorioTipo />
        </div>

        <div class="report-section">
            <h2>Lucro por Produto</h2>
            <RelatorioLucro />
        </div>
    </div>
</template>

<script>
import RelatorioTipo from '@/components/RelatorioTipo.vue'
import RelatorioLucro from '@/components/RelatorioLucro.vue'

export default {
    components: {
        RelatorioTipo,
        RelatorioLucro
    }
}
</script>

<style scoped>
.report-section {
    margin-bottom: 40px;
}
</style>