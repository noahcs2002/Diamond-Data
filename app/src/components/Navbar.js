import React, { useEffect, useState } from 'react';
import Diamond_Data_Logo_White from "../assets/Diamond_Data_Logo_White.PNG";
import Diamond_Data_Transparent from "../assets/Diamond_Data_Transparent.png"
import { Link, useNavigate } from 'react-router-dom';
import ReorderIcon from '@mui/icons-material/Reorder';
import SettingsIcon from '@mui/icons-material/Settings'; // Import the Settings icon
import '../styles/Navbar.scss';

function Navbar() {
    const [openLinks, setOpenLinks] = useState(false);
    const navigate = useNavigate();

    const toggleNavbar = () => {
        setOpenLinks(!openLinks);
    };

    const handleLogout = () => {
        if (window.confirm("Logout?")) {
            navigate('/');
        }
    };

    return (
        <div className="navbar">
            <div className="leftSide" id={openLinks ? "open" : "close"}>
                <img src={Diamond_Data_Transparent} />
                <h4 style={{color:'white'}}> {JSON.parse(localStorage.getItem('sessionData')).name} ({JSON.parse(localStorage.getItem('sessionData')).email}) </h4>
                <div className="hiddenLinks">
                    <Link className="navbarLink" to="/Home"> Home </Link>
                    <Link to="/PlayerManagement"> Player Management </Link>
                    <Link to="/TeamStats"> Team Stats </Link>
                    <Link to="/PlayerStats"> Player Stats </Link>
                    <Link to="/Roster"> Roster </Link>
                    {/* <Link to="/Insights"> Insights </Link> */}
                    <Link to="/BulkEntry"> Bulk Entry </Link>
                    <button className="navbarLink" onClick={handleLogout}>Logout</button>
                    <Link to="/Settings">
                        <SettingsIcon style={{ fontSize: 32, color: '#FFF' }} />
                    </Link>
                </div>
            </div>
            <div className="rightSide">
                <Link className="navbarLink" to="/Home"> Home </Link>
                <Link className="navbarLink" to="/PlayerManagement"> Player Management </Link>
                <Link className="navbarLink" to="/TeamStats"> Team Stats </Link>
                <Link className="navbarLink" to="/PlayerStats"> Player Stats </Link>
                <Link className="navbarLink" to="/Roster"> Roster </Link>
                {/* <Link to="/Insights"> Insights </Link> */}
                <Link className="navbarLink" to="/BulkEntry"> Bulk Entry </Link>
                <button className="navbarLink" onClick={handleLogout}>Logout</button>
                <Link className="navbarLink" to="/Settings">
                    <SettingsIcon style={{ fontSize: 32, color: '#FFF' }} />
                </Link>
                {/* <button onClick={toggleNavbar}>
                    <ReorderIcon />
                </button> */}
            </div>
        </div>
    )
}

export default Navbar;
