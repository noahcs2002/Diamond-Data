import React, { useEffect, useState } from 'react';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import LoadingScreen from '../components/LoadingScreen'

function PlayerManagement({ onEdit }) {
  const [offensiveData, setOffensiveData] = useState([]);
  const [rawPlayerData, setRawPlayerData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(() => JSON.parse(localStorage.getItem('selectedTeam')) || ''); // Initialize from localStorage
  const [isAddingPlayer, setIsAddingPlayer] = useState(false);
  const [newPlayerFirstName, setNewPlayerFirstName] = useState('');
  const [newPlayerLastName, setNewPlayerLastName] = useState('');
  const [selectedPositions, setSelectedPositions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    proc()
  }, []);

  const proc = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    const teams = await fetchTeams(user);
    await fetchPlayers(user, selectedTeam || teams[0]);
    setLoading(false);
  }


  const allPositions = ["C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"];

  const fetchTeams = async (user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
    const url = new URL(endpoint);
    url.searchParams.append("userId", user.id);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error: ');
      }
      const data = await response.json();
      setTeams(data);
      setSelectedTeam(data[0]);
      localStorage.setItem('selectedTeam', JSON.stringify(data[0])); 
      return data
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  const fetchPlayers = async (user, team) => {
   // if (!selectedTeam) return;
    const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append("userId", user.id);
    url.searchParams.append('teamId', team.id);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("Network error");
      }
      const data = await response.json();
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
                id: 1203,
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
            positions: selectedPositions,
            team: {
                id: 1203,
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
        },
        assignment: 'n/a'
    };

    const endpoint = `http://localhost:8080/diamond-data/api/players/create`;
    const url = new URL(endpoint);
    url.searchParams.append('teamId', 1203);
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerCreationRequestModel),
      });
      if (!response.ok) {
        throw new Error("Failed to create player");
      }
      const newPlayer = await response.json();

      setOffensiveData((prev) => [...prev, newPlayer]);
    } catch (error) {
      console.error("Error creating player:", error);
    }
    setIsAddingPlayer(false);
    setNewPlayerFirstName('');
    setNewPlayerLastName('');
    setSelectedPositions([]);
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
    // Extract first name and last name from updated full name
    const updatedFirstName = updatedFullName.split(' ')[0];
    const updatedLastName = updatedFullName.split(' ')[1];

    const playerUpdateRequestModel = {
      firstName: updatedFirstName,
      lastName: updatedLastName,
    };

    const endpoint = `http://localhost:8080/diamond-data/api/players/update`;
    const url = new URL(endpoint);
    url.searchParams.append('id', playerId);

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
    await fetchPlayers(); // Optionally update local state after successful update
  } catch (error) {
    console.error('Error updating player:', error);
  }
};

const handlePositionChange = (position) => {
  setSelectedPositions(prev => {
    if (prev.includes(position)) {
      return prev.filter(p => p !== position);
    } else {
      return [...prev, position];
    }
  });
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
        {loading ? <LoadingScreen/> : <>
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
              <div className="positionsSelection">
                <label>Positions:</label>
                {allPositions.map((position) => (
                  <div key={position}>
                    <input type="checkbox" id={position} name={position} value={position} checked={selectedPositions.includes(position)} onChange={() => handlePositionChange(position)} />
                    <label htmlFor={position}>{position}</label>
                  </div>
                ))}
              </div>
              <button onClick={handlePlayerCreate}>Save</button>
              <button onClick={() => setIsAddingPlayer(false)}>Cancel</button>
            </div>
          )}
        </div> </>}
      </div>
  );
}

export default PlayerManagement;
