import api from './api';

export const lessonService = {
  // Listar todas as aulas
  getAll: async () => {
    try {
      const response = await api.get('/aulas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar aulas:', error);
      throw error;
    }
  },

  // Buscar aula por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/aulas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar aula ${id}:`, error);
      throw error;
    }
  },

  // Criar nova aula
  create: async (aulaData) => {
    try {
      const response = await api.post('/aulas', aulaData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar aula:', error);
      throw error;
    }
  },

  // Atualizar aula
  update: async (id, aulaData) => {
    try {
      const response = await api.put(`/aulas/${id}`, aulaData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar aula ${id}:`, error);
      throw error;
    }
  },

  // Deletar aula
  delete: async (id) => {
    try {
      await api.delete(`/aulas/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar aula ${id}:`, error);
      throw error;
    }
  }
};