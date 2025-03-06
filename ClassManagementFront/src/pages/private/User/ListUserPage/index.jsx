import React, { useState, useEffect } from "react";

import "./styles.css";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Dropdown } from 'primereact/dropdown';
import { Tag } from 'primereact/tag';
import { FilterMatchMode } from 'primereact/api';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { listUsers, deleteUser } from "../../../../services/userService";

const ListUserPage = () => {
    const allRoles = ["Admin", "Aluno", "Professor"];
    const [filters, setFilters] = useState({
        id: { value: null, matchMode: FilterMatchMode.CONTAINS },
        nome: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        email: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        matricula: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        cargo: { value: null, matchMode: FilterMatchMode.EQUALS }
    });
    const [dataList, setDataList] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const users = await listUsers();
                const mappedUsers = users.map(user => ({
                    id: user.id,
                    nome: user.name,
                    matricula: user.registration,
                    email: user.email,
                    cargo: mapOccupationToRole(user.occupation)
                }));
                setDataList(mappedUsers);
            } catch (error) {
                console.error("Erro ao buscar usuários:", error);
            }
        };

        fetchUsers();
    }, []);

    const mapOccupationToRole = (occupation) => {
        switch (occupation) {
            case "ADMIN": return "Admin";
            case "STUDENT": return "Aluno";
            case "TEACHER": return "Professor";
            default: return "Desconhecido";
        }
    };

    const getRoleData = (role) => {
        switch (role) {
            case "Aluno": return 'success';
            case "Professor": return null;
            case "Admin": return 'warning';
            default: return 'danger';
        }
    }

    const roleTemplate = (rowData) => {
        return <Tag style={{ width: '50%', textAlign:'center', fontSize:'15px' }} value={rowData.cargo} severity={getRoleData(rowData.cargo)} />;
    };
    const roleItemTemplate = (option) => {
        return <Tag value={option} severity={getRoleData(option)} style={{ width: '100%', textAlign:'center' }}/>;
    };

    const roleRowFilterTemplate = (options) => {
        return (
            <Dropdown value={options.value} options={allRoles} onChange={(e) => options.filterApplyCallback(e.value)} itemTemplate={roleItemTemplate} placeholder="Filtrar Status" className="p-column-filter" showClear style={{ minWidth: '12rem' }} />
        );
    };

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="thematic"
                    tooltip="Remover Usuário" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-trash"
                    onClick={() => confirmDelete(rowData)}/>
            </div>
        );
    }

    const removeUser = async (id) => {
        try {
            await deleteUser(id);
            setDataList(dataList.filter(user => user.id !== id));
        } catch (error) {
            console.error("Erro ao remover usuário:", error);
        }
    }

    // dialog para confirmar remoção do usuário
    const confirmDelete = (user) => {
        confirmDialog({
            message: 'Deseja remover '+ user.nome +'?',
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeUser(user.id),
            reject: null
        });
    };

    return(
    <>
    <ConfirmDialog />
    <section id="list-page">
        <div className="title-div">
            <h1 className="page-title main-page-title">Usuários</h1>
            <h2 className="page-title sub-page-title">Listar</h2>
        </div>

        <div className="list-table-div" >
            <DataTable 
                value={dataList}
                emptyMessage="Nenhum registro encontrado."
                stripedRows
                showGridlines 
                tableStyle={{ minWidth: '80vw',  maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                dataKey="matricula"
                filters={filters} filterDisplay="row"
                globalFilterFields={['nome','email', 'matricula']}
                >
                <Column field="nome" sortable filter  header="Nome" filterPlaceholder="Buscar" style={{ minWidth: '12rem' }} ></Column>
                <Column field="matricula" filter header="Matricula" filterPlaceholder="Buscar" style={{ minWidth: '12rem' }}></Column>
                <Column field="email" filter header="Email" filterPlaceholder="Buscar" style={{ minWidth: '12rem' }}></Column>
                <Column 
                    field="cargo" filter 
                    header="Cargo" 
                    showFilterMenu={false} 
                    body={roleTemplate} 
                    filterElement={roleRowFilterTemplate} 
                    style={{ minWidth: '12rem', textAlign:"center"}} 
                    filterMenuStyle={{ width: '14rem' }}></Column>
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} ></Column>
            </DataTable>
        </div>
    </section>
    </>
    );
}


export default ListUserPage;