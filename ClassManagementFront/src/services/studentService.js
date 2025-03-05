import api from './api';

export const studentService = {
  // Listar todos os alunos
  getAll: async () => {
    try {
      const response = await api.get('/alunos');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar alunos:', error);
      throw error;
    }
  },

  // Buscar aluno por ID ou matrícula
  getById: async (id) => {
    try {
      const response = await api.get(`/alunos/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar aluno ${id}:`, error);
      throw error;
    }
  },

  // Criar novo aluno
  create: async (alunoData) => {
    try {
      const response = await api.post('/alunos', alunoData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar aluno:', error);
      throw error;
    }
  },

  // Atualizar aluno
  update: async (id, alunoData) => {
    try {
      const response = await api.put(`/alunos/${id}`, alunoData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar aluno ${id}:`, error);
      throw error;
    }
  },

  // Deletar aluno
  delete: async (id) => {
    try {
      await api.delete(`/alunos/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar aluno ${id}:`, error);
      throw error;
    }
  }
};