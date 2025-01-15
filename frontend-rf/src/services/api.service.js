import axios from 'axios';

const ApiService = {
  // Inicializa a URL base da API
  init(baseURL) {
    axios.defaults.baseURL = baseURL;
  },

  // Adiciona um interceptor para lidar com erros (opcional)
  setErrorHandler(errorHandler) {
    axios.interceptors.response.use(
      (response) => response,
      (error) => {
        if (errorHandler) {
          errorHandler(error);
        }
        return Promise.reject(error);
      }
    );
  },

  // Faz um GET genérico
  get(resource, params = {}) {
    return axios.get(resource, { params });
  },

  // Faz um POST genérico
  post(resource, data) {
    return axios.post(resource, data);
  },

  // Faz um PUT genérico
  update(resource, data) {
    return axios.put(resource, data);
  },

  // Faz um DELETE genérico
  delete(resource) {
    return axios.delete(resource);
  },
};

export default ApiService;
