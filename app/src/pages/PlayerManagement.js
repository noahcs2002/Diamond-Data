import React, { useEffect, useState } from 'react';
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import LoadingScreen from '../components/LoadingScreen'
import SavingScreen from '../components/SavingScreen'
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function PlayerManagement() {
  const [rawPlayerData, setRawPlayerData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [isAddingPlayer, setIsAddingPlayer] = useState(false);
  const [newPlayerFirstName, setNewPlayerFirstName] = useState('');
  const [newPlayerLastName, setNewPlayerLastName] = useState('');
  const [selectedPositions, setSelectedPositions] = useState([]);
  const [isAddingPitcher, setIsAddingPitcher] = useState(false);
  const [loading, setLoading] = useState(true);
  const [pitcherPref, setPitcherPref] = useState('');
  const [playerCreationCount, setPlayerCreationCount] = useState(0);
  const [saving, setSaving] = useState(false);
  const [changes, setChanges] = useState(0);
  const nav = useNavigate();

  useEffect(() => {
    prop()
  },[]);

  const prop = async () => {
    let team = {};
    let players = [];
    let pitchers = {};
    const user = await JSON.parse(localStorage.getItem('sessionData'));

    if(user === undefined || user === null) {
      nav('/')
      return;
    }

    try {
      team = await JSON.parse(localStorage.getItem('cachedTeam'));

      if (team === null || team === undefined) {
        throw new Error();
      }
    }
    catch {
      team = await fetchTeam(user);
    }

    try {
      players = await JSON.parse(localStorage.getItem('cachedPlayers'))
      if(players === null || players === undefined) {
        throw new Error();
      }
    }
    catch {
      players = await fetchPlayers(user, team);
    }

    try {
      pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));
      if (pitchers === null || pitchers === undefined) {
        throw new Error();
      }
    }
    catch {
      pitchers = await fetchPitchers(user, team);
    }

    if(team === undefined || pitchers === undefined || players === undefined) {
      window.location.reload();
    }

    setPitcherData(pitchers);
    setRawPlayerData(players);
    setLoading(false);
    toast.success('Data loaded successfully!', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
    })
  }

  const allPositions = ["C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "DH"];

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
      localStorage.setItem('cachedTeam', data);
      return data
    } 
    catch (error) {
      toast.error('Error fetching team, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  };

  const fetchPitchers = async (user, team) => {
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
      localStorage.setItem('cachedPitchers', JSON.stringify(data));
      return data;
    }
    catch(_e) {
      toast.error('Error fetching pitchers, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
    setLoading(false);
  }

  const fetchPlayers = async (user, team) => {
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
      localStorage.setItem('cachedPlayers', JSON.stringify(data));
      return data;
    } 
    catch (error) {
      toast.error('Error fetching players, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
    setLoading(false);
  };

  const handlePlayerCreate = async () => {
    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    const playerCreationRequestModel = {
        id: playerCreationCount,
        firstName: newPlayerFirstName,
        lastName: newPlayerLastName,
        offensivePlayer: {
            team: {
                id: 0,
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
            strikeout: 0,
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
                id: 0,
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

    const players = await JSON.parse(localStorage.getItem('cachedPlayers'));
    players.push(playerCreationRequestModel);
    localStorage.setItem('cachedPlayers', JSON.stringify(players));
    setRawPlayerData(players);

    setIsAddingPlayer(false);
    setNewPlayerFirstName('');
    setNewPlayerLastName('');
    setSelectedPositions([]);
    setPlayerCreationCount(playerCreationCount + 1);
  };

  const handlePitcherCreate = async () => {
    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    const pitcher = {
      "firstName": newPlayerFirstName,
      "lastName": newPlayerLastName,
      "preference": pitcherPref,
      "team" : {
        "name": '',
        "id": 0
      },
      "appearances": 0,
      "balks": 0,
      "battersFaced": 0,
      "blownSaves": 0,
      "completeGames": 0,
      "earnedRuns": 0,
      "earnedRunAverage": 0.0,
      "flyouts": 0,
      "gamesFinished": 0,
      "gamesStarted": 0,
      "groundouts": 0,
      "holds": 0,
      "hits": 0,
      "inheritedRunners": 0,
      "inningsPitched": 0.0,
      "losses": 0,
      "numberOfPitches": 0,
      "pickoffs": 0,
      "qualityStarts": 0,
      "reliefWins": 0,
      "saves": 0,
      "saveOpportunities": 0,
      "savePercentage": 0.0,
      "shutouts": 0,
      "strikeouts": 0,
      "unearnedRuns": 0,
      "walksAndHitsPerInningPitched": 0.0,
      "wildPitches": 0,
      "wins": 0,
      "winningPercentage": 0.0,
      "assignment": "n/a"
    }

    const pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));
    pitchers.push(pitcher);
    localStorage.setItem('cachedPitchers', JSON.stringify(pitchers));
    setPitcherData(pitchers);
    setPitcherPref('');
    setIsAddingPitcher(false);
    setNewPlayerFirstName('')
    setNewPlayerLastName('')
    setPlayerCreationCount(playerCreationCount + 1);
  }

  const handleDeletePlayer = async (id) => {

    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    const players = await JSON.parse(localStorage.getItem('cachedPlayers'));

    const player = players.filter(p => p.id === id)[0]; 
    
    let deletedPlayers = JSON.parse(localStorage.getItem('deletedPlayers')) || [];
    deletedPlayers.push(player);
    localStorage.setItem('deletedPlayers', JSON.stringify(deletedPlayers));

    const newPlayers = players.filter(item => item.id !== id);

    console.log(newPlayers);
    setRawPlayerData(newPlayers);
    localStorage.setItem('cachedPlayers', JSON.stringify(newPlayers));
  };

  const handleDeletePitcher = async (id) => {
    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    console.log(id);
    const pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));

    const pitcher = pitchers.filter(p => p.id === id)[0];
    console.log(pitcher); 
  
    let deletedPitchers= JSON.parse(localStorage.getItem('deletedPitchers'))
    
    if (deletedPitchers === null | deletedPitchers === undefined) {
      deletedPitchers = [];
    }
    deletedPitchers.push(pitcher);
    localStorage.setItem('deletedPitchers', JSON.stringify(deletedPitchers));

    const newPitchers = pitchers.filter(p => p !== pitcher);
    console.log(newPitchers);
    setPitcherData(newPitchers);
    localStorage.setItem('cachedPitchers', JSON.stringify(newPitchers ));
    console.log('deleted pitchers: ', deletedPitchers);
  }

  const handleEditPlayer = async (playerId, updatedFullName) => {
    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    const players = await JSON.parse(localStorage.getItem('cachedPlayers'));
    const [first, last] = updatedFullName.split(' ');

    players.map((p) => {
      if (p.id === playerId) {
        p.firstName = first;
        p.lastName = last;
      }
    })

    setRawPlayerData(players);
    localStorage.setItem('cachedPlayers', JSON.stringify(players));
  };

  const handleEditPitcher = async (playerId, updatedFullName) => {
    setChanges(changes + 1)
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss();
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })
    }
    const pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));
    const [first, last] = updatedFullName.split(' ');

    pitchers.map((p) => {
      if (p.id === playerId) {
        p.firstName = first;
        p.lastName = last;
      }
    })

    setPitcherData(pitchers);
    localStorage.setItem('cachedPitchers', JSON.stringify(pitchers)); 
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

  const saveChanges = async () => {
    setSaving(true);
    setChanges(0);
    toast.loading('Saving player changes', {
      position:'bottom-right',
      hideProgressBar:true,
    })
    const user = await JSON.parse(localStorage.getItem('sessionData'));

    let team = {};

    try {
      team = await JSON.parse(localStorage.getItem('cachedTeam'));
      if (team === undefined || team === null) {
        throw new Error();
      }
    }
    catch {
      team = await fetchTeam(user);
    }

    const endpoint = 'http://localhost:8080/diamond-data/api/rosters/bulk-player-changes';
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    const res = await fetch(url, {
       body: JSON.stringify({players: rawPlayerData, pitchers: pitcherData}),
       method: 'PUT',
       headers: {
        'Content-Type':'application/json'
       }
    })

    const deletePitcherEndpoint = 'http://localhost:8080/diamond-data/api/rosters/bulk-delete-pitchers'
    const deletePlayerEndpoint = 'http://localhost:8080/diamond-data/api/rosters/bulk-delete-players'

    const deletePitcherUrl = new URL(deletePitcherEndpoint);
    const deletePlayerUrl = new URL(deletePlayerEndpoint);

    deletePitcherUrl.searchParams.append('userId', user.id);
    deletePitcherUrl.searchParams.append('teamId', team.id);
    
    deletePlayerUrl.searchParams.append('userId', user.id);
    deletePlayerUrl.searchParams.append('teamId', team.id); 

    await fetch(deletePitcherUrl, {
      method: 'DELETE',
      body: localStorage.getItem('deletedPitchers'),
      headers: {
        'Content-Type':'application/json'
      } 
    })

    await fetch(deletePlayerUrl, {
      method: 'DELETE',
      body: localStorage.getItem('deletedPlayers'),
      headers: {
        'Content-Type':'application/json'
      } 
    })

    const newPlayers = await fetchPlayers(user, team);
    const newPitchers = await fetchPitchers(user, team);
    console.log(newPlayers);

    localStorage.setItem('cachedPitchers', JSON.stringify(newPitchers));
    localStorage.setItem('cachedPlayers', JSON.stringify(newPlayers));
    localStorage.setItem('deletedPitchers', JSON.stringify([]));
    localStorage.setItem('deletedPlayers', JSON.stringify([]));

    setPlayerCreationCount(0);
    setSaving(false);
    toast.dismiss();
    toast.success('Players saved successfully!', {
      position:'bottom-right',
      autoClose: 2500,
      hideProgressBar:true,
      closeOnClick:true 
    })
  }

  return (
      <div>
        <ToastContainer/>
        {loading ? <LoadingScreen/> : <>
        {saving ? <SavingScreen/> : <>
        <Navbar />
        <div className="playerManagement">
          <h1 className='title'>Player Management</h1>
          <div className="positionList">
            <div className='positionContainer'>
              <div className='buttonWrapper'>
                <button  className='playerManagementButton'onClick={saveChanges}>Save Changes</button>
              </div>
              <h2>Position Players</h2>
              <div className='icons'>
                {playerCreationCount < 15 ? <AddCircleIcon onClick={() => setIsAddingPlayer(true)} className='addCircleIcon' /> : <> <div>Please save your changes to add a new player</div></> }
              </div>
              <div className='playerGrid'>
                {rawPlayerData.map(player => (
                  <div className='playerItem' key={player.id}>
                    <PlayerItem
                      key={player.id}
                      fullName={`${player.firstName} ${player.lastName}`}
                      onDelete={() => handleDeletePlayer(player.id)} 
                      onEdit={() => handleEditPlayer(player.id, localStorage.getItem('updatedName'))}
                      positions={player.defensivePlayer.positions}
                    />
                  </div>
                ))}
              </div>
            </div>
            <div className='positionContainer'>
              <h2>Pitchers</h2>
              <div className='icons'>
                {playerCreationCount < 15 ? <AddCircleIcon onClick={() => setIsAddingPitcher(true)} className='addCircleIcon' />: <div>Please save your changes to add a new pitcher </div>}
              </div>
              <div className='playerGrid'>
                {pitcherData.map(player => (
                  <div className='playerItem' key={player.id}>
                    <PlayerItem
                      key={player.id}
                      fullName={`${player.firstName} ${player.lastName}`}
                      onDelete={() => handleDeletePitcher(player.id)} 
                      onEdit={() => handleEditPitcher(player.id, localStorage.getItem('updatedName'))}
                    />
                  </div>
                ))}
              </div>
            </div>
          </div>
          {isAddingPlayer && (
            <div className="modal">
              <div className='modalContent'>
                <span className="close" onClick={() => setIsAddingPlayer(false)}>&times;</span>
                <h2>Player Name:</h2>
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
                  <h2>Positions:</h2>
                  {allPositions.map((position) => (
                    <div key={position}>
                      <input  className='checkboxes' type="checkbox" id={position} name={position} value={position} checked={selectedPositions.includes(position)} onChange={() => handlePositionChange(position)} />
                      <label htmlFor={position}>{position}</label>
                    </div>
                  ))}
                </div>
                <button onClick={handlePlayerCreate}>Save</button>
              </div>
            </div>
          )}
          {isAddingPitcher && 
          <div className='modal'>
            <div className='modalContent'>
              <span className="close" onClick={() => setIsAddingPitcher(false)}>&times;</span>
              <h2>Pitcher Name:</h2>
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
                <div className='handSelections'>
                  <h2>Left or Right Handed:</h2>
                  <input type='radio' id='right-handed-checkbox' name='preference-checkbox' onChange={() => {setPitcherPref('R')}}/>
                  <label> Right Handed </label>

                  <input type='radio' id='left-handed-checkbox' name='preference-checkbox' onChange={() => {setPitcherPref('L')}}/>
                  <label> Left Handed </label>
                </div>
                <button onClick={handlePitcherCreate}>Submit</button>
              </div>
          </div>
          }
        </div> </>}</>}
        <Footer />
      </div>
  );
}

export default PlayerManagement;