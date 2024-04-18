import React from "react";
import '../styles/LoadingScreen.scss';
import SportsBaseballIcon from '@mui/icons-material/SportsBaseball';
import SportsBaseball from "@mui/icons-material/SportsBaseball";

const LoadingScreen = () => {

    return (
        <div className="loadingScreen">
            <div className="baseballIcon">
                <SportsBaseball style={{ fontSize: '10em' }}/>
            </div>
            <p className="loadingText">Loading</p>
        </div>
    )
}

export default LoadingScreen;