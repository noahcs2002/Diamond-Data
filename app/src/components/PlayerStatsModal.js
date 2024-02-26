import React from "react";

function PlayerStatsModal({ player, onClose}) {

    return (
        <div className="playerStatsModal">
            <div className="modal-content">
                <span className="close" onClick={onClose}>&times;</span>
                <h2>{player.firstName} {player.lastName} Stats</h2>
            </div>
        </div>
    )
}

export default PlayerStatsModal;