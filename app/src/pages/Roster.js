import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'

function Roster() {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(() => localStorage.getItem('selectedTeam') || '');
  const [players, setPlayers] = useState([]);
 

  useEffect(() => {
    const savedPlayers = localStorage.getItem('players');
    if (savedPlayers) {
      setPlayers(JSON.parse(savedPlayers));
    } else {
      const mockRoster = [
        { id: 'player-1', name: 'Player 1', position: 'Catcher', assignment: '40 Man Roster' },
        { id: 'player-2', name: 'Player 2', position: 'First Baseman', assignment: 'Line-Up' },
      ];
      setPlayers(mockRoster);
    }
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
    const url = new URL(endpoint);
    url.searchParams.append("userId", 302);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error');
      }
      const data = await response.json();
      setTeams(data);
    } catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  
  const handleTeamSelect = (e) => {
    const teamId = e.target.value;
    setSelectedTeam(teamId);
    localStorage.setItem('selectedTeam', teamId);
  };
    
  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));


  const handleDragStart = (e, playerId) => {
    e.dataTransfer.setData("playerId", playerId);
  };

  const handleDrop = (e, newAssignment) => {
    e.preventDefault();
    const playerId = e.dataTransfer.getData("playerId");
    setPlayers(prevPlayers => {
      const updatedPlayers = prevPlayers.map(player => 
        player.id === playerId ? { ...player, assignment: newAssignment } : player
      );
  
     
      localStorage.setItem('players', JSON.stringify(updatedPlayers));
      return updatedPlayers;
    });
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const getPlayerList = (assignment) =>
    players.filter(player => player.assignment === assignment).map((player) => (
      <div key={player.id}
           className="playerCard"
           draggable
           onDragStart={(e) => handleDragStart(e, player.id)}>
        {player.name} - {player.position}
      </div>
    ));


    return (
      <div>
        <Navbar />
        <div className="roster">
          <h1 className="title">Roster</h1>
          <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={handleTeamSelect} 
          >
            {teamOptions}
          </select>
        </div>
          <div className="columnHeaders">
            <h2>40 Man Roster</h2>
            <h2>Line-Up</h2>
            <h2>Bench</h2>
            <h2>Pitching Rotation</h2>
            <h2>Injured List</h2>
          </div>
          <div className="columns">
            <div className="fixedColumn"
                 onDrop={e => handleDrop(e, '40 Man Roster')}
                 onDragOver={handleDragOver}>
              <div className="playerListContainer">
                <div className="playerList">
                  {getPlayerList('40 Man Roster')}
                </div>
              </div>
            </div>
            <div className="column"
                 onDrop={e => handleDrop(e, 'Line-Up')}
                 onDragOver={handleDragOver}>
              <div className="playerGrid">
                {getPlayerList('Line-Up')}
              </div>
            </div>
            <div className="column"
                 onDrop={e => handleDrop(e, 'Bench')}
                 onDragOver={handleDragOver}>
              <div className="playerGrid">
                {getPlayerList('Bench')}
              </div>
            </div>
            <div className="column"
                 onDrop={e => handleDrop(e, 'Pitching Rotation')}
                 onDragOver={handleDragOver}>
              <div className="playerGrid">
                {getPlayerList('Pitching Rotation')}
              </div>
            </div>
            <div className="column"
                 onDrop={e => handleDrop(e, 'Injured List')}
                 onDragOver={handleDragOver}>
              <div className="playerGrid">
                {getPlayerList('Injured List')}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
  
  export default Roster;