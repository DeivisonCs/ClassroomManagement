import React, { useRef } from "react";
import { Toast } from "primereact/toast";
import { ConfirmDialog } from "primereact/confirmdialog";
import "./styles.css";

import { useLessonData } from "./hooks/useLessonData.jsx";
import { useLessonActions } from "./hooks/useLessonActions.jsx";
import LessonTable from "./components/LessonTable";
import TurmaDetailsDialog from "./components/TurmaDetailsDialog";

const ListLessonPage = () => {
  const toast = useRef(null);
  
  const { 
    groupedData, 
    loading, 
    selectedTurma,
    turmaDetailsVisible,
    setSelectedTurma,
    setTurmaDetailsVisible,
    showToast 
  } = useLessonData(toast);
  
  const {
    confirmDelete,
    showGroupDetails,
    openTurmaDetails
  } = useLessonActions({
    setSelectedTurma,
    setTurmaDetailsVisible,
    showToast
  });

  return (
    <>
      <Toast ref={toast} />
      <ConfirmDialog />
      
      <section id="list-page">
        <div className="title-div">
          <h1 className="page-title main-page-title">Aulas</h1>
          <h2 className="page-title sub-page-title">Listar</h2>
        </div>

        <LessonTable 
          data={groupedData} 
          loading={loading} 
          onDelete={confirmDelete}
          onShowGroupDetails={showGroupDetails}
          onTurmaClick={openTurmaDetails}
        />
      </section>

      <TurmaDetailsDialog 
        turma={selectedTurma} 
        visible={turmaDetailsVisible}
        onHide={() => setTurmaDetailsVisible(false)}
      />
    </>
  );
};

export default ListLessonPage;