export function Header() {
	return (
		<header style={{
			position: "fixed",
			top: 0,
			left: 0,
			right: 0,
			height: "60px",
			backgroundColor: "#282c34",
			color: "white",
			display: "flex",
			alignItems: "center",
			padding: "0 20px",
			zIndex: 1000
		}}>
			<h1>IBAN-Validation-Demo</h1>
		</header>
	);
}
