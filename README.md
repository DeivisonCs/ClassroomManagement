# Web App de Gerenciamento de Salas

### Micro Serviços
- Usuários
- Salas

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

### MS - Salas
    
### Rotas

---
#### Sala Controller
**(POST) /api/salas**
```
    Cria uma sala, indicando o nome e capacidade máxima da sala.

    Dados de Entrada:
    {
        "nome": (nome_da_sala)
        "capacidade": (capacidade_de_alunos)
    }

    Retorno:
    {
        "id": 1
        "nome": "sala_01"
        "capacidade": 40
    }

    Permissão: todos os usuários autenticados
```

---
**(PUT) /api/salas/{id}**
```
    Atualiza os dados de uma sala, dado seu id.

    Retorno:
    {
        "nome": "lab 01"
        "capacidade": 35
    }

    Retorno:
    {
        "id": 1
        "nome": "lab 01"
        "capacidade": 35
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/salas/{id}**
```
    Retorna informações de uma sala, dado seu id.

    Retorno:
    {
        "id": 1
        "nome": "lab 01"
        "capacidade": 35
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/salas**
```
    Retorna todas as salas cadastradas.

    Retorno:
    [
        {
            "id": 1
            "nome": "lab 01"
            "capacidade": 35
        },
        {
            "id": 2
            "nome": "lab 02"
            "capacidade": 30
        }
    ]

    Permissão: todos os usuários autenticados
```

---
**(DELETE) /api/salas/{id}**
```
    Apaga uma sala do sistema, dado seu id.

    Retorno: void

    Permissão: todos os usuários autenticados
```

---
#### Disciplina Controller
**(POST) /api/disciplinas**
```
    Cria uma nova disciplina.

    Dados de Entrada:
    {
        "nome": (nome_da_disciplina)
        "professorMatricula": [
            (ids_dos_professores)
        ]
        "turmasIds": [
            (ids_das_turmas)
        ]
    }

    Retorno:
    {
        "id": 1
        "nome": "Matemática"
        "professores": [
            {
                "matricula": "202512300"
                "nome": "Professor Teste 1"
            },
            {
                "matricula": "20241231"
                "nome": "Professor Teste 2"
            }
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(PUT) /api/disciplinas/{id}**
```
    Atualiza os dados de uma disciplina, dado seu id.

    Dados de Entrada:
    {
        "nome": (nome_da_disciplina)
        "professorMatricula": [
            (ids_dos_professores)
        ]
        "turmasIds": [
            (ids_das_turmas)
        ]
    }

    Retorno:
    {
        "id": 1
        "nome": "Matemática"
        "professores": [
            {
                "matricula": "202512300"
                "nome": "Professor Teste 1"
            },
            {
                "matricula": "20241231"
                "nome": "Professor Teste 2"
            }
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/disciplinas/{id}**
```
    Retorna os detalhes de uma disciplina, dado seu id.

    Retorno:
    {
        "id": 1
        "nome": "Matemática"
        "professores": [
            {
                "matricula": "202512300"
                "nome": "Professor Teste 1"
            },
            {
                "matricula": "20241231"
                "nome": "Professor Teste 2"
            }
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/disciplinas**
```
    Retorna todas as disciplinas cadastradas.

    Retorno:
    [
        {
            "id": 1
            "nome": "Matemática"
            "professores": [
                {
                    "matricula": "202512300"
                    "nome": "Professor Teste 1"
                },
                ...
            ]
        },
        ...
    ]

    Permissão: todos os usuários autenticados
```

---
**(DELETE) /api/disciplinas/{id}**
```
    Apaga uma disciplina do sistema, dado seu id.

    Retorno: void

    Permissão: todos os usuários autenticados
```

---
#### Turma Controller
**(POST) /api/turmas**
```
    Cria uma nova turma no sistema.

    Dados de Entrada:
    {
        "nome": (nome_da_turma),
        "disciplinaId": (id_da_disciplina),
        "alunosIds": [
            (ids_dos_alunos)
        ]
    }

    Retorno:
    {
        "id": 1,
        "nome": "Turma A",
        "disciplina": {
            "id": 1,
            "nome": "Matemática"
            "professores": [
                {
                    "matricula": "202512300"
                    "nome": "Professor Teste 1"
                },
                ...
            ]
        },
        "alunos": [
            {
                "matricula": 2024123,
                "nome": "Aluno Teste 1"
                "turmas": []
            },
            ...
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(PUT) /api/turmas/{id}**
```
    Atualiza os dados de uma turma, dado seu ID.

    Dados de Entrada:
    {
        "nome": (nome_da_turma),
        "disciplinaId": (id_da_disciplina),
        "alunosIds": [
            (ids_dos_alunos)
        ]
    }

    Retorno:
    {
        "id": 1,
        "nome": "Turma A",
        "disciplina": {
            "id": 1,
            "nome": "Matemática"
            "professores": [
                {
                    "matricula": "202512300"
                    "nome": "Professor Teste 1"
                },
                ...
            ]
        },
        "alunos": [
            {
                "matricula": 2024123,
                "nome": "Aluno Teste 1"
                "turmas": []
            },
            ...
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/turmas/{id}**
```
    Retorna os detalhes de uma turma, dado seu ID.

    Retorno:
    {
        "id": 1,
        "nome": "Turma A",
        "disciplina": {
            "id": 1,
            "nome": "Matemática"
            "professores": [
                {
                    "matricula": "202512300"
                    "nome": "Professor Teste 1"
                },
                ...
            ]
        },
        "alunos": [
            {
                "matricula": 2024123,
                "nome": "Aluno Teste 1"
                "turmas": []
            },
            ...
        ]
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/turmas**
```
    Retorna todas as turmas cadastradas no sistema.

    Retorno:
    [
        {
            "id": 1,
            "nome": "Turma A",
            "disciplina": {
                "id": 1,
                "nome": "Matemática"
                "professores": [
                    {
                        "matricula": "202512300"
                        "nome": "Professor Teste 1"
                    },
                    ...
                ]
            },
            "alunos": [
                {
                    "matricula": 2024123,
                    "nome": "Aluno Teste 1"
                    "turmas": []
                },
                ...
            ]
        },
        ...
    ]

    Permissão: todos os usuários autenticados
```

---
**(DELETE) /api/turmas/{id}**
```
    Apaga uma turma do sistema, dado seu ID.

    Retorno: void

    Permissão: todos os usuários autenticados
```



---
#### Aula Controller
**(POST) /api/aulas**
```
    Cria uma nova aula no sistema.

    Dados de Entrada:
    {
        "disciplinaId": (id_da_disciplina),
        "turmaId": (id_da_turma)
    }

    Retorno:
    {
        "id": 1,
        "disciplina": {
            "id": 1,
            "nome": "Matemática",
            "professores": [
                {
                    "matricula": "202512041"
                    "nome": "Professor Teste"
                },
                ...
            ]
        },
        "turma": {
            "id": 1,
            "nome": "Turma A"
        }
    }

    Permissão: todos os usuários autenticados
```

---
**(PUT) /api/aulas/{id}**
```
    Atualiza os dados de uma aula, dado seu ID.

    Dados de Entrada:
    {
        "disciplinaId": (id_da_disciplina),
        "turmaId": (id_da_turma)
    }

    Retorno:
    {
        "id": 1,
        "disciplina": {
            "id": 1,
            "nome": "Matemática",
            "professores": [
                {
                    "matricula": "202512041"
                    "nome": "Professor Teste"
                },
                ...
            ]
        },
        "turma": {
            "id": 1,
            "nome": "Turma A"
        }
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/aulas/{id}**
```
    Retorna os detalhes de uma aula, dado seu ID.

    Retorno:
    {
        "id": 1,
        "disciplina": {
            "id": 1,
            "nome": "Matemática",
            "professores": [
                {
                    "matricula": "202512041"
                    "nome": "Professor Teste"
                },
                ...
            ]
        },
        "turma": {
            "id": 1,
            "nome": "Turma A"
        }
    }

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/aulas**
```
    Retorna todas as aulas cadastradas no sistema.

    Retorno:
    [
        {
            "id": 1,
            "disciplina": {
                "id": 1,
                "nome": "Matemática",
                "professores": [
                    {
                        "matricula": "202512041"
                        "nome": "Professor Teste"
                    },
                    ...
                ]
            },
            "turma": {
                "id": 1,
                "nome": "Turma A"
            }
        },
        ...
    ]

    Permissão: todos os usuários autenticados
```

---
**(GET) /api/aulas**
```
    Apaga uma aula do sistema, dado seu ID.

    Retorno: void

    Permissão: todos os usuários autenticados
```