import api from './api';

export const classScheduleService = {
  // Listar todas as associações turma-sala
  getAll: async () => {
    try {
      const response = await api.get('/turmas-salas');
      return response.data;
    } catch (error) {
      console.error('Error fetching class schedules:', error);
      throw error;
    }
  },

  // Buscar por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/turmas-salas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching schedule with ID ${id}:`, error);
      throw error;
    }
  },

  // Criar nova associação turma-sala
  create: async (scheduleData) => {
    try {
      const response = await api.post('/turmas-salas', scheduleData);
      return response.data;
    } catch (error) {
      console.error('Error creating class schedule:', error);
      throw error;
    }
  },

  // Atualizar associação
  update: async (id, scheduleData) => {
    try {
      const response = await api.put(`/turmas-salas/${id}`, scheduleData);
      return response.data;
    } catch (error) {
      console.error(`Error updating schedule ${id}:`, error);
      throw error;
    }
  },

  // Deletar associação
  delete: async (id) => {
    try {
      await api.delete(`/turmas-salas/${id}`);
      return true;
    } catch (error) {
      console.error(`Error deleting schedule ${id}:`, error);
      throw error;
    }
  }
};