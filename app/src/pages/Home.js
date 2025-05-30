// Currently deprecated, support will resume later.
// Noah Sternberg

// import React, { useEffect } from 'react'
// import { Link, useLocation, useHistory, useNavigate } from "react-router-dom";
// import '../styles/Home.scss';
// import Navbar from '../components/Navbar';
// import Footer from '../components/Footer';
// import { useState } from 'react';

// function Home() {
//   const [teams, setTeams] = useState([]);
//   const [selectedTeam, setSelectedTeam] = useState('');
//   const location = useLocation();
//   const [sessionData, setSessionData] = useState([]);
//   const nav = useNavigate();

// // Use location to get where we are

// const fetchTeams = async () => {
//   const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
//   const url = new URL(endpoint);
//   url.searchParams.append("userId", 302); 
//   try {
//     const response = await fetch(url);
//     if (!response.ok) {
//       throw new Error('Network error');
//     }
//     const data = await response.json();
//     setTeams(data);
//     if (data.length > 0) {
//       setSelectedTeam(data[0].name); 
//     }
//   } catch (error) {
//     console.error('Error fetching teams:', error);
//   }
// };


// useEffect(() => {
  
// }, [])

 
// const handleTeamChange = (teamId) => {
//   setSelectedTeam(teamId);
// };


//   const getUpcomingDays = () => {
//     const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
//     const today = new Date();
//     const upcomingDays = [];

//     for (let i = 0; i < 7; i++) {
//       const nextDay = new Date(today);
//       nextDay.setDate(today.getDate() + i);
//       upcomingDays.push(days[nextDay.getDay()]);
//     }

//     return upcomingDays;
//   };

//   const upcomingDays = getUpcomingDays();

//   return (
//     <div className="home">
//         <Navbar/>
//         <h1 className='title'>Welcome {selectedTeam}</h1>
//         <div className="teamDropdown">
//           <select value={selectedTeam} onChange={(e) => handleTeamChange(e.target.value)}>
//             {teams.map(team => (
//               <option key={team.name} value={team.name}>{team.name}</option>
//             ))}
//           </select>
//         </div>
//         <div className="buttonsContainer">
//           <Link to="/PlayerManagement" className="button">Player Management</Link>
//           <Link to="/TeamStats" className="button">Team Stats</Link>
//           <Link to="/UserManagement" className="button">User Management</Link>
//           <Link to="" className="button">Insights</Link>
//           <Link to="" className="button">Bulk Entry</Link>
//         </div>
//         <div className="startingLineup">
//           <h2>Starting Lineup</h2>
//           <div className="lineup">
//             <div className="player">Player 1</div>
//             <div className="player">Player 2</div>
//             <div className="player">Player 3</div>
//             <div className="player">Player 4</div>
//             <div className="player">Player 5</div>
//             <div className="player">Player 6</div>
//             <div className="player">Player 7</div>
//             <div className="player">Player 8</div>
//             <div className="player">Player 9</div>
//             <div className="player"> Starting Pitcher</div>
//           </div>
//           <div className="calendar">
//             <h2>Upcoming Games</h2>
//             <div className="daysContainer">
//               {upcomingDays.map((day, index) => (
//                 <div key={index} className="day">{day}</div>
//               ))}
//             </div>
//           </div>
//         </div>
//         <Footer />
//     </div>
//   );
// }
// export default Home;