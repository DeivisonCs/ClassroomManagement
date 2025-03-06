import api from './api';

export const weekdayService = {
  getAll: async () => {
    try {
      const response = await api.get('/dias-semana');
      return response.data;
    } catch (error) {
      console.error('Error fetching weekdays:', error);
      throw error;
    }
  }
};