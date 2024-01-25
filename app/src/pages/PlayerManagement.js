import React from 'react'
import { PositionList } from '../helpers/PositionList';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.css';

function PlayerManagement() {
  return (
    <div className="playerManagement">
        <h1 className="title">Player Management</h1>
        <div className="positionList">
            {PositionList.map((positionList, key) => {
                return (
                    <PlayerItem 
                        name={positionList.name} 
                        stat1={positionList.stat1} 
                        stat2={positionList.stat2} 
                />
            );
        })}
        </div>
    </div>
  )
}

export default PlayerManagement