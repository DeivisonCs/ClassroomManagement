import api from './api';

export const subjectService = {
  // Listar todas as disciplinas
  getAll: async () => {
    try {
      const response = await api.get('/disciplinas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar disciplinas:', error);
      throw error;
    }
  },

  // Buscar disciplina por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/disciplinas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar disciplina ${id}:`, error);
      throw error;
    }
  },

  // Criar nova disciplina
  create: async (disciplinaData) => {
    try {
      const response = await api.post('/disciplinas', disciplinaData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar disciplina:', error);
      throw error;
    }
  },

  // Atualizar disciplina
  update: async (id, disciplinaData) => {
    try {
      const response = await api.put(`/disciplinas/${id}`, disciplinaData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar disciplina ${id}:`, error);
      throw error;
    }
  },

  // Deletar disciplina
  delete: async (id) => {
    try {
      await api.delete(`/disciplinas/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar disciplina ${id}:`, error);
      throw error;
    }
  }
};