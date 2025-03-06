import api from './api';

export const classService = {
  // Listar todas as turmas
  getAll: async () => {
    try {
      const response = await api.get('/turmas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar turmas:', error);
      throw error;
    }
  },

  // Buscar turma por ID
  getById: async (id) => {
    try {
      const response = await api.get(`/turmas/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao buscar turma ${id}:`, error);
      throw error;
    }
  },

  // Criar nova turma
  create: async (turmaData) => {
    try {
      const response = await api.post('/turmas', turmaData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar turma:', error);
      throw error;
    }
  },

  // Atualizar turma
  update: async (id, turmaData) => {
    try {
      const response = await api.put(`/turmas/${id}`, turmaData);
      return response.data;
    } catch (error) {
      console.error(`Erro ao atualizar turma ${id}:`, error);
      throw error;
    }
  },

  // Deletar turma
  delete: async (id) => {
    try {
      await api.delete(`/turmas/${id}`);
      return true;
    } catch (error) {
      console.error(`Erro ao deletar turma ${id}:`, error);
      throw error;
    }
  },
  
  // Adicionar aluno à turma
  addStudent: async (turmaId, alunoId) => {
    try {
      const response = await api.post(`/turmas/${turmaId}/alunos/${alunoId}`);
      return response.data;
    } catch (error) {
      console.error(`Erro ao adicionar aluno à turma:`, error);
      throw error;
    }
  },
  
  // Remover aluno da turma
  removeStudent: async (turmaId, alunoId) => {
    try {
      await api.delete(`/turmas/${turmaId}/alunos/${alunoId}`);
      return true;
    } catch (error) {
      console.error(`Erro ao remover aluno da turma:`, error);
      throw error;
    }
  }
};