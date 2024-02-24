import React, { useState, useEffect } from 'react'; 
import '../styles/TeamStats.scss';
import Navbar from '../components/Navbar';

function TeamStats() {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    try {
      const response = await fetch('http://localhost:8080/diamond-data/api/teams/get-all');
      if (!response.ok) {
        throw new Error('Failed to fetch teams');
      }
      const data = await response.json();
      setTeams(data);
    } catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  return (
    <div className="teamStats">
      <Navbar />
      <h1>Team Stats</h1>
      <div className="teamList">
        {teams.length > 0 ? (
          teams.map((team) => (
            <div key={team.id} className="teamItem">
              <div className="teamInfo">
                <div className="teamName">{team.name}</div>
              </div>
            </div>
          ))
        ) : (
          <div>No teams available</div>
        )}
      </div>
    </div>
  );
}

export default TeamStats;
