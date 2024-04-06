import React, { useEffect, useState } from 'react';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import AddCircleIcon from '@mui/icons-material/AddCircle';

function PlayerManagement({ onEdit }) {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [rawPlayerData, setRawPlayerData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(() => localStorage.getItem('selectedTeam') || ''); // Initialize from localStorage
  const [isAddingPlayer, setIsAddingPlayer] = useState(false);
  const [newPlayerFirstName, setNewPlayerFirstName] = useState('');
  const [newPlayerLastName, setNewPlayerLastName] = useState('');

  useEffect(() => {
    fetchTeams();
    fetchPlayers();
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
   // if (!selectedTeam) return;
    const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append("userId", 302);
    url.searchParams.append('teamId', 1152);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("Network error");
      }
      const data = await response.json();
      console.log(data);
      setRawPlayerData(data);
    } catch (error) {
      console.error('Error fetching players:', error);
    }
  };

  const handlePlayerCreate = async () => {
    const playerCreationRequestModel = {
        firstName: newPlayerFirstName,
        lastName: newPlayerLastName,
        offensivePlayer: {
            team: {
                id: 1152,
                name: ''
            },
            atBats: 0,
            battingAverage: 0,
            caughtStealing: 0,
            doubles: 0,
            extraBaseHits: 0,
            gamesPlayed: 0,
            grandSlams: 0,
            groundIntoDoublePlay: 0,
            groundOutAirOut: 0,
            hitByPitch: 0,
            hits: 0,
            homeRuns: 0,
            intentionalWalks: 0,
            leftOnBase: 0,
            onBasePercentage: 0,
            onBasePlusSlugging: 0,
            plateAppearances: 0,
            reachedOnError: 0,
            runs: 0,
            runsBattedIn: 0,
            sacrificeBunt: 0,
            sacrificeFly: 0,
            singles: 0,
            sluggingPercentage: 0,
            stolenBases: 0,
            totalBases: 0,
            triples: 0,
            walks: 0,
            walkOffs: 0
        },
        defensivePlayer: {
            positions: [],
            team: {
                id: 1152,
                name: ''
            },
            assists: 0,
            caughtStealingPercent: 0,
            doublePlay: 0,
            errors: 0,
            fieldingPercentage: 0,
            inningsPlayed: 0,
            outs: 0,
            outfieldAssists: 0,
            passedBalls: 0,
            putouts: 0,
            totalChances: 0,
            triplePlays: 0
        }
    };

    const endpoint = `http://localhost:8080/diamond-data/api/players/create`;
    const url = new URL(endpoint);
    url.searchParams.append('teamId', 1152);
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerCreationRequestModel),
      });
      console.log(response);
      if (!response.ok) {
        throw new Error("Failed to create player");
      }
      const newPlayer = await response.json();
      console.log(newPlayer);

      setOffensiveData((prev) => [...prev, newPlayer]);
    } catch (error) {
      console.error("Error creating player:", error);
    }
    setIsAddingPlayer(false);
    setNewPlayerFirstName('');
    setNewPlayerLastName('');
};

const handleDeletePlayer = async (id) => {
  const url = new URL(`http://localhost:8080/diamond-data/api/players/delete`);
  url.searchParams.append('id', id);

  try {
    const response = fetch(url, { method: 'DELETE' });

    if (!response.ok) {
      throw new Error(`Failed to delete player with id: ${id}`);
    }

    await fetchPlayers(); 
  } catch (error) {
    console.error('Error deleting player:', error);
  }
};

const handleDelete = async (id) => {
  try {
    await handleDeletePlayer(id);
    console.log('Player deleted successfully');
  } catch (error) {
    console.error('Error deleting player:', error);
  }
};


const handleEdit = async (playerId, updatedFullName) => {
  try {
    const endpoint = `http://localhost:8080/diamond-data/api/players/update`;
    const url = new URL(endpoint);
    url.searchParams.append('id', playerId);
    console.log(updatedFullName);
    const playerUpdateRequestModel = {
      firstName: updatedFullName.split(' ')[0],
      lastName: updatedFullName.split(' ')[1],
    };

    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(playerUpdateRequestModel),
    });

    if (!response.ok) {
      throw new Error(`Failed to update player with id: ${playerId}`);
    }

    console.log('Player updated successfully');
    await fetchPlayers();
  } catch (error) {
    console.error('Error updating player:', error);
  }
};




  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const handleTeamSelect = (e) => {
    const teamId = e.target.value;
    setSelectedTeam(teamId);
    localStorage.setItem('selectedTeam', teamId);
  };

  return (
    <div>
      <Navbar />
      <div className="playerManagement">
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={handleTeamSelect} 
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
              {rawPlayerData.map(player => (
                <div className='playerItem' key={player.id}>
                  <PlayerItem
                    key={player.id}
                    fullName={`${player.firstName} ${player.lastName}`}
                    onDelete={() => handleDelete(player.id)} 
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
