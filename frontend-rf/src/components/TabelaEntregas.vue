<template>
    <div class="box-tables">
        <h4>{{ status }}</h4>
        <div class="table-scroll-container">
            <b-table
                striped
                hover
                :items="items"
                :fields="fields"
                class="custom-table"
                empty-text="NÃO POSSUI ENTREGAS NESTE STATUS"
            >
                <!-- Slot para personalizar a coluna "ações" -->
                <template #cell(acoes)="data">
                    <b-dropdown
                        right
                        variant="link"
                        class="dropdown-icon"
                        no-caret
                    >
                        <template #button-content>
                            <i class="bi bi-caret-down-fill"></i>
                        </template>
                        <b-dropdown-item v-if="statusQuePodemEditar.includes(status)" @click="$emit('editar', data.item)">Editar</b-dropdown-item>
                        <b-dropdown-item @click="visualizar(data.item)">Visualizar</b-dropdown-item>
                        <b-dropdown-item v-if="!statusQueNaoPodemCancelar.includes(status)" @click="$emit('cancelar', data.item)">Cancelar</b-dropdown-item>
                        <b-dropdown-item v-if="this.status === 'CRIADA'" @click="$emit('excluir', data.item)">Excluir</b-dropdown-item>
                        <b-dropdown-item 
                            v-if="this.status === 'CRIADA'" 
                            @click="$emit('iniciar-rota', data.item)"
                        >
                            Iniciar Rota
                        </b-dropdown-item>
                        <b-dropdown-item 
                            v-if="this.status === 'EM ANDAMENTO'" 
                            @click="$emit('finalizar-entrega', data.item)"
                        >
                            Finalizar
                        </b-dropdown-item>
                    </b-dropdown>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>
import StatusEntrega from '@/enums/StatusEntrega';
export default {
    name: 'TabelaEntregas',
    props: {
        status: {
            type: String,
            required: true
        },
        items: {
            type: Array,
            default: () => []
        }
    },
    data() {
        return {
            fields: [
                { key: 'codigoRota', label: 'Rota', sortable: true },
                { key: 'codigo', label: 'Código da entrega', sortable: true },
                { key: 'nomeCliente', label: 'Nome do Cliente', sortable: true },
                { key: 'acoes', label: '', sortable: false }
            ],
            statusQueNaoPodemCancelar: [StatusEntrega.CRIADA, StatusEntrega.FINALIZADA, StatusEntrega.CANCELADA],
            statusQuePodemEditar: [StatusEntrega.CRIADA, StatusEntrega.EM_ANDAMENTO]
        }
    },
    async mounted(){
    },
    methods: {
        visualizar(item) {
            this.$router.push({ name: 'VisualizarRota', params: { codigoRota: item.codigoRota } });
        },
    }
}
</script>

<style lang="scss">
.box-tables {
    width: 45%;
    margin: auto;
    margin-bottom: 50px;
    padding: 20px;
    border-radius: 15px;
    background-color: #f9f9f9;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    border: 1px solid #e0e0e0;
}

h4 {
    color: #5AC8FA;
    text-align: center;
    margin-bottom: 20px;
}

.table-scroll-container {
    max-height: 300px; /* Limita a altura total da tabela (aproximadamente 6 itens) */
    overflow-y: auto; /* Ativa o scroll vertical */
    border-radius: 10px;
}

.custom-table {
    width: 100%;
    background-color: white;
    border-collapse: separate;
    border-spacing: 0;
}

.custom-table thead th {
    position: sticky; /* Mantém o cabeçalho fixo no topo */
    top: 0;
    z-index: 2;
    background-color: #32BC9B; /* Cor do cabeçalho */
    color: white;
    text-align: center;
    padding: 10px;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.table td {
    text-align: center;
    vertical-align: middle;
    border: none;
    padding: 8px;
}

.custom-table tbody tr:hover {
    background-color: #f2f2f2;
}

.custom-table tbody td {
    text-align: center;
    padding: 8px;
}

.dropdown-icon {
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
}

i {
    font-size: 1.5rem;
    color: #5AC8FA;
    transition: transform 0.2s ease, color 0.2s ease;
}

i:hover {
    transform: scale(1.1);
    color: #218b71;
}
</style>