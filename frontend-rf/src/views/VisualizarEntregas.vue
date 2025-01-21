<template>
    <div>
        <h2>Entregas</h2>
    </div>
    <div class="delivery-tables">
        <TabelaEntregasVue 
            :status="StatusEntrega.CRIADA"
            :items="entregasCriadas"
            @iniciar-rota="iniciarRota"
            @editar="editarEntrega"
            @excluir="excluirEntrega">
        </TabelaEntregasVue>
        <TabelaEntregasVue 
            :status="StatusEntrega.EM_ANDAMENTO"
            :items="entregasEmAndamento"
            @editar="editarEntrega"
            @cancelar="cancelarEntrega"
            @finalizar-entrega="finalizarEntrega">
        </TabelaEntregasVue>
        <TabelaEntregasVue :status="StatusEntrega.FINALIZADA" :items="entregasFinalizadas"></TabelaEntregasVue>
        <TabelaEntregasVue :status="StatusEntrega.CANCELADA" :items="entregasCanceladas"></TabelaEntregasVue>
    </div>
</template>

<script>
import TabelaEntregasVue from '@/components/TabelaEntregas.vue'
import StatusEntrega from '@/enums/StatusEntrega.js'
import EntregaService from '@/services/entrega.service'

export default {
    name: 'VisualizarEntregas',
    components: {
        TabelaEntregasVue,
    },
    data() {
        return {
            StatusEntrega,
            entregasCriadas: [],
            entregasEmAndamento: [],
            entregasFinalizadas: [],
            entregasCanceladas: []
        }
    },
    async mounted() {
        await this.fetchEntregas();
    },
    methods: {

        async fetchEntregas() {

            const formatarStatus = (status) => status.replace(/\s/g, "_");

            this.entregasCriadas = await EntregaService.getEntregasByStatus(StatusEntrega.CRIADA);
            this.entregasEmAndamento = await EntregaService.getEntregasByStatus(formatarStatus(StatusEntrega.EM_ANDAMENTO));
            this.entregasFinalizadas = await EntregaService.getEntregasByStatus(StatusEntrega.FINALIZADA);
            this.entregasCanceladas = await EntregaService.getEntregasByStatus(StatusEntrega.CANCELADA);
        },
        async iniciarRota(entrega) {
            await EntregaService.iniciarRota(entrega.codigoRota);
            await this.fetchEntregas();
        },
        async editarEntrega(entrega) {
        },
        async excluirEntrega(entrega) {
            
        },
        async cancelarEntrega(entrega) {
            const cods = [];
            cods.push(entrega.codigo);
            await EntregaService.cancelarEntregas(cods);
            await this.fetchEntregas();
        },
        async finalizarEntrega(entrega) {
            const cods = [];
            cods.push(entrega.codigo);
            await EntregaService.finalizarEntregas(cods);
            await this.fetchEntregas();
        }

        
    }
}
</script>

<style lang="scss" scoped>
    h2 {
        color: #5AC8FA;
        text-align: left;
        margin-top: 40px;
        margin-left: 65px;
    }

    .delivery-tables {
        padding: 20px;
        width: 100%;
        display: flex;
        flex-wrap: wrap;
    }
</style>