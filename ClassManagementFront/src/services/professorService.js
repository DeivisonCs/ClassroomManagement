import api from './api';

export const professorService = {
  // Listar todos os professores
  getAll: async () => {
    try {
      const response = await api.get('/professores');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar professores:', error);
      throw error;
    }
  },

  // Buscar professor por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/professores/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar professor ${id}:`, error);
      throw error;
    }
  },

  // Criar novo professor
  create: async (professorData) => {
    try {
      const response = await api.post('/professores', professorData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar professor:', error);
      throw error;
    }
  },

  // Atualizar professor
  update: async (id, professorData) => {
    try {
      const response = await api.put(`/professores/${id}`, professorData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar professor ${id}:`, error);
      throw error;
    }
  },

  // Deletar professor
  delete: async (id) => {
    try {
      await api.delete(`/professores/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar professor ${id}:`, error);
      throw error;
    }
  }
};