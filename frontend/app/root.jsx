import { isRouteErrorResponse, Outlet, } from "react-router"
import { Header } from './routes/header.jsx'
import { Sidebar } from './routes/sidebar.jsx'
import "./app.css"

export const links = () => [
	{ rel: "preconnect", href: "https://fonts.googleapis.com" },
	{
		rel: "preconnect",
		href: "https://fonts.gstatic.com",
		crossOrigin: "anonymous",
	},
];

export default function App() {
	return (
		<>
			<title>IBAN validation demo</title>
			<Header />
			<Sidebar />
			<main style={{
				marginTop: "60px",
				marginLeft: "200px",
				padding: "20px",
				minHeight: "calc(100vh - 60px)"
			}}>
				<Outlet />
			</main>
		</>
	);
}

export function ErrorBoundary({ error }) {
	let message = "Oops!";
	let details = "An unexpected error occurred.";
	let stack;

	if (isRouteErrorResponse(error)) {
		message = error.status === 404 ? "404" : "Error";
		details =
			error.status === 404
				? "The requested page could not be found."
				: error.statusText || details;
	} else if (import.meta.env.DEV && error && error instanceof Error) {
		details = error.message;
		stack = error.stack;
	}

	return (
		<main className="pt-16 p-4 container mx-auto">
			<h1>{message}</h1>
			<p>{details}</p>
			{stack && (
				<pre className="w-full p-4 overflow-x-auto">
          <code>{stack}</code>
        </pre>
			)}
		</main>
	);
}
