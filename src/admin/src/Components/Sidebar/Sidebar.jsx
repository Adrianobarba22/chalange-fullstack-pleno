import React from 'react'
import './Sidebar.css'
import {Link} from 'react-router-dom'
import add_contact_icon from '../../assets/addcontact.png'
import list_contact_icon from '../../assets/list-contatos.svg'

const Sidebar = ()=>{
    return (
        <div className='sidebar'>
            <Link to={'/addcontact'} style={{textDecoration:"none"}}>
                <div className="sidebar-item">
                    <img src={add_contact_icon} width="36px" alt="" />
                    <p>Adicionar Contato</p>
                </div>
                
            </Link>
            <Link to={'/listcontact'} style={{textDecoration:"none"}}>
                <div className="sidebar-item">
                    <img src={list_contact_icon} width="36px" alt="" />
                    <p>Listar Contatos</p>
                </div>
            </Link>
        </div>
    )
}

export default Sidebar