import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8082/api'
});

// Interceptor para configurar headers condicionalmente
api.interceptors.request.use(
  (config) => {
    // Define o header "Content-Type" apenas para POST, PUT e PATCH
    if (config.method && ['post', 'put', 'patch'].includes(config.method.toLowerCase())) {
      config.headers['Content-Type'] = 'application/json';
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Interceptor para debug
api.interceptors.response.use(
  (response) => {
    console.log('API response interceptor:', response);
    return response;
  },
  (error) => {
    console.error('API error interceptor:', error);
    return Promise.reject(error);
  }
);

export default api;
