import { useState, useEffect } from "react";
import { lessonService } from "../../../../../services/lessonService";
import { classScheduleService } from "../../../../../services/classScheduleService";
import { groupContinuousLessons } from "../utils/groupingUtils";

export const useLessonData = (toastRef) => {
  const [rawData, setRawData] = useState([]);
  const [groupedData, setGroupedData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedTurma, setSelectedTurma] = useState(null);
  const [turmaDetailsVisible, setTurmaDetailsVisible] = useState(false);

  const showToast = (severity, summary, detail) => {
    toastRef.current?.show({ severity, summary, detail, life: 3000 });
  };

  const loadData = async () => {
    try {
      setLoading(true);
      
      const [aulasData, schedulesData] = await Promise.all([
        lessonService.getAll(),
        classScheduleService.getAll(),
      ]);

      const turmaToScheduleMap = {};
      schedulesData.forEach((schedule) => {
        if (schedule.turma && schedule.turma.id) {
          if (turmaToScheduleMap[schedule.turma.id]) {
            turmaToScheduleMap[schedule.turma.id].push(schedule);
          } else {
            turmaToScheduleMap[schedule.turma.id] = [schedule];
          }
        }
      });

      const combinedResults = aulasData.map((aula) => {
        const scheduleInfos =
          aula.turma && aula.turma.id
            ? turmaToScheduleMap[aula.turma.id] || []
            : [];

        return {
          ...aula,
          scheduleInfos: scheduleInfos,
        };
      });

      setRawData(combinedResults);
      const grouped = groupContinuousLessons(combinedResults);
      setGroupedData(grouped);
    } catch (error) {
      console.error("Erro ao buscar dados:", error);
      showToast("error", "Erro", "Falha ao carregar dados");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  return {
    rawData,
    groupedData,
    loading,
    selectedTurma,
    turmaDetailsVisible,
    setSelectedTurma,
    setTurmaDetailsVisible,
    showToast,
    loadData
  };
};