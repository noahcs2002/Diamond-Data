import React, { useState, useEffect } from 'react'; 
import '../styles/TeamStats.scss';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import LoadingScreen from '../components/LoadingScreen';

function TeamStats() {
  const [team, setTeam] = useState([]);
  const [players, setPlayers] = useState([]);
  const [selectedTeamStats, setSelectedTeamStats] = useState(null);
  
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    prop();
  }, []);

  const prop = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    let team = {};
    let players = {};

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

      if (players === undefined || players === null) {
        throw new Error();
      }
    }
    catch {
      players = await fetchPlayers();
    }

    setPlayers(players);
    setLoading(false);
  }

  const fetchTeam = async (user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-by-user';
    const url = new URL(endpoint);
    url.searchParams.append("userId", user.id); 
  
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error');
      }
      const data = await response.json();
      localStorage.setItem('cachedTeam', JSON.stringify(data));
      setTeam(data);
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  const fetchPlayers = async () => {
    try {
      const response = await fetch('http://localhost:8080/diamond-data/api/players/get-all');
      if (!response.ok) {
        throw new Error('Failed to fetch players');
      }
      const data = await response.json();
      localStorage.setItem('cachedPlayers', JSON.stringify(data));
      setPlayers(data);
    } catch (error) {
      console.error('Error fetching players:', error);
    }
  };

  const aggregateTeamStats = (teamId) => {
    const teamPlayers = players.filter(player => player.offensivePlayer.team.id === teamId);
    const aggregatedStats = teamPlayers.reduce((acc, player) => {
      if(player.offensivePlayer) {
        acc.atBats += player.offensivePlayer.atBats;
        acc.battingAverage += player.offensivePlayer.battingAverage;
        acc.caughtStealing += player.offensivePlayer.caughtStealing;
        acc.doubles += player.offensivePlayer.doubles;
        acc.extraBaseHits += player.offensivePlayer.extraBaseHits;
        acc.gamesPlayed += player.offensivePlayer.gamesPlayed;
        acc.grandSlams += player.offensivePlayer.grandSlams;
        acc.groundIntoDoublePlay += player.offensivePlayer.groundIntoDoublePlay;
        acc.groundOutAirOut += player.offensivePlayer.groundOutAirOut; 
        acc.hitByPitch += player.offensivePlayer.hitByPitch;
        acc.hits += player.offensivePlayer.hits;
        acc.homeRuns += player.offensivePlayer.homeRuns;
        acc.intentionalWalks += player.offensivePlayer.intentionalWalks;
        acc.leftOnBase += player.offensivePlayer.leftOnBase;
        acc.onBasePercentage += player.offensivePlayer.onBasePercentage; 
        acc.plateAppearances += player.offensivePlayer.plateAppearances;
        acc.reachedOnError += player.offensivePlayer.reachedOnError;
        acc.runs += player.offensivePlayer.runs;
        acc.runsBattedIn += player.offensivePlayer.runsBattedIn;
        acc.sacrificeBunt += player.offensivePlayer.sacrificeBunt;
        acc.sacrificeFly += player.offensivePlayer.sacrificeFly;
        acc.singles += player.offensivePlayer.singles;
      }
      return acc;
    }, {
      atBats: 0, battingAverage: 0, caughtStealing: 0, doubles: 0, extraBaseHits: 0, gamesPlayed: 0,
      grandSlams: 0, groundIntoDoublePlay: 0, groundOutAirOut: 0, hitByPitch: 0, hits: 0,
      homeRuns: 0, intentionalWalks: 0, leftOnBase: 0, onBasePercentage: 0,
      plateAppearances: 0, reachedOnError: 0, runs: 0, runsBattedIn: 0, sacrificeBunt: 0,
      sacrificeFly: 0, singles: 0
    });

    if (aggregatedStats.atBats > 0) {
      aggregatedStats.battingAverage = aggregatedStats.hits / aggregatedStats.atBats;
    } else {
      aggregatedStats.battingAverage = 0;
    }
  
    
    if (aggregatedStats.atBats + aggregatedStats.walks > 0) {
      aggregatedStats.onBasePercentage = (aggregatedStats.hits + aggregatedStats.walks) / (aggregatedStats.atBats + aggregatedStats.walks);
    } else {
      aggregatedStats.onBasePercentage = 0;
    }
  
    if (aggregatedStats.atBats > 0) {
      aggregatedStats.sluggingPercentage = aggregatedStats.totalBases / aggregatedStats.atBats;
    } else {
      aggregatedStats.sluggingPercentage = 0;
    }
  
    return aggregatedStats;
  };
  const handleTeamClick = (team) => {
    const stats = aggregateTeamStats(team.id);
    setSelectedTeamStats({ ...stats, name: team.name });
    setShowModal(true);
  };

  return (
    <div className="teamStats">
      {loading ? <LoadingScreen/> : <>
      <Navbar />
      <h1 className='title'>Team Stats</h1>
      <div className="teamList">
        <div key={team.id} className="teamItem" onClick={() => handleTeamClick(team)}>
          <div className="teamInfo">
            <div className="teamName">{team.name} - Click to see more </div>
          </div>
        </div>
      </div>
      {showModal && (
        <div className="modal">
          <div className="modalContent">
            <span className="close" onClick={() => setShowModal(false)}>&times;</span>
            <h2>{selectedTeamStats.name} Stats</h2>
            <div className='stats'><strong>At Bats:</strong> {selectedTeamStats.atBats}</div>
            <div className='stats'><strong>Batting Average:</strong> {selectedTeamStats.battingAverage.toFixed(3)}</div>
            <div className='stats'><strong>Caught Stealing:</strong> {selectedTeamStats.caughtStealing}</div>
            <div className='stats'><strong>Doubles:</strong> {selectedTeamStats.doubles}</div>
            <div className='stats'><strong>Extra Base Hits:</strong> {selectedTeamStats.extraBaseHits}</div>
            <div className='stats'><strong>Games Played:</strong> {selectedTeamStats.gamesPlayed}</div>
            <div className='stats'><strong>Grand Slams:</strong> {selectedTeamStats.grandSlams}</div>
            <div className='stats'><strong>Ground Into Double Play:</strong> {selectedTeamStats.groundIntoDoublePlay}</div>
            <div className='stats'><strong>Hit By Pitch:</strong> {selectedTeamStats.hitByPitch}</div>
            <div className='stats'><strong>Hits:</strong> {selectedTeamStats.hits}</div>
            <div className='stats'><strong>Home Runs:</strong> {selectedTeamStats.homeRuns}</div>
            <div className='stats'><strong>Intentional Walks:</strong> {selectedTeamStats.intentionalWalks}</div>
            <div className='stats'><strong>Left On Base:</strong> {selectedTeamStats.leftOnBase}</div>
            <div className='stats'><strong>On Base Percentage:</strong> {selectedTeamStats.onBasePercentage.toFixed(3)}</div>
            <div className='stats'><strong>Plate Appearances:</strong> {selectedTeamStats.plateAppearances}</div>
            <div className='stats'><strong>Reached On Error:</strong> {selectedTeamStats.reachedOnError}</div>
            <div className='stats'><strong>Runs:</strong> {selectedTeamStats.runs}</div>
            <div className='stats'><strong>Runs Batted In:</strong> {selectedTeamStats.runsBattedIn}</div>
            <div className='stats'><strong>Sacrifice Bunt:</strong> {selectedTeamStats.sacrificeBunt}</div>
            <div className='stats'><strong>Sacrifice Fly:</strong> {selectedTeamStats.sacrificeFly}</div>
            <div className='stats'><strong>Singles:</strong> {selectedTeamStats.singles}</div>
          </div>
        </div>
      )}</>}
      <Footer />
    </div>
  );
}

export default TeamStats;
