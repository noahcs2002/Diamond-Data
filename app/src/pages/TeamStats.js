import React, { useState } from 'react';
import '../styles/TeamStats.css';

function TeamStats() {
  const [teams, setTeams] = useState([
    { id: 1, name: 'Team 1', players: 20, record: '10-5' },
    { id: 2, name: 'Team 2', players: 18, record: '8-7' },
    { id: 3, name: 'Team 3', players: 22, record: '12-3' },
    { id: 4, name: 'Team 4', players: 15, record: '5-10' },
    { id: 5, name: 'Team 5', players: 19, record: '9-6' },
  ]);

  const [teamStats, setTeamStats] = useState({
    1: { battingAverage: 0.300, homeRuns: 20, stolenBases: 10, runs: 100 },
    2: { battingAverage: 0.280, homeRuns: 15, stolenBases: 8, runs: 90 },
    3: { battingAverage: 0.320, homeRuns: 25, stolenBases: 12, runs: 110 },
    4: { battingAverage: 0.260, homeRuns: 10, stolenBases: 5, runs: 80 },
    5: { battingAverage: 0.310, homeRuns: 18, stolenBases: 9, runs: 95 },
  });

  const [selectedTeam, setSelectedTeam] = useState(null);
  const [isStatsView, setIsStatsView] = useState(false);

  const openModal = (team) => {
    setSelectedTeam(team);
  };

  const closeModal = () => {
    setSelectedTeam(null);
    setIsStatsView(false);
  };

  const toggleView = () => {
    setIsStatsView(!isStatsView);
  };

  return (
    <div className="teamStats">
      <h1>Team Stats</h1>
      <div className="teamList">
        {teams.map((team) => (
          <div key={team.id} className="teamItem">
            <div className="teamInfo" onClick={() => openModal(team)}>
              <div className="teamLabel">{team.name}</div>
              <div className="teamRecord">{team.record}</div>
            </div>
          </div>
        ))}
      </div>

      {selectedTeam && (
        <div className="modal">
          <div className="modalContent">
            <div className="toggleSwitchContainer" onClick={toggleView}>
              <div className={`toggleSwitch ${isStatsView ? 'stats' : 'info'}`}>
                {isStatsView ? 'Team Stats' : 'Team Info'}
              </div>
            </div>
            <span className="close" onClick={closeModal}>&times;</span>
            <h2>{selectedTeam.name}</h2>
            {isStatsView ? (
              <>
                <p>Batting Average: {teamStats[selectedTeam.id].battingAverage.toFixed(3)}</p>
                <p>Home Runs: {teamStats[selectedTeam.id].homeRuns}</p>
                <p>Stolen Bases: {teamStats[selectedTeam.id].stolenBases}</p>
                <p>Runs: {teamStats[selectedTeam.id].runs}</p>
              </>
            ) : (
              <>
                <p>Number of Players: {selectedTeam.players}</p>
                <p>Record: {selectedTeam.record}</p>
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default TeamStats;
