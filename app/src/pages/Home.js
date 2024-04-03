import React, { useEffect } from 'react'
import { Link, useLocation, useHistory, useNavigate } from "react-router-dom";
import '../styles/Home.scss';
import Navbar from '../components/Navbar';
import { useState } from 'react';

function Home() {

// Use location to get where we are
const location = useLocation();
let data = {};

useEffect(() => {
  // if location has a state, we have come here from login screen
  // store the session information in the cache (localStorage)
  if (location.state) {
    console.log(location.state);
    data = location.state.data;
    localStorage.setItem('data', JSON.stringify(location.state));
    console.log(JSON.parse(localStorage.getItem('data')));
  }
  // If we don't have a state, we are here from a different screen, load it from the cache
  else {
    data = JSON.parse(localStorage.getItem('data'));
  }

  // Do whatever with it
  console.log(data);
}, [])

  const [selectedTeam, setSelectedTeam] = useState('Saint Louis Cardinals');

  const teams = ['Saint Louis Cardinals', 'Dodgers Stink', 'Dodgers Overrated', 'Dodgers Suck'];

  const handleTeamChange = (team) => {
    setSelectedTeam(team);
  };

  const getUpcomingDays = () => {
    const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const today = new Date();
    const upcomingDays = [];

    for (let i = 0; i < 7; i++) {
      const nextDay = new Date(today);
      nextDay.setDate(today.getDate() + i);
      upcomingDays.push(days[nextDay.getDay()]);
    }

    return upcomingDays;
  };

  const upcomingDays = getUpcomingDays();

  return (
    <div className="home" style={{ backgroundImage: 'url(${})'}}>
        <Navbar/>
      <div className="headerContainer">
        <h1>Welcome {selectedTeam}</h1>
        <div className="teamDropdown">
          <select value={selectedTeam} onChange={(e) => handleTeamChange(e.target.value)}>
            {teams.map((team, index) => (
              <option key={index} value={team}>{team}</option>
            ))}
          </select>
        </div>
        <div className="buttonsContainer">
          <Link to="/PlayerManagement" className="button">Player Management</Link>
          <Link to="/TeamStats" className="button">Team Stats</Link>
          <Link to="/UserManagement" className="button">User Management</Link>
          <Link to="" className="button">Insights</Link>
          <Link to="" className="button">Bulk Entry</Link>
        </div>
        <div className="startingLineup">
          <h2>Starting Lineup</h2>
          <div className="lineup">
            <div className="player">Player 1</div>
            <div className="player">Player 2</div>
            <div className="player">Player 3</div>
            <div className="player">Player 4</div>
            <div className="player">Player 5</div>
            <div className="player">Player 6</div>
            <div className="player">Player 7</div>
            <div className="player">Player 8</div>
            <div className="player">Player 9</div>
            <div className="player"> Starting Pitcher</div>
          </div>
          <div className="calendar">
            <h2>Upcoming Games</h2>
            <div className="daysContainer">
              {upcomingDays.map((day, index) => (
                <div key={index} className="day">{day}</div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Home;