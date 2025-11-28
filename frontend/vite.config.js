import { reactRouter } from "@react-router/dev/vite";
import tailwindcss from "@tailwindcss/vite";
import { defineConfig } from "vite";

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
