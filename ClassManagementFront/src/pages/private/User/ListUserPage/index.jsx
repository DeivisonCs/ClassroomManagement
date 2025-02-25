import React, { useState } from "react";

import "./styles.css";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Dropdown } from 'primereact/dropdown';
import { Tag } from 'primereact/tag';
import { FilterMatchMode } from 'primereact/api';
import { InputText } from 'primereact/inputtext';
import { IconField } from 'primereact/iconfield';
import { InputIcon } from 'primereact/inputicon';

const ListUserPage = () => {
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const allRoles = ["Admin", "Aluno", "Professor"];
    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        nome: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        email: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        matricula: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
        cargo: { value: null, matchMode: FilterMatchMode.EQUALS }
    });
    const dataList = [
        {matricula: '1234',nome: 'Teste do Santos',email: 'teste@gmail.com',cargo: 'Professor'},
        {matricula: '56788',nome: 'Ademiro Damasceno',email: 'adema@gmail.com',cargo: 'Aluno'},
        {matricula: '4127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Admin'},
        {matricula: '616127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '1825127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '068127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '613127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '61127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '84127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '823127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '190127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '74127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '612127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '617127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
        {matricula: '58127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'asdd'},
        {matricula: '198127',nome: 'Gragas de calcinha',email: 'furadeira123@gmail.com',cargo: 'Aluno'},
    ]

    const getRoleData = (role) => {
        switch (role) {
            case "Aluno": return 'success';
            case "Professor": return null;
            case "Admin": return 'warning';
            default: return 'danger';
        }
    }
    const onGlobalFilterChange = (e) => {
        const value = e.target.value;
        let _filters = { ...filters };

        _filters['global'].value = value;

        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const renderHeader = () => {
        return (
            <div className="flex justify-content-end">
                <IconField iconPosition="left">
                    <InputIcon className="pi pi-search" />
                    <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Buscar" />
                </IconField>
            </div>
        );
    };

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

    const header = renderHeader();

    return(
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
            </DataTable>
        </div>
    </section>
    );
}


export default ListUserPage;