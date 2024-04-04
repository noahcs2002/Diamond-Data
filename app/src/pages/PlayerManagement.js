import React, { useEffect, useState } from 'react';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import { useTable } from 'react-table';
import AddCircleIcon from '@mui/icons-material/AddCircle';

function PlayerManagement({ onEdit }) {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [rawPlayerData, setRawPlayerData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [isAddingPlayer, setIsAddingPlayer] = useState(false);
  const [newPlayerFirstName, setNewPlayerFirstName] = useState('');
  const [newPlayerLastName, setNewPlayerLastName] = useState('');

  useEffect(() => {
    fetchPlayers();
    fetchTeams();
  }, [selectedTeam]);

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

  const fetchPlayers = async () => {
    if (!selectedTeam) return;
    const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append("userId", 302);
    url.searchParams.append('teamId', selectedTeam);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("Network error");
      }
      const data = await response.json();
      // You need to decide how you want to use this data to update your state
      // For example, if the API returns offensive and defensive data separately,
      // you might need to update setOffensiveData and setDefensiveData here
    } catch (error) {
      console.error('Error fetching players:', error);
    }
  };

  const handlePlayerCreate = async () => {
    const playerCreationRequestModel = {
      firstName: newPlayerFirstName,
      lastName: newPlayerLastName,
      teamId: selectedTeam,
    };
    const endpoint = 'http://localhost:8080/diamond-data/api/players/create';
    const url = new URL(endpoint);
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(playerCreationRequestModel)
      });
      if (!response.ok) {
        throw new Error("Failed to create player");
      }
      const newPlayer = await response.json();
      // Assuming you want to add the new player to offensiveData for display
      // You might need to adjust based on your actual data structure and requirements
      setOffensiveData(prev => [...prev, newPlayer]);
    } catch (error) {
      console.error("Error creating player:", error);
    }
    // Reset the form and close the modal
    setIsAddingPlayer(false);
    setNewPlayerFirstName('');
    setNewPlayerLastName('');
  };

  const handleDelete = (fullName) => {
    console.log('Player deleted', fullName);
    // Filter out the player from offensiveData, defensiveData, and pitcherData
    // Adjust these as necessary based on how you're structuring your data
    setOffensiveData(data => data.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
    setDefensiveData(data => data.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
    setPitcherData(data => data.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
  };

  const handleEdit = (oldFullName, newFullName) => {
    console.log('Player edited', oldFullName, 'to', newFullName);
    // Implement the logic to find the player by oldFullName and update their name to newFullName
    // This logic would be similar to handleDelete, but instead of filtering out, you'd update the name
  };

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  return (
    <div>
      <Navbar />
      <div className="playerManagement">
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
        <h1 className='title'>Player Management</h1>
        <div className="positionList">
          <div className='positionContainer'>
            <h2>Position Players</h2>
            <div className='icons'>
              <AddCircleIcon onClick={() => setIsAddingPlayer(true)} className='addCircleIcon' />
            </div>
            <div className='playerGrid'>
              {offensiveData.map(player => (
                <div className='playerItem' key={player.id}>
                  <PlayerItem
                    key={player.id}
                    fullName={`${player.firstName} ${player.lastName}`}
                    onDelete={() => handleDelete(`${player.firstName} ${player.lastName}`)}
                    onEdit={(newFullName) => handleEdit(`${player.firstName} ${player.lastName}`, newFullName)}
                  />
                </div>
              ))}
            </div>
          </div>
        </div>
        {isAddingPlayer && (
          <div className="addPlayerModal">
            <input
              type="text"
              placeholder="First Name"
              value={newPlayerFirstName}
              onChange={e => setNewPlayerFirstName(e.target.value)}
            />
            <input
              type="text"
              placeholder="Last Name"
              value={newPlayerLastName}
              onChange={e => setNewPlayerLastName(e.target.value)}
            />
            <button onClick={handlePlayerCreate}>Save</button>
            <button onClick={() => setIsAddingPlayer(false)}>Cancel</button>
          </div>
        )}
      </div>
    </div>
  );
}

export default PlayerManagement;
