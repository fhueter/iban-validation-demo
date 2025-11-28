import axios from 'axios';

const api = axios.create({
	headers: {
		'Content-Type': 'application/json'
	}
});

export const bankService = {
	validateIban: (data) => api.post('/api/bank/validate-iban', data),
	createBank: (data) => api.post('/api/bank/create', data),
};
