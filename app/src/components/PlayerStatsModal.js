import React from "react";
import '/home/jayleebaker/school/sp2024/csc530/CSC530_Final_Project/CSC530-Final-Project/app/src/styles/PlayerStatsModal.scss';

function PlayerStatsModal({ player, show, onClose}) {

    if (!show || !player) return null;

    const isObject = (value) => {
        return typeof value == 'object' && value !== null && !Array.isArray(value);
    };

    return (
        <div className="playerStatsModal">
            <div className="modal-content">
                <span className="close" onClick={onClose}>&times;</span>
                <h2>{player.firstName} {player.lastName} Stats</h2>
                <div className='playerStatsInfo'>
                    {Object.entries(player).map(([key, value]) => (
                        <div className="statRow" key={key}>
                            <span className="statName">{key.replace(/([A-Z])/g, ' $1').toUpperCase()}</span>
                            {isObject(value) ? null : <span className="statValue">{value}</span>}
                        </div>
                    ))}
                </div>
                <h2>{player.firstName} {player.lastName} Milestones</h2>
            </div>
        </div>
    )
}

export default PlayerStatsModal;