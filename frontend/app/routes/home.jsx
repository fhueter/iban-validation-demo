import { bankService } from '../services/api.js'
import { useState } from 'react'

export default function Home() {
	const [validationResult, setValidationResult] = useState({});

	const handleSubmit = (e) => {
		e.preventDefault();
		const formData = new FormData(e.target);
		bankService.validate(formData).then((res) => {
			setValidationResult(res.data);
		});
	};

	return (
		<div className="flex-1 flex flex-col items-center gap-16 min-h-0">
			<header className="flex flex-col items-center gap-9">
				<h1>
					IBAN validieren
				</h1>
			</header>
			<div className="max-w-[700px] w-full space-y-6 px-4">
				<div className="max-w-md mx-auto p-6 bg-white rounded-lg shadow-md">
					<form onSubmit={handleSubmit} className="space-y-4">
						<div>
							<label htmlFor="ibanInput" className="block text-sm font-medium text-gray-700 mb-2">
								IBAN
							</label>
							<input
								id="ibanInput"
								name="iban"
								type="text"
								required
								className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
								placeholder="DE12 3456 ..."
							/>
						</div>
						<button
							type="submit"
							className="w-full bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white font-medium py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition duration-150 ease-in-out"
						>
							Validieren
						</button>
					</form>
					{validationResult.valid && (
						<p className="mt-4 p-2 bg-green-100 border border-green-400 text-green-700 rounded-md">
							<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor"
								 className="size-6 inline-block">
								<path strokeLinecap="round" strokeLinejoin="round"
									  d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
							</svg>
							{validationResult.bankName?.length > 0 && (
								<span className="pl-2">{validationResult.bankName} ({validationResult.bic})</span>
							) || (
								<span className="pl-2">Bankbestimmung nur für deutsche IBANs</span>
							)}
						</p>
					)}
					{validationResult.valid === false && (
						<p className="mt-4 p-2 bg-red-100 border border-red-400 text-red-700 rounded-md">
							<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor"
								 className="size-6 inline-block">
								<path strokeLinecap="round" strokeLinejoin="round"
									  d="M12 9v3.75m9-.75a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9 3.75h.008v.008H12v-.008Z" />
							</svg>
							<span className="pl-2">Ungültige IBAN</span>
						</p>
					)}
				</div>
			</div>
		</div>
	);
}
