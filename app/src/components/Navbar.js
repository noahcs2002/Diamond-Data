import React, { useState } from 'react';
import Diamond_Data_Logo_White from "../assets/Diamond_Data_Logo_White.PNG";
import { Link } from 'react-router-dom';
import ReorderIcon from '@mui/icons-material/Reorder';
import SettingsIcon from '@mui/icons-material/Settings'; // Import the Settings icon
import '../styles/Navbar.scss';

function Navbar() {
    const [openLinks, setOpenLinks] = useState(false);

    const toggleNavbar = () => {
        setOpenLinks(!openLinks);
    }

    return (
        <div className="navbar">
            <div className="leftSide" id={openLinks ? "open" : "close"}>
                <img src={Diamond_Data_Logo_White} />
                <div className="hiddenLinks">
                    <Link to="/Home"> Home </Link>
                    <Link to="/PlayerManagement"> PlayerManagement </Link>
                    <Link to="/TeamStats"> TeamStats </Link>
                    <Link to="/UserManagement"> UserManagement </Link>
                    <Link to="/Settings">
                        <SettingsIcon style={{ fontSize: 32, color: '#FFF' }} />
                    </Link>
                </div>
            </div>
            <div className="rightSide">
                <Link to="/Home"> Home </Link>
                <Link to="/PlayerManagement"> PlayerManagement </Link>
                <Link to="/TeamStats"> TeamStats </Link>
                <Link to="/UserManagement"> UserManagement </Link>
                <Link to="/Settings">
                    <SettingsIcon style={{ fontSize: 32, color: '#FFF' }} />
                </Link>
                <button onClick={toggleNavbar}>
                    <ReorderIcon />
                </button>
            </div>
        </div>
    )
}

export default Navbar;
