/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      screens: {
        "1xl": "1350px",
        "2md": "850px",
        xs: "410px",
        "2xs": "350px",
      },
    },
  },
  plugins: [],
};
