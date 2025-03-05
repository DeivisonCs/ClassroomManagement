-- -- DO $$
-- -- BEGIN
-- --     IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE tablename = 'aluno_turma') THEN
-- --         CREATE TABLE aluno_turma (
-- --             aluno_id BIGINT REFERENCES alunos(matricula),
-- --             turma_id BIGINT REFERENCES turmas(id),
-- --             PRIMARY KEY (aluno_id, turma_id)
-- --         );
-- --     END IF;
-- -- END $$;

-- -- Inserir disciplinas
-- INSERT INTO disciplinas (id, nome) VALUES
-- (1, 'Matemática'),
-- (2, 'Português'),
-- (3, 'História')
-- ON CONFLICT (id) DO NOTHING;

-- -- Inserir turmas
-- INSERT INTO turmas (id, nome, disciplina_id) VALUES
-- (1, 'Turma A', 1),
-- (2, 'Turma B', 2),
-- (3, 'Turma C', 3)
-- ON CONFLICT (id) DO NOTHING;

-- -- Inserir alunos
-- INSERT INTO alunos (matricula, nome, turma_id) VALUES
-- (1, 'João Silva', 1),
-- (2, 'Maria Oliveira', 1),
-- (3, 'Pedro Santos', 2),
-- (4, 'Ana Costa', 2),
-- (5, 'Lucas Pereira', 3)
-- ON CONFLICT (matricula) DO NOTHING;

-- -- Inserir professores
-- INSERT INTO professores (matricula, nome) VALUES
-- (1, 'Carlos Silva'),
-- (2, 'Fernanda Souza'),
-- (3, 'Roberto Lima')
-- ON CONFLICT (matricula) DO NOTHING;

-- -- Inserir dias da semana
-- INSERT INTO dias_semana (id, nome) VALUES
-- (1, 'Segunda-feira'),
-- (2, 'Terça-feira'),
-- (3, 'Quarta-feira'),
-- (4, 'Quinta-feira'),
-- (5, 'Sexta-feira')
-- ON CONFLICT (id) DO NOTHING;

-- -- Inserir horários
-- INSERT INTO horarios (id, inicio, fim) VALUES
-- (1, '17:00', '17:50'),
-- (2, '17:50', '18:40'),
-- (3, '18:40', '19:30'),
-- (4, '19:30', '20:20'),
-- (5, '20:20', '21:10'),
-- (6, '21:10', '22:00')
-- ON CONFLICT (id) DO NOTHING;

-- -- Inserir salas
-- INSERT INTO salas (id, nome) VALUES
-- (1, 'Sala 101', 30),
-- (2, 'Sala 102', 30),
-- (3, 'Sala 103', 50),
-- (4, 'Sala 104', 50),
-- (5, 'Sala 105', 50)
-- ON CONFLICT (id) DO NOTHING;

-- -- Inserir aulas
-- INSERT INTO aulas (id, disciplina_id, turma_id) VALUES
-- (1, 1, 1),
-- (2, 2, 2),
-- (3, 3, 3),
-- (4, 1, 2),
-- (5, 2, 3)
-- ON CONFLICT (id) DO NOTHING;


-- -- Inserir turma_sala
-- INSERT INTO turma_sala (id, sala_id, turma_id, horario_id, dia_semana_id, professor_id) VALUES
-- (1, 1, 1, 1, 1, 1),
-- (2, 2, 2, 2, 2, 2),
-- (3, 3, 3, 3, 3, 3),
-- (4, 4, 1, 4, 4, 1),
-- (5, 5, 2, 5, 5, 2)
-- ON CONFLICT (id) DO NOTHING;