import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/Insights.scss';

function Insights() {
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [players, setPlayers] = useState([]);
  const [selectedPlayers, setSelectedPlayers] = useState([null, null]); 
  const [reportNotes, setReportNotes] = useState([]); 
  const [newReportNote, setNewReportNote] = useState(''); 
  const [editedNoteIndex, setEditedNoteIndex] = useState(null); 

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const playerOptions = players.map(player => (
    <option key={player.id} value={player.id}>{player.name}</option>
  ));

  useEffect(() => {
    const fetchTeams = async () => {
      const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
      const url = new URL(endpoint);
      url.searchParams.append("userId", 302);
      try {
        const response = await axios.get(url.toString());
        setTeams(response.data);
      } catch (error) {
        console.error('Error fetching teams:', error);
      }
    };
    fetchTeams();
  }, []);

  useEffect(() => {
    const fetchPlayers = async () => {
      if (!selectedTeam) return;
      const endpoint = `http://localhost:8080/diamond-data/api/get-by-team?teamId=${selectedTeam}`;
      try {
        const response = await axios.get(endpoint);
        setPlayers(response.data);
      } catch (error) {
        console.error('Error fetching players:', error);
      }
    };
    
    fetchPlayers();
  }, [selectedTeam]);

  const handlePlayerSelectionChange = (index, playerId) => {
    const updatedSelection = [...selectedPlayers];
    updatedSelection[index] = playerId;
    setSelectedPlayers(updatedSelection);
  };

  const handleSaveReportNote = () => {
    if (editedNoteIndex !== null) {
      const updatedNotes = [...reportNotes];
      updatedNotes[editedNoteIndex] = newReportNote;
      setReportNotes(updatedNotes);
      setEditedNoteIndex(null); 
    } else {
      
      setReportNotes([...reportNotes, newReportNote]);
    }
    setNewReportNote(''); 
  };

  const handleEditNote = (index) => {
    setNewReportNote(reportNotes[index]); 
    setEditedNoteIndex(index); 
  };

  const handleDeleteNote = (index) => {
    const updatedNotes = [...reportNotes];
    updatedNotes.splice(index, 1); 
    setReportNotes(updatedNotes);
  };

  return (  
    <div>
      <Navbar />
      <div className="insights">
        <h1 className="title">Team Insights</h1>
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
        <div className="content">
          <div className="left-column">
            <div className="column">
              <h2>AI-generated Roster Lineup</h2>
              <div className="scrollable-cards">
                {/* Dummy card components */}
                <div className="card">Player 1 Info</div>
                <div className="card">Player 2 Info</div>
                <div className="card">Player 3 Info</div>
                <div className="card">Player 4 Info</div>
                <div className="card">Player 5 Info</div>
                <div className="card">Player 6 Info</div>
                <div className="card">Player 7 Info</div>
                <div className="card">Player 8 Info</div>
                <div className="card">Player 9 Info</div>
                {/* Add more dummy cards as needed */}
              </div>
            </div>
            <div className="column">
              <h2>Team Records</h2>
              {/* Dummy team records data */}
              <p>Team records data goes here...</p>
            </div>
            <div className="column">
              <h2>Coach's Notes</h2>
              <div className="report-notes">
                {reportNotes.map((note, index) => (
                  <div key={index} className="report-note">
                    <p>{note}</p>
                    <div className="note-actions">
                      <button onClick={() => handleEditNote(index)}>Edit</button>
                      <button onClick={() => handleDeleteNote(index)}>Delete</button>
                    </div>
                  </div>
                ))}
              </div>
              <textarea
                value={newReportNote}
                onChange={(e) => setNewReportNote(e.target.value)}
                placeholder="Write your report note here..."
              ></textarea>
              <button onClick={handleSaveReportNote}>Save</button>
            </div>
          </div>
          <div className="right-column">
            <div className="comparison">
              <h2>Player Comparison</h2>
              <div className="comparison-cards">
                {selectedPlayers.map((playerId, index) => (
                  <div key={index} className="card">
                    <select
                      value={playerId || ''}
                      onChange={e => handlePlayerSelectionChange(index, e.target.value)}
                    >
                      <option value="">Select a Player</option>
                      {playerOptions}
                    </select>
                  </div>
                ))}
              </div>
            
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
export default Insights;
