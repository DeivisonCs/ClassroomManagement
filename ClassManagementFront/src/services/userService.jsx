import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
const BASE_ACCOUNT_ENDPOINT = '/account';

const LOGIN_ENDPOINT = '/auth/login';
const REGISTER_ENDPOINT = BASE_ACCOUNT_ENDPOINT+'/register';

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
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

export const createUser = async (occupation, name, email, cpf, registration) => {
  try {
    const response = await api.post(REGISTER_ENDPOINT, {
        occupation: occupation,
        name: name,
        email: email,
        cpf: cpf,
        humanReadableId: registration
    }, 
    {
      headers: {
          'Authorization': `Bearer ${sessionStorage.getItem('token')}`
      }
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};