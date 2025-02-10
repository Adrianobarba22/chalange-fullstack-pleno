import React from 'react'
import './Admin.css'
import Sidebar from '../../Components/Sidebar/Sidebar'
import {Routes, Route} from 'react-router-dom'
import AddContact from '../../Components/AddContact/AddContact'
import ListContact from '../../Components/ListContact/ListContact'

const Admin = ()=>{
    return (
        <div className="admin">
        <Sidebar/>
            <Routes>
                <Route path='/addcontact'element={<AddContact/>}/>
                <Route path='/listcontact'element={<ListContact/>}/>
            </Routes>
           
        </div>
    )
}

export default Admin