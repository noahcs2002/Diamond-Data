import React from "react";
import '../styles/LoadingScreen.scss';
import SportsBaseball from "@mui/icons-material/SportsBaseball";

const SavingScreen = () => {

    return (
        <div className="loadingScreen">
            <div className="baseballIcon">
                <SportsBaseball style={{ fontSize: '10em' }}/>
            </div>
            <p className="loadingText">Saving Changes</p>
        </div>
    )
}

export default SavingScreen;