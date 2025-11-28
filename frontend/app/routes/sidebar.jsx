import { Link } from 'react-router'

export function Sidebar() {
	return (
		<nav style={{
			position: "fixed",
			top: "60px",
			left: 0,
			width: "200px",
			height: "calc(100% - 60px)",
			backgroundColor: "#f0f0f0",
			padding: "20px",
			boxSizing: "border-box"
		}}>
			<ul style={{ listStyle: "none", padding: 0 }}>
				<li><Link to="/">IBAN validieren</Link></li>
				<li><Link to="/bank/add">Bank hinzufügen</Link></li>
			</ul>
		</nav>
	);
}