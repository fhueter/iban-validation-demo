import { Welcome } from "../welcome/welcome";

export function meta() {
  return [
    { title: "IBAN validation demo" },
    { name: "description", content: "Eine Demo-App, mit der man IBANs validieren kann" },
  ];
}

export default function Home() {
  return <Welcome />;
}
