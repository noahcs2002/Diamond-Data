import React, {useState } from 'react';
import Diamond_Data_Transparent from "../assets/Diamond_Data_Transparent.png"
import { Link, useNavigate } from 'react-router-dom';
import SettingsIcon from '@mui/icons-material/Settings'; // Import the Settings icon
import '../styles/Navbar.scss';
import ConfirmModal from './ConfirmModal';
import { ToastContainer, toast } from 'react-toastify';

function Navbar() {
    const [openLinks, setOpenLinks] = useState(false);
    const navigate = useNavigate();
    const [showConfirmLogout, setShowConfirmLogout] = useState(false);
    const version = '1.1.0';
    const releaseDate = 'April 29th, 2024';

    const handleLogout = () => {
        setShowConfirmLogout(true);
    };

    const showToast = () => {
        toast.dismiss();
        toast.info(
            <div>
                <span>
                    Version {version}
                </span>
                <br/>
                <span>
                    Released {releaseDate}
                </span>
            </div>, {
            position:'bottom-right',
            autoClose: 5000,
            hideProgressBar:false,
            closeOnClick:true,
            pauseOnHover: false
        }) 
    }

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
                    <Link to="/BulkEntry"> Game Day </Link>
                    <button className="navbarLink" onClick={handleLogout}>Logout</button>

                    <Link onClick={showToast}>Info </Link>
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
                <Link className="navbarLink" to="/BulkEntry"> Game Day </Link>
                <button className="navbarLink" onClick={handleLogout}>Logout</button>
                <Link onClick={showToast}>Info </Link>
                <Link className="navbarLink" to="/Settings">
                    <SettingsIcon style={{ fontSize: 32, color: '#FFF' }} />
                </Link>
            </div>
            <ConfirmModal 
            isOpen={showConfirmLogout}
            message="Are you sure you want to logout?"
            onClose={() => setShowConfirmLogout(false)}
            onConfirm={() => navigate('/', {
                state: {
                    isLogOut: true
                }
            })}         />
        </div>
    )
}

export default Navbar;
