import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
const BASE_ACCOUNT_ENDPOINT = '/account';

const LOGIN_ENDPOINT = '/auth/login';

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const getAuthHeader = () => ({
  headers: {
    'Authorization': `Bearer ${sessionStorage.getItem('token')}`
  }
});

export const authUser = async (user, password) => {
  try {
    const response = await api.post(LOGIN_ENDPOINT, {
        login: user,
        password: password
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createUser = async (occupationId, name, email, cpf, registration) => {
  try {
    const response = await api.post(BASE_ACCOUNT_ENDPOINT, {
        occupationId: occupationId,
        name: name,
        email: email,
        cpf: cpf,
        registration: registration
    }, 
    getAuthHeader());

    return response.data;
  } catch (error) {
    throw error;
  }
};

export const listOccupations = async () => {
  try {
    const response = await api.get(BASE_ACCOUNT_ENDPOINT + '/occupations',
    getAuthHeader());

    return response.data;
  } catch (error) {
    throw error;
  }
};

export const listUsers = async () => {
  try {
    const response = await api.get(BASE_ACCOUNT_ENDPOINT,
    getAuthHeader());

    return response.data;
  } catch (error) {
    throw error;
  }
}

export const deleteUser = async (userId) => {
  try {
    const response = await api.delete(BASE_ACCOUNT_ENDPOINT + '/' + userId,
    getAuthHeader());

    return response.data;
  } catch (error) {
    throw error;
  }
};