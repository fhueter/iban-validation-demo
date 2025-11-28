import { defineConfig } from "vite";
import { reactRouter } from "@react-router/dev/vite";
import tailwindcss from "@tailwindcss/vite";

export default defineConfig({
	plugins: [
		reactRouter(),
		tailwindcss()
	],

	server: {
		proxy: {
			'/api': {
				target: 'http://localhost:8080',
				ignorePath: false,
				changeOrigin: true,
				autoRewrite: true,
			}
		}
	},
});
