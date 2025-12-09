import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor for debugging
api.interceptors.request.use(
  (config) => {
    console.log('API Request:', config.method?.toUpperCase(), config.url, config.data);
    return config;
  },
  (error) => {
    console.error('API Request Error:', error);
    return Promise.reject(error);
  }
);

// Add response interceptor for error handling
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error('API Response Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

// Groups API
export const groupsAPI = {
  getAll: () => api.get('/group'),
  getById: (id) => api.get(`/group/id/${id}`),
  getByName: (name) => api.get(`/group/name/${name}`),
  create: (data) => api.post('/group', data),
  update: (data) => api.put('/group', data),
  deleteById: (id) => api.delete(`/group/id/${id}`),
  deleteByName: (name) => api.delete(`/group/name/${name}`),
};

// Teachers API
export const teachersAPI = {
  getAll: () => api.get('/teacher'),
  getById: (id) => api.get(`/teacher/id/${id}`),
  getByName: (name) => api.get(`/teacher/name/${name}`),
  create: (data) => api.post('/teacher', data),
  update: (data) => api.put('/teacher', data),
  deleteById: (id) => api.delete(`/teacher/id/${id}`),
  deleteByName: (name) => api.delete(`/teacher/name/${name}`),
};

// Subjects API
export const subjectsAPI = {
  getAll: () => api.get('/subject'),
  getById: (id) => api.get(`/subject/id/${id}`),
  getByName: (name) => api.get(`/subject/name/${name}`),
  create: (data) => api.post('/subject', data),
  update: (data) => api.put('/subject', data),
  deleteById: (id) => api.delete(`/subject/id/${id}`),
  deleteByName: (name) => api.delete(`/subject/name/${name}`),
};

// Schedules API
export const schedulesAPI = {
  getAll: () => api.get('/schedule'),
  getById: (id) => api.get(`/schedule/id/${id}`),
  getByGroupName: (groupName) => api.get(`/schedule/group/${groupName}`),
  getBySubjectName: (subjectName) => api.get(`/schedule/subject/${subjectName}`),
  getByTeacherName: (teacherName) => api.get(`/schedule/teacher/${teacherName}`),
  create: (data) => api.post('/schedule', data),
  update: (data) => api.put('/schedule', data),
  deleteById: (id) => api.delete(`/schedule/id/${id}`),
};

export default api;
