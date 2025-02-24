import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import './global.css'
import 'primereact/resources/themes/lara-light-indigo/theme.css';  // Tema do PrimeReact
import 'primereact/resources/primereact.min.css';  // Estilos principais
import 'primeicons/primeicons.css';  // Ícones do PrimeReact
import AppRoutes from './routes';


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AppRoutes/>
  </StrictMode>,
)
