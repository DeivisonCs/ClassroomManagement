import api from './api';

export const classroomService = {
  // Listar todas as salas
  getAll: async () => {
    try {
      // Usando '/salas' como endpoint - verifique se este é o endpoint correto
      const response = await api.get('/salas');
      console.log('API response raw:', response);
      
      // Certifique-se de retornar o response.data, não o response completo
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar salas:', error);
      throw error;
    }
  },

  // Buscar sala por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/salas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar sala ${id}:`, error);
      throw error;
    }
  },

  // Criar nova sala
  create: async (salaData) => {
    try {
      const response = await api.post('/salas', salaData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar sala:', error);
      throw error;
    }
  },

  // Atualizar sala
  update: async (id, salaData) => {
    try {
      const response = await api.put(`/salas/${id}`, salaData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar sala ${id}:`, error);
      throw error;
    }
  },

  // Deletar sala
  delete: async (id) => {
    try {
      await api.delete(`/salas/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar sala ${id}:`, error);
      throw error;
    }
  }
};