/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "node_modules/flowbite-react/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ["Poppins", "sans-serif"],
        mono: ["JetBrains Mono", "monospace"],
      },
      animation: {
        appear: "appear 1s",
        wiggle: "wiggle 10s infinite ease-in",
      },
      keyframes: {
        appear: {
          "0%": { transform: "translateY(-5rem)" },
        },
        wiggle: {
          "0%": { transform: "scale(1)" },
          "50%": { transform: "scale(0.95) translateY(-50px)" },
          "100%": { transform: "scale(1)" },
        },
      },
      plugins: [require("@tailwindcss/line-clamp")],
    },
  },
};
