import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer';
import LoadingScreen from '../components/LoadingScreen';
import SavingScreen from '../components/SavingScreen';

function Roster() {
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    prop(user)
  }, []);

  const prop = async (user) => {

    let team = undefined 
    let players = undefined
    let pitchers = undefined
    let finalised = undefined
    let assignedPitchers = undefined
    let combined = undefined

    try {
      team = await JSON.parse(localStorage.getItem('cachedTeam'));

      if (team === null || team === undefined) {
        throw new Error();
      }
    }
    catch {
      team = await fetchTeam(user.id);
    }

    try {
      players = await JSON.parse(localStorage.getItem('cachedPlayers'));

      if (players === null || players === undefined) {
        throw new Error();
      }
    }
    catch {
      players = await fetchPlayers(team, user);
    }

    try {
      pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));

      if (pitchers === null || pitchers === undefined) {
        throw new Error();
      }
    }

    catch {
      pitchers = await fetchPitchers(team, user);
    }

    try {
      finalised = await JSON.parse(localStorage.getItem('cachedAssignedPlayers'));

      if (finalised === null || finalised === undefined) {
        throw new Error();
      }
    }

    catch {
      finalised = await assignPlayers(players);
    }

    console.log(finalised);

    try {
      assignedPitchers = await JSON.parse(localStorage.getItem('cachedAssignedPitchers'));

      if (assignedPitchers === null || assignedPitchers === undefined) {
        throw new Error();
      }
    }

    catch {
      assignedPitchers = await assignPitchers(pitchers);
    }

    try {
      combined = await JSON.parse(localStorage.getItem('cachedCombined'));

      if (combined === null || combined === undefined) {
        throw new Error();
      } 
    }
    catch {
      combined = await combine(finalised, assignedPitchers)
    }

    setPlayers(combined);
    setLoading(false);
  }

  const combine = async (players, pitchers) => {
    let roster = [];

    players.map((p) => {
      roster.push(p)
    })

    pitchers.map((p) => {
      roster.push(p)
    })

    localStorage.setItem('cachedCombined', JSON.stringify(roster));
    return roster;
  } 

  const fetchPitchers = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/pitchers/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append('teamId', team.id);
    url.searchParams.append('userId', user.id);

    try {
      const res = await fetch(url);

      if(!res.ok) {
        console.error('Bad request');
      }

      const data = await res.json();
      localStorage.setItem('cachedPitchers', JSON.stringify(data));
      return data;
    }
    catch(_e) {
      console.error(_e);
    }
  }

  const assignPitchers = async (pitchers) => {
    let res = []
    pitchers.map((pitcher, i) => {
      if (pitcher.assignment === 'n/a') {
        pitcher.assignment = '40 Man Roster';
      }

      const p = {
        id: pitcher.id,
        name: pitcher.firstName + ' ' + pitcher.lastName,
        position: 'Pitcher',
        assignment: pitcher.assignment
      }
      res.push(p);
    })
    localStorage.setItem('cachedAssignedPitchers', JSON.stringify(res));
    return res;
  }

  const assignPlayers = async (players) => {
        let roster = [];

        players.map((player, i) => {
          if (player.assignment === 'n/a') {
            player.assignment = '40 Man Roster';
          }
          const p = {
            id: player.id,
            name: player.firstName +' '+player.lastName, 
            position: player.defensivePlayer.positions.join(', '),
            assignment: player.assignment
          };
          roster.push(p);
        });
        localStorage.setItem('cachedAssignedPlayers', JSON.stringify(roster));
        return roster;
  } 

  const fetchPlayers = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/rosters/get';

    const url = new URL(endpoint);
    url.searchParams.append('teamId', team.id);
    url.searchParams.append('userId', user.id);

    try{
      const res = await fetch(url);
      if (!res.ok) {
        alert('Res not ok');
      }
      const players = await res.json();
      localStorage.setItem('cachedPlayers', JSON.stringify(players));
      return players;
    }
    catch(_e) {
      console.error(_e);
    }
  }

  const fetchTeam = async (userId) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-by-user';
    const url = new URL(endpoint);
    url.searchParams.append('userId', userId);

    try {
      const res = await fetch(url);
      if(!res.ok) {
        alert('Response not ok');
      }
      const loadedTeam = await res.json();
      localStorage.setItem('cachedTeam', JSON.stringify(loadedTeam));
      return loadedTeam;
    }
    catch(_e) {
      console.error(_e);
    }
  };
    
  const handleDragStart = (e, playerId, playerPosition) => {
    e.dataTransfer.setData("playerId", playerId);
    e.dataTransfer.setData('playerPosition', playerPosition)
  };

  const handleDrop = async (e, newAssignment) => {
    console.log('e (relevant info): ', e.dataTransfer.getData('playerId'))
    console.log('newAssignment: ', newAssignment);

    const id = e.dataTransfer.getData('playerId');

    const combined = await JSON.parse(localStorage.getItem('cachedCombined'));

    console.log(combined);

    combined.forEach((p) => {
      if (p.id == id) {
        console.log('Player hit: ', p)
        p.assignment = newAssignment;
      }
    })

    localStorage.setItem('cachedCombined', JSON.stringify(combined));
    setPlayers(combined);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const getPlayerList = (assignment) =>
    players.filter(player => player.assignment === assignment).map((player) => (
      <div key={player.id}
           className="playerCard"
           draggable
           onDragStart={(e) => handleDragStart(e, player.id, player.position)}>
        {player.name} - {player.position}
      </div>
    ));

    const saveRoster = async () => {
      setSaving(true);
      console.log('Save button clicked');
      const newRoster = await JSON.parse(localStorage.getItem('cachedCombined'));
      console.log('newRoster: ', newRoster);
      const user = await JSON.parse(localStorage.getItem('sessionData'));
      let team = await JSON.parse(localStorage.getItem('cachedTeam'));

      if (team === undefined) {
        team = await fetchTeam(useEffect);
      }

      const endpoint = 'http://localhost:8080/diamond-data/api/rosters/bulk-update';
      const url = new URL(endpoint);
      url.searchParams.append('teamId', team.id);
      url.searchParams.append('userId', user.id);

      const res = await fetch(url, {
        body: localStorage.getItem('cachedCombined'),
        method: 'PUT',
        headers: {
          'Content-Type':'application/json'
        }
      })

      if(!res.ok) {
        console.error('res not okay: ', res);
      }

      const data = await res.json();
      console.log('returned result: ', data);
      setSaving(false);

    }

    return (
      <div>
        {saving ? <SavingScreen/> : <>
        {loading ? <LoadingScreen/> : <>
        <Navbar />
        <div className="roster">
          <div className='titleAndButton'>
            <h1 className="title">Roster</h1>
            <button className='saveButton' onClick={saveRoster}> Save </button>
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
        </div> </>}</>}
        <Footer />
      </div>
    );
  }
  
  export default Roster;