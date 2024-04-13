import React, { useEffect, useState } from 'react';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import LoadingScreen from '../components/LoadingScreen'

function PlayerManagement() {
  const [rawPlayerData, setRawPlayerData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [isAddingPlayer, setIsAddingPlayer] = useState(false);
  const [newPlayerFirstName, setNewPlayerFirstName] = useState('');
  const [newPlayerLastName, setNewPlayerLastName] = useState('');
  const [selectedPositions, setSelectedPositions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    prop()
  }, []);

  const prop = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    const team = await fetchTeam(user);
    await fetchPlayers(user, team);
    await fetchPitchers(user, team);
    setLoading(false);
  }

  const allPositions = ["C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"];

  const fetchTeam = async (user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-by-user';
    const url = new URL(endpoint);
    url.searchParams.append("userId", user.id);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error: ');
      }
      const data = await response.json();
      localStorage.setItem('team', data);
      return data
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  const fetchPitchers = async (user, team) => {
    setLoading(true);
    const endpoint = 'http://localhost:8080/diamond-data/api/pitchers/get-by-team'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);

      if(!res.ok) {
        alert('Res not ok');
      }

      const data = await res.json();
      setPitcherData(data);
      return data;
    }
    catch(_e) {
      alert(_e);
    }
    setLoading(false);
  }

  const fetchPlayers = async (user, team) => {
    setLoading(true)
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
    } 
    catch (error) {
      console.error('Error fetching players:', error);
    }
    setLoading(false);
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

    const user = JSON.parse(localStorage.getItem('sessionData'));
    const team = await fetchTeam(user)

    const endpoint = `http://localhost:8080/diamond-data/api/players/create`;
    const url = new URL(endpoint);
    url.searchParams.append('teamId', team.id);
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

    } 
    catch (error) {
      console.error("Error creating player:", error);
    }
    setIsAddingPlayer(false);
    setNewPlayerFirstName('');
    setNewPlayerLastName('');
    setSelectedPositions([]);
    window.location.reload();
  };

  const handleDeletePlayer = async (id) => {
    setLoading(true);
    const url = new URL(`http://localhost:8080/diamond-data/api/players/delete`);
    url.searchParams.append('id', id);

    try {
      const response = await fetch(url, { method: 'DELETE' });
      console.log(response);
    } 
    catch (error) {
      console.error('Error deleting player in handleDeletePlayer:', error);
    }
    window.location.reload()
  };


const handleEditPlayer = async (playerId, updatedFullName) => {
  setLoading(true)
  try {
    console.log('Updated full name: ', updatedFullName);

    const [first, last] = updatedFullName.split(' ');
    first.trim();
    last.trim();

    const firstNameEndpoint = `http://localhost:8080/diamond-data/api/players/change-first-name`;
    const lastNameEndpoint = `http://localhost:8080/diamond-data/api/players/change-last-name`;

    const firstNameURL = new URL(firstNameEndpoint);
    const lastNameURL = new URL(lastNameEndpoint);

    firstNameURL.searchParams.append('id', playerId);
    lastNameURL.searchParams.append('id', playerId);

    firstNameURL.searchParams.append('newFirstName', first)
    lastNameURL.searchParams.append('newLastName', last)

    try {
      const firstNameRes = await fetch(firstNameURL, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!firstNameRes.ok) {
        console.log('error');
      }

      const lastNameRes = await fetch(lastNameURL, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!firstNameRes.ok) {
        console.log('error');
      }
    }
    catch(_e) {
      console.log(_e);
    }

  } 
  catch (error) {
    console.log('Error updating player:', error);
  }
  try {
    window.location.reload();
  }
  catch(_e) {

  }
};

const handleEditPitcher = async (playerId, updatedFullName) => {
  setLoading(true);
  try {
    console.log('New name: ', updatedFullName)
    console.log('Player id: ', playerId);
    // Extract first name and last name from updated full name
    const [first, last] = updatedFullName.split(' ');

    const endpoint = `http://localhost:8080/diamond-data/api/pitchers/change-name`;
    const url = new URL(endpoint);
    url.searchParams.append('id', playerId);
    url.searchParams.append('firstName', first)
    url.searchParams.append('lastName', last)
    url.searchParams.append('userId', JSON.parse(localStorage.getItem('sessionData')).id)

    const res = await fetch(url);
    
    if (!res.ok) {
      console.log('Res not ok: ', res)
    }

    setLoading(false);
    window.location.reload();
  } 
  catch (error) {
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

  return (
      <div>
        {loading ? <LoadingScreen/> : <>
        <Navbar />
        <div className="playerManagement">
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
                      onDelete={() => handleDeletePlayer(player.id)} 
                      onEdit={() => handleEditPlayer(player.id, localStorage.getItem('updatedName'))}
                    />
                  </div>
                ))}
              </div>
            </div>
            <div className='positionContainer'>
              <h2>Pitchers</h2>
              <div className='icons'>
                <AddCircleIcon onClick={() => setIsAddingPlayer(true)} className='addCircleIcon' />
              </div>
              <div className='playerGrid'>
                {pitcherData.map(player => (
                  <div className='playerItem' key={player.id}>
                    <PlayerItem
                      key={player.id}
                      fullName={`${player.firstName} ${player.lastName}`}
                      // Make deletePitcher eventually
                      onDelete={() => handleDeletePlayer(player.id)} 
                      onEdit={() => handleEditPitcher(player.id, localStorage.getItem('updatedName'))}
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
        <Footer />
      </div>
  );
}

export default PlayerManagement;
