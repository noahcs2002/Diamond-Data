import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer';
import LoadingScreen from '../components/LoadingScreen';
import SavingScreen from '../components/SavingScreen';
import { ToastContainer, toast } from 'react-toastify';

function Roster() {
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [changes, setChanges] = useState(0);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    prop(user)
  }, []);

  const prop = async (user) => {
    toast.dismiss();
    toast.loading('Getting all your players', {
      position:'bottom-right',
      hideProgressBar:true,
    })

    let team = undefined 
    let players = undefined
    let pitchers = undefined
    let finalised = undefined
    let assignedPitchers = undefined
    let combined = undefined
    let cachedVerdict = undefined;

    try {
      cachedVerdict = JSON.parse(localStorage.getItem('cachedPlayersMAIN'));

      if (cachedVerdict === undefined || cachedVerdict === null) {
        throw new Error()
      }

      setPlayers(cachedVerdict);
    }
    catch {

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

      finalised = await assignPlayers(players);
      assignedPitchers = await assignPitchers(pitchers);
      combined = await combine(finalised, assignedPitchers)
      setPlayers(combined);

    }

    setLoading(false);
    toast.dismiss();
    toast.success('Data loaded successfully!', {
      position:'bottom-right',
      autoClose: 1500,
      hideProgressBar:true,
      closeOnClick:true
    })
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
      toast.error('Error fetching pitchers, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
      toast.error('Error fetching players, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
      toast.error('Error fetching team, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  };
    
  const handleDragStart = (e, playerId, playerPosition) => {
    e.dataTransfer.setData("playerId", playerId);
    e.dataTransfer.setData('playerPosition', playerPosition)
  };

  const handleDrop = async (e, newAssignment) => {
    setChanges(changes + 1);
    if (changes === 1 || (changes + 1) % 10 === 0) {
      toast.dismiss()
      toast.info('Make sure to save your changes!', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true 
      })
    }
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
      setChanges(0);
      toast.loading('Saving roster transactions', {
        position:'bottom-right',
        hideProgressBar:true,
      })
      setSaving(true);
      const newRoster = await JSON.parse(localStorage.getItem('cachedCombined'));
      const user = await JSON.parse(localStorage.getItem('sessionData'));
      let team = await JSON.parse(localStorage.getItem('cachedTeam'));

      if (team === undefined) {
        team = await fetchTeam(user);
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
      localStorage.setItem('cachedPlayersMAIN', JSON.stringify(data));
      console.log(data);
      let cacheableRoster = [];

      newRoster.map(e => {
        const [first, last] = e.name.split(' ');
        if (e.assignment === 'Line-Up') {
          cacheableRoster.push({
            firstName: first,
            lastName: last
          }) 
        }
      })
      localStorage.setItem('cachedLineupPlayers', JSON.stringify(cacheableRoster));
      setSaving(false);
      toast.dismiss();
      toast.success('Roster saved successfully!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true 
      })
    }

    return (
      <div>
        <ToastContainer/>
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