import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'

function Roster() {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');

  useEffect(() => {
    // dummy data
    const mockRoster = [
      { name: 'Player 1', position: 'Catcher' },
      { name: 'Player 2', position: 'First Baseman' },
      { name: 'Player 3', position: 'Second Baseman' },
      { name: 'Player 4', position: 'Shortstop' },
      { name: 'Player 5', position: 'Third Baseman' },
      { name: 'Player 6', position: 'Left Field' },
      { name: 'Player 7', position: 'Center Field' },
      { name: 'Player 8', position: 'Right Field' },
      { name: 'Player 9', position: 'Pitcher' },
      { name: 'Player 10', position: 'DH' },
      { name: 'Player 11', position: 'Pitcher' },
      { name: 'Player 12', position: 'Pitcher' },
    ];

    // Set dummy data for each column
    setOffensiveData(mockRoster.slice(0, 15)); 
    setDefensiveData(mockRoster.slice(10, 20));
    setPitcherData(mockRoster.slice(10)); 
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

  const fetchData = async () => {
    const id = '202';
    const endpoints = [
      'http://localhost:8080/diamond-data/api/offensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/defensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/pitchers/get-by-team'
    ];
  }
    
  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
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
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
      {/* Headers for the columns */}
      <div className="columnHeaders">
        <h2>40 Man Roster</h2>
        <h2>Line-Up</h2>
        <h2>Bench</h2>
        <h2>Pitching Rotation</h2>
        <h2> Injured List</h2>
      </div>
      <div className="columns">
          <div className="fixedColumn">
            <div className="playerListContainer">
              <div className="playerList">
                {/* Render 40 man roster */}
                {offensiveData.map((player, index) => (
                  <div key={index} className="playerCard">{player.name} - {player.position}</div>
                ))}
              </div>
            </div>
          </div>
          <div className="column">
            <div className="playerGrid">
              {/* Render lineup */}
              {/* Add your lineup rendering logic here */}
            </div>
          </div>
          <div className="column">
            <div className="playerGrid">
              {/* Render bench */}
              {/* Add your bench rendering logic here */}
            </div>
          </div>
          <div className="column">
            <div className="playerGrid">
              {/* Render pitching rotation */}
              {/* Add your pitching rotation rendering logic here */}
            </div>
          </div>
          <div className="column">
            <div className="playerGrid">
              {/* Render injured list */}
              {/* Add your injured list rendering logic here */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Roster;