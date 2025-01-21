import ApiService from './api.service.js';

const EntregaService = {
    createEntrega(data) {
        console.log("Teste")
        return ApiService.post('/entregas', data);
    },
    async getEntregasByStatus(status) {
        const response = await ApiService.get(`/entregas/find/${status}`);
        console.log(response)
        return response.data;
    },
    async iniciarRota(codigoRota) {
        const response = await ApiService.post(`/entregas/iniciar/${codigoRota}`);
        return response.data;
    },
    async finalizarEntregas(codigosEntregas) {
        const response = await ApiService.post(`/entregas/finalizar`, codigosEntregas);
        return response.data;
    },
    async cancelarEntregas(codigosEntregas) {
        const response = await ApiService.post(`/entregas/cancelar`, codigosEntregas);
        return response.data;
    }
}

export default EntregaService;