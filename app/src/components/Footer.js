import React from 'react'
import SportsBaseballIcon from '@mui/icons-material/SportsBaseball';
import '../styles/Footer.scss';

function Footer() {
  return (
    <div className="footer">
        <div className="container">
            <SportsBaseballIcon />  
        </div>
        <p> &copy; 2024 Diamond Data </p>
    </div>
  )
}

export default Footer