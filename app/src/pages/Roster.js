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
    const user = JSON.parse(localStorage.getItem('sessionData'));
    console.log('Gathered user: ', user);
    prop(user)
  }, []);

  const prop = async (user) => {
    const teams = await fetchTeams(user.id);
    console.log('Fetched teams: ', teams);
    const data = await handleRosterLoad();
  }

  const fetchTeams = async (userId) => {
    console.log('UserId: ', userId);
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
    const url = new URL(endpoint);
    url.searchParams.append("userId", userId);
    console.log('Team fetch URL: ', url);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error');
      }
      const data = await response.json();
      setTeams(data);
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  const handleRosterLoad = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));

    const endpoint = 'http://localhost:8080/diamond-data/api/rosters/get';
    const url = new URL(endpoint);
    
    if (teams.length === 1) {
      url.searchParams.append('teamId', teams[0].id);
    }
    else {
      url.searchParams.append('teamId', selectedTeam.id);
    }

    url.searchParams.append('userId', user.id);

    try {
      const response = await fetch(url);
      if(!response.ok) {
        alert('Invalid request');
      }
      else {
        const data = await response.json();
        localStorage.setItem('players', JSON.stringify(data));
        return data;
      }
    }
    catch(err) {
      console.error('Error fetching teams: ', err)
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