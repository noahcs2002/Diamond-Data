import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer';
import LoadingScreen from '../components/LoadingScreen';

function Roster() {
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState();
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
 

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    prop(user)
  }, []);

  const prop = async (user) => {
    const team = await fetchTeam(user.id);
    const players = await fetchPlayers(team, user);
    const pitchers = await fetchPitchers(team, user);
    const finalised = await assignPlayers(players);
    const assignedPitchers = await assignPitchers(pitchers);
    const combined = await combine(finalised, assignedPitchers)
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
      localStorage.setItem('team', JSON.stringify(loadedTeam));
      return loadedTeam;
    }
    catch(_e) {
      console.error(_e);
    }
  };

    
  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const handleDragStart = (e, playerId, playerPosition) => {
    e.dataTransfer.setData("playerId", playerId);
    e.dataTransfer.setData('playerPosition', playerPosition)
  };

  const handleDrop = async (e, newAssignment) => {
    setLoading(true);
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem('sessionData'));

    const playerId = e.dataTransfer.getData("playerId");
    const playerPosition = e.dataTransfer.getData('playerPosition');
    console.log(playerPosition);

    if(playerPosition.trim() === 'Pitcher') {
      const endpoint = 'http://localhost:8080/diamond-data/api/rosters/update-pitcher-assignment';
      const url = new URL(endpoint);
      url.searchParams.append('pitcherId', playerId)
      url.searchParams.append('userId', user.id)
      url.searchParams.append('newAssignment', newAssignment);

      try {
        const res = await fetch(url, {method: 'PUT'})
        if (!res.ok) {
          console.log('Res not okay: ', res);
        }
        window.location.reload();
      }
      catch(_e) {
        console.error(_e);
      }
    }
    else {

      const endpoint = 'http://localhost:8080/diamond-data/api/rosters/update-assignment';
      const url = new URL(endpoint);
      url.searchParams.append('playerId', playerId)
      url.searchParams.append('userId', user.id)
      url.searchParams.append('newAssignment', newAssignment);

      try{
        const res = await fetch(url);
        if (!res.ok) {
          alert('Res not ok');
        }
        window.location.reload();
      }
      catch(_e) {
        console.error(_e);
      }

      if (newAssignment === 'Line-Up') {
        const storedLineup = JSON.parse(localStorage.getItem('lineup')) || [];
        const updatedLineup = [...storedLineup, playerId];
        localStorage.setItem('lineup', JSON.stringify(updatedLineup));
      } else if (newAssignment !== 'Line-Up' && playerPosition.trim() === 'Line-Up') {
        const storedLineup = JSON.parse(localStorage.getItem('lineup')) || [];
        const updatedLineup = storedLineup.filter(id => id !== playerId);
        localStorage.setItem('lineup', JSON.stringify(updatedLineup));
      }

    }

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
           onDragStart={(e) => handleDragStart(e, player.id, player.position)}>
        {player.name} - {player.position}
      </div>
    ));


    return (
      <div>
        {loading ? <LoadingScreen/> : <>
        <Navbar />
        <div className="roster">
          <h1 className="title">Roster</h1>
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
        </div> </>}
        <Footer />
      </div>
    );
  }
  
  export default Roster;