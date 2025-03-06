export const groupContinuousLessons = (lessons) => {
    const normalizedLessons = [];
    
    lessons.forEach(lesson => {
        if (lesson.scheduleInfos && lesson.scheduleInfos.length > 0) {
            lesson.scheduleInfos.forEach(schedule => {
                normalizedLessons.push({
                    lessonId: lesson.id,
                    scheduleId: schedule.id,
                    disciplinaId: lesson.disciplina?.id,
                    disciplinaNome: lesson.disciplina?.nome,
                    turmaId: lesson.turma?.id,
                    turmaNome: lesson.turma?.nome,
                    salaId: schedule.sala?.id,
                    salaNome: schedule.sala?.nome,
                    salaCapacidade: schedule.sala?.capacidade,
                    professorId: schedule.professor?.matricula || schedule.professor?.id,
                    professorNome: schedule.professor?.nome,
                    diaSemanaId: schedule.diaSemana?.id,
                    diaSemanaNome: schedule.diaSemana?.nome,
                    horarioId: schedule.horario?.id,
                    horarioInicio: schedule.horario?.inicio,
                    horarioFim: schedule.horario?.fim,
                    originalLesson: lesson,
                    originalSchedule: schedule
                });
            });
        } else {
            normalizedLessons.push({
                lessonId: lesson.id,
                disciplinaId: lesson.disciplina?.id,
                disciplinaNome: lesson.disciplina?.nome,
                turmaId: lesson.turma?.id,
                turmaNome: lesson.turma?.nome,
                originalLesson: lesson,
                originalSchedule: null
            });
        }
    });

    // Gera uma chave de agrupamento para cada aula normalizada
    normalizedLessons.forEach(item => {
        item.groupKey = `${item.disciplinaId}-${item.turmaId}-${item.salaId}-${item.professorId}-${item.diaSemanaId}`;
    });

    normalizedLessons.sort((a, b) => {
        if (a.groupKey !== b.groupKey) {
            return a.groupKey.localeCompare(b.groupKey);
        }
        
        return (a.horarioInicio || '').localeCompare(b.horarioInicio || '');
    });
    
    const groups = [];
    let currentGroup = null;
    
    normalizedLessons.forEach(item => {
        if (!currentGroup || currentGroup.groupKey !== item.groupKey) {
            if (currentGroup) {
                groups.push(currentGroup);
            }
            
            currentGroup = {
                groupKey: item.groupKey,
                disciplinaId: item.disciplinaId,
                disciplinaNome: item.disciplinaNome,
                turmaId: item.turmaId,
                turmaNome: item.turmaNome,
                salaId: item.salaId,
                salaNome: item.salaNome,
                salaCapacidade: item.salaCapacidade,
                professorId: item.professorId,
                professorNome: item.professorNome,
                diaSemanaId: item.diaSemanaId,
                diaSemanaNome: item.diaSemanaNome,
                startTime: item.horarioInicio,
                endTime: item.horarioFim,
                items: [item],
                lessonCount: 1
            };
        } else {
            const lastItem = currentGroup.items[currentGroup.items.length - 1];
            if (lastItem.horarioFim === item.horarioInicio) {
                currentGroup.items.push(item);
                currentGroup.lessonCount++;
                currentGroup.endTime = item.horarioFim;
            } else {
                groups.push(currentGroup);
                currentGroup = {
                    groupKey: item.groupKey,
                    disciplinaId: item.disciplinaId,
                    disciplinaNome: item.disciplinaNome,
                    turmaId: item.turmaId,
                    turmaNome: item.turmaNome,
                    salaId: item.salaId,
                    salaNome: item.salaNome,
                    salaCapacidade: item.salaCapacidade,
                    professorId: item.professorId,
                    professorNome: item.professorNome,
                    diaSemanaId: item.diaSemanaId,
                    diaSemanaNome: item.diaSemanaNome,
                    startTime: item.horarioInicio,
                    endTime: item.horarioFim,
                    items: [item],
                    lessonCount: 1
                };
            }
        }
    });
    
    if (currentGroup) {
        groups.push(currentGroup);
    }
    
    // Rastrear os IDs das aulas que estão em grupos reais (com múltiplas aulas)
    const lessonsInGroups = new Set();
    groups.forEach(group => {
        if (group.lessonCount > 1) {
            group.items.forEach(item => {
                lessonsInGroups.add(item.scheduleId);
            });
        }
    });
    
    const result = groups.map(group => {
        const isRealGroup = group.lessonCount > 1;
        const firstItem = group.items[0];
        
        return {
            id: firstItem.lessonId,
            scheduleId: firstItem.scheduleId,
            disciplina: {
                id: group.disciplinaId,
                nome: group.disciplinaNome
            },
            turma: {
                id: group.turmaId,
                nome: group.turmaNome
            },
            currentSchedule: {
                id: firstItem.scheduleId,
                sala: {
                    id: group.salaId,
                    nome: group.salaNome,
                    capacidade: group.salaCapacidade
                },
                professor: {
                    matricula: group.professorId,
                    nome: group.professorNome
                },
                diaSemana: {
                    id: group.diaSemanaId,
                    nome: group.diaSemanaNome
                },
                horario: {
                    inicio: group.startTime,
                    fim: group.endTime
                }
            },
            isGroup: isRealGroup,
            lessonCount: group.lessonCount,
            startTime: group.startTime,
            endTime: group.endTime,
            originalLessons: group.items.map(item => item.originalLesson),
            groupKey: group.groupKey
        };
    });
    
    // Filtrar o resultado para remover aulas individuais que já estão em grupos
    const filteredResult = result.filter(item => 
        item.isGroup || // Manter todos os grupos reais
        !lessonsInGroups.has(item.currentSchedule.id) // Manter apenas aulas individuais que não estão em grupos
    );
    
    // Remover duplicatas baseadas na chave única
    const uniqueGroups = {};
    filteredResult.forEach(group => {
        const uniqueKey = `${group.groupKey}|${group.startTime}-${group.endTime}`;
        
        if (!uniqueGroups[uniqueKey]) {
            uniqueGroups[uniqueKey] = group;
        }
    });
    
    return Object.values(uniqueGroups);
};