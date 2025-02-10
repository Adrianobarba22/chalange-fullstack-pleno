import React from 'react';
import './Navbar.css';
import navProfile from '../../assets/nav-profile.svg';

const Navbar = () => {
    const redirectToHome = () => {
        window.location.href = "/";
    };

    return (
        <div className='navbar'>
           <div className='logo'>
            <img src="/contactmanager.jpeg" width="64px" alt="logo" />
             <p>CONTACT MANAGER</p>
           </div>
            <img src={navProfile} className='nav-profile' alt="Profile" onClick={redirectToHome} />
        </div>
    );
};

export default Navbar;
