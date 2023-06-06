/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    colors: {
      'p-yellow': '#FF9000',
      'p-brown' : '#4F200D',
      'p-red': '#B31313',
      'p-white': '#F6F1E9',
      'p-gray': '#D9D9D9',
      'p-orange': '#FF8400'
    },
    extend: {
      backgroundImage: {
        'bg-login': "url('./public/bg-login.png')",
        'bg-nav-bar': "url('./public/bg-nav-bar.png')",
        'logo-tickcheckting': "url('./public/logo-tickcheckting.png')"
      }
    },
  },
  plugins: [],
}

