import React, { useState, useEffect } from 'react'; 
import '../styles/TeamStats.scss';
import Navbar from '../components/Navbar';

function TeamStats() {
  const [teams, setTeams] = useState([]);
  const [players, setPlayers] = useState([]);
  const [selectedTeamStats, setSelectedTeamStats] = useState(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    fetchTeams();
    fetchPlayers();
  }, []);

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
    try {
      const response = await fetch('http://localhost:8080/diamond-data/api/players/get-all');
      if (!response.ok) {
        throw new Error('Failed to fetch players');
      }
      const data = await response.json();
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
        acc.onBasePlusSlugging += player.offensivePlayer.onBasePlusSlugging;
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
      homeRuns: 0, intentionalWalks: 0, leftOnBase: 0, onBasePercentage: 0, onBasePlusSlugging: 0,
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
  
    aggregatedStats.onBasePlusSlugging = aggregatedStats.onBasePercentage + aggregatedStats.sluggingPercentage;
  
    return aggregatedStats;
  };
  const handleTeamClick = (team) => {
    const stats = aggregateTeamStats(team.id);
    setSelectedTeamStats({ ...stats, name: team.name });
    setShowModal(true);
  };

  return (
    <div className="teamStats">
      <Navbar />
      <h1>Team Stats</h1>
      <div className="teamList">
        {teams.length > 0 ? (
          teams.map((team) => (
            <div key={team.id} className="teamItem" onClick={() => handleTeamClick(team)}>
              <div className="teamInfo">
                <div className="teamName">{team.name}</div>
              </div>
            </div>
          ))
        ) : (
          <div>No teams available</div>
        )}
      </div>
      {showModal && (
        <div className="modal">
          <div className="modalContent">
            <span className="close" onClick={() => setShowModal(false)}>&times;</span>
            <h2>{selectedTeamStats.name} Stats</h2>
      <div>At Bats: {selectedTeamStats.atBats}</div>
      <div>Batting Average: {selectedTeamStats.battingAverage.toFixed(3)}</div>
      <div>Caught Stealing: {selectedTeamStats.caughtStealing}</div>
      <div>Doubles: {selectedTeamStats.doubles}</div>
      <div>Extra Base Hits: {selectedTeamStats.extraBaseHits}</div>
      <div>Games Played: {selectedTeamStats.gamesPlayed}</div>
      <div>Grand Slams: {selectedTeamStats.grandSlams}</div>
      <div>Ground Into Double Play: {selectedTeamStats.groundIntoDoublePlay}</div>
      <div>Ground Out Air Out Ratio: {selectedTeamStats.groundOutAirOut.toFixed(2)}</div> {/* Placeholder */}
      <div>Hit By Pitch: {selectedTeamStats.hitByPitch}</div>
      <div>Hits: {selectedTeamStats.hits}</div>
      <div>Home Runs: {selectedTeamStats.homeRuns}</div>
      <div>Intentional Walks: {selectedTeamStats.intentionalWalks}</div>
      <div>Left On Base: {selectedTeamStats.leftOnBase}</div>
      <div>On Base Percentage: {selectedTeamStats.onBasePercentage.toFixed(3)}</div>
      <div>On Base Plus Slugging: {selectedTeamStats.onBasePlusSlugging.toFixed(3)}</div>
      <div>Plate Appearances: {selectedTeamStats.plateAppearances}</div>
      <div>Reached On Error: {selectedTeamStats.reachedOnError}</div>
      <div>Runs: {selectedTeamStats.runs}</div>
      <div>Runs Batted In: {selectedTeamStats.runsBattedIn}</div>
      <div>Sacrifice Bunt: {selectedTeamStats.sacrificeBunt}</div>
      <div>Sacrifice Fly: {selectedTeamStats.sacrificeFly}</div>
      <div>Singles: {selectedTeamStats.singles}</div>
    </div>
        </div>
      )}
    </div>
  );
}

export default TeamStats;
