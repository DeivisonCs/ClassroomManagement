# Web App de Gerenciamento de Salas

### Micro Serviços
- Usuários

### MS - Usuários
    
### Rotas

---
#### Auth Controller
**(POST) /auth/login**
```
    Recebe a matricula e senha de um usuário, retornando o token de acesso.
    !O login é o número de matricula!

    Retorno:
    {
        "token": (token_de_acesso)
    }

    Permissão: todos os usuários
```

---
#### Account Controller
**(POST) /account**
```
    Cadastra um novo usuário.

    Dados de Entrada:
    {
        "occupationId": (id_do_cargo),
        "name": (nome)
        "email": (email)
        "cpf": (cpf_formatado)
        "registration: (matricula)
    }

    Retorno:
    {
        "id": 1,
        "registration": "2024110522"
        "name": "Teste dos Santos"
        "email": "teste@gmail.com"
        "occupation: "STUDENT"
    }

    Permissão: ADMIN
```

---
**(GET) /account**
```
    Lista todos os usuários cadastrados.

    Retorno:
    {
        "id": 1,
        "registration": "2024110522"
        "name": "Teste dos Santos"
        "email": "teste@gmail.com"
        "occupation: "Aluno"
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /account/{id}**
```
    Retorna os dados de um usuário, dado seu id.

    Retorno:
    {
        "registration": "2024110522"
        "occupation": "STUDENT"
        "name": "Teste dos Santos"
        "email": "teste@gmail.com"
        "cpf": "111.111.111-11"
    }

    Permissão: todos os usuários autenticados
```

---
**(PUT) /account/{id}**
```
    Recebe dados de edição de um usuário, alterando esses dados com base no id do usuário.

    Dados de Entrada:
    {
        "occupationId": "2",
        "name": "Teste"
        "email": "teste_1@gmail.com"
        "cpf": "111.111.111-11"
        "registration: "2024110522"
    }

    Retorno:
    {
        "registration": "2024110522"
        "occupation": "STUDENT"
        "name": "Teste"
        "email": "teste_1@gmail.com"
        "cpf": "111.111.111-11"
    }

    Permissão: todos os usuários autenticados
```

---
**(DELETE) /account/{id}**
```
    Apaga um usuário do sistema, dado seu id.

    Retorno: void

    Permissão: ADMIN
```

---
**(GET) /account/occupations**
```
    Retorna as ocupações cadastradas no sistema.

    Retorno:
    {
        "id": 1,
        "name": "ADMIN"
    }

    Permissão: ADMIN
```