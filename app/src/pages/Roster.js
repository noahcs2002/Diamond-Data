import React, { useEffect, useState } from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'
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
    const teams = await fetchTeams(user.id);
    const players = await fetchPlayers(selectedTeam || teams[0], user);
    const finalised = await assignPlayers(players);
    setPlayers(finalised);
    setLoading(false);
  }

  const assignPlayers = async (players) => {
        let roster = [];

        players.map((player, i) => {
          if (player.assignment === 'na') {
            player.assignment = '40 Man Roster';
          }
          const p = {
            // id: player.id, 
            id: player.id,
            apiId: player.id,
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

  const fetchTeams = async (userId) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
    const url = new URL(endpoint);
    url.searchParams.append('userId', userId);

    try {
      const res = await fetch(url);
      if(!res.ok) {
        alert('Response not ok');
      }
      const loadedTeams = await res.json();
      setTeams(loadedTeams);
      setSelectedTeam(loadedTeams[0]);
      return loadedTeams;
    }
    catch(_e) {
      console.error(_e);
    }
  };

  const handleTeamSelect = (e) => {
    const teamId = e.target.value;
    console.log(teams);
    teams.map((t) => {
      if (t.id == teamId) {
        setSelectedTeam(t);
      }
    localStorage.setItem('selectedTeam', t);
    })
  };
    
  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const handleDragStart = (e, playerId) => {
    e.dataTransfer.setData("playerId", playerId);
  };

  const handleDrop = async (e, newAssignment) => {
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem('sessionData'));

    const playerId = e.dataTransfer.getData("playerId");

    const endpoint = 'http://localhost:8080/diamond-data/api/rosters/update-assignment';
    const url = new URL(endpoint);
    url.searchParams.append('playerId', playerId)
    url.searchParams.append('userId', user.id)
    url.searchParams.append('newAssignment', newAssignment);

    try{
      // alert('Updating Rosters...');
      const res = await fetch(url);
      if (!res.ok) {
        alert('Res not ok');
      }
      const data = await res.json();
      console.log(data);
      window.location.reload();
    }
    catch(_e) {
      console.error(_e);
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
           onDragStart={(e) => handleDragStart(e, player.id)}>
        {player.name} - {player.position}
      </div>
    ));


    return (
      <div>
        {loading ? <LoadingScreen/> : <>
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
        </div> </>}
      </div>
    );
  }
  
  export default Roster;