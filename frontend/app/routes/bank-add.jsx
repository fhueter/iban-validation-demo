import { useState } from 'react'
import { bankService } from '../services/api.js'

export default function BankAdd() {
	const [createResult, setCreateResult] = useState({});
	const [error, setError] = useState('');

	const handleSubmit = (e) => {
		e.preventDefault();

		setCreateResult({});
		setError('');

		const formData = new FormData(e.target);
		bankService.createBank(formData).then((res) => {
			setCreateResult(res.data);
		}).catch((err) => {
			if (err.status === 409) {
				setError('Eine Bank mit dieser BLZ existiert bereits.')
			} else if (err.status === 400) {
				setError('Ungültige Eingaben')
			} else {
				setError('Unbekannter Fehler')
			}
		});
	};

	return (
		<div className="flex-1 flex flex-col items-center gap-16 min-h-0">
			<header className="flex flex-col items-center gap-9">
				<h1>
					Bank hinzufügen
				</h1>
			</header>
			<div className="max-w-[700px] w-full space-y-6 px-4">
				<div className="max-w-md mx-auto p-6 bg-white rounded-lg shadow-md">
					<form onSubmit={handleSubmit} className="space-y-4">
						<div>
							<label htmlFor="bankCodeInput" className="block text-sm font-medium text-gray-700 mb-2">
								Bankleitzahl
							</label>
							<input
								id="bankCodeInput"
								name="bankCode"
								type="text"
								required
								className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
							/>
						</div>
						<div>
							<label htmlFor="bicInput" className="block text-sm font-medium text-gray-700 mb-2">
								BIC
							</label>
							<input
								id="bicInput"
								name="bic"
								type="text"
								required
								className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
							/>
						</div>
						<div>
							<label htmlFor="nameInput" className="block text-sm font-medium text-gray-700 mb-2">
								Name der Bank
							</label>
							<input
								id="nameInput"
								name="name"
								type="text"
								required
								className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
							/>
						</div>
						<button
							type="submit"
							className="w-full bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white font-medium py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition duration-150 ease-in-out"
						>
							Validieren
						</button>
					</form>
					{createResult.id && (
						<p className="mt-4 p-2 bg-green-100 border border-green-400 text-green-700 rounded-md">
							<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor"
								 className="size-6 inline-block">
								<path strokeLinecap="round" strokeLinejoin="round"
									  d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
							</svg>
							<span className="pl-2">Bank gespeichert (ID={createResult.id})</span>
						</p>
					) || error && (
						<p className="mt-4 p-2 bg-red-100 border border-red-400 text-red-700 rounded-md">
							<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor"
								 className="size-6 inline-block">
								<path strokeLinecap="round" strokeLinejoin="round"
									  d="M12 9v3.75m9-.75a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9 3.75h.008v.008H12v-.008Z" />
							</svg>
							<span className="pl-2">{error}</span>
						</p>
					)}
				</div>
			</div>
		</div>
	);
}
