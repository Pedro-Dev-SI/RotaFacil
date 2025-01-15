import axios from 'axios';

const CepService = {
  /**
   * Busca os dados de um CEP na API ViaCEP.
   *
   * @param {string} cep - O CEP a ser consultado (somente números).
   * @returns {Promise<Object>} - Retorna uma Promise com os dados do CEP.
   */
  async getCepData(cep) {
    try {
      // Remove caracteres não numéricos do CEP
      const sanitizedCep = cep.replace(/\D/g, '');
      if (sanitizedCep.length !== 8) {
        throw new Error('CEP inválido. Deve conter 8 dígitos.');
      }

      // Faz a requisição para a API ViaCEP
      const response = await axios.get(`https://viacep.com.br/ws/${sanitizedCep}/json/`);

      // Verifica se a API retornou um erro
      if (response.data.erro) {
        throw new Error('CEP não encontrado.');
      }

      return response.data; // Retorna os dados do CEP
    } catch (error) {
      console.error('Erro ao buscar o CEP:', error.message);
      throw error; // Propaga o erro para o chamador
    }
  },
};

export default CepService;
