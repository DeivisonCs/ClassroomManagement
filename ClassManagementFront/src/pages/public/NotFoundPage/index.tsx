import React from 'react';
import './styles.css'; // Importando o arquivo CSS para o estilo
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  return (
    <div className="notfound-container">
      <div className="notfound-content">
        <img
          className="notfound-image"
          src="https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExN3lwd3VpYXRneTl6dnBmcWpxYXY5aGd2MjhvZnA4dzN5b3d0NG85aCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/pyLTnfIzYPnw8LEWWU/giphy.gif"
          alt="Error"
        />
        <h1 className="notfound-title">Página não encontrada</h1>
        <p className="notfound-description">
          Oops! A página que você está procurando não existe. Volte para a página inicial ou verifique a URL.
        </p>

        <Link to="/" className="notfound-button">
          Voltar para Home
        </Link>
      </div>
    </div>
  );
};

export default NotFoundPage;
