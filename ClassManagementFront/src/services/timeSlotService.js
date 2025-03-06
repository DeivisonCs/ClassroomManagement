import api from './api';

export const timeSlotService = {
  getAll: async () => {
    try {
      const response = await api.get('/horarios');
      return response.data;
    } catch (error) {
      console.error('Error fetching time slots:', error);
      throw error;
    }
  }
};