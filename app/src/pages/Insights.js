import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import '../styles/Insights.scss'; // Import the Insights styling

function Insights() {
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [players, setPlayers] = useState([]);
  const [selectedPlayers, setSelectedPlayers] = useState([null, null]); // Assuming two players for comparison

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const playerOptions = players.map(player => (
    <option key={player.id} value={player.id}>{player.name}</option>
  ));

  useEffect(() => {
    const fetchTeams = async () => {
      const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
      const url = new URL(endpoint);
      url.searchParams.append("userId", 302);
      try {
        const response = await axios.get(url.toString());
        setTeams(response.data);
      } catch (error) {
        console.error('Error fetching teams:', error);
      }
    };
    fetchTeams();
  }, []);

  useEffect(() => {
    const fetchPlayers = async () => {
      if (!selectedTeam) return;
      const endpoint = `http://localhost:8080/diamond-data/api/get-by-team?teamId=${selectedTeam}`;
      try {
        const response = await axios.get(endpoint);
        setPlayers(response.data);
      } catch (error) {
        console.error('Error fetching players:', error);
      }
    };
    
    fetchPlayers();
  }, [selectedTeam]);
  

  const handlePlayerSelectionChange = (index, playerId) => {
    const updatedSelection = [...selectedPlayers];
    updatedSelection[index] = playerId;
    setSelectedPlayers(updatedSelection);
  };

  return (  
    <div>
      <Navbar />
      <div className="insights">
        <h1 className="title">Team Insights</h1>
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
        <div className="content">
          <div className="left-column">
            <div className="column">
              <h2>AI-generated Roster Lineup</h2>
              <div className="scrollable-cards">
                {/* Dummy card components */}
                <div className="card">Player 1 Info</div>
                <div className="card">Player 2 Info</div>
                <div className="card">Player 3 Info</div>
                <div className="card">Player 4 Info</div>
                <div className="card">Player 5 Info</div>
                <div className="card">Player 6 Info</div>
                <div className="card">Player 7 Info</div>
                <div className="card">Player 8 Info</div>
                <div className="card">Player 9 Info</div>
                {/* Add more dummy cards as needed */}
              </div>
            </div>
            <div className="column">
              <h2>Team Records</h2>
              {/* Dummy team records data */}
              <p>Team records data goes here...</p>
            </div>
            <div className="column">
              <h2>Reports</h2>
              {/* Dummy reports data */}
              <p>Reports data goes here...</p>
            </div>
          </div>
          <div className="right-column">
            <div className="comparison">
              <h2>Player Comparison</h2>
              <div className="comparison-cards">
                {selectedPlayers.map((playerId, index) => (
                  <div key={index} className="card">
                    <select
                      value={playerId || ''}
                      onChange={e => handlePlayerSelectionChange(index, e.target.value)}
                    >
                      <option value="">Select a Player</option>
                      {playerOptions}
                    </select>
                  </div>
                ))}
              </div>
            </div>
            <div className="stats-graph">
              <h2>Stats Graph</h2>
              {/* Fake graph */}
              <div className="fake-baseball-graph">
                <div className="graph-title">Baseball Stats</div>
                <div className="graph">
                  <div className="bar" style={{ height: '70%' }}></div>
                  <div className="bar" style={{ height: '20%' }}></div>
                  <div className="bar" style={{ height: '95%' }}></div>
                  <div className="bar" style={{ height: '55%' }}></div>
                  <div className="bar" style={{ height: '35%' }}></div>

                  {/* Add more bars as needed */}
                </div>
                <div className="graph-labels">
                  <span>Player 1</span>
                  <span>Player 2</span>
                  <span>Player 3</span>
                  <span>Player 4</span>
                  <span>Player 5</span>
                  {/* Add more labels as needed */}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Insights;
