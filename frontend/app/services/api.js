import axios from 'axios';

const api = axios.create({
	headers: {
		'Content-Type': 'application/json'
	}
});

export const bankService = {
	validate: (data) => api.post('/api/bank/validate-iban', data),
};
