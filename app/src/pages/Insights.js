import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/Insights.scss';
import ConfirmModal from '../components/ConfirmModal';

function Insights() {
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [players, setPlayers] = useState([]);
  const [selectedPlayers, setSelectedPlayers] = useState([null, null]); 
  const [reportNotes, setReportNotes] = useState([]); 
  const [newReportNote, setNewReportNote] = useState(''); 
  const [editedNoteIndex, setEditedNoteIndex] = useState(null); 
  const [userId, setUserId] = useState(null);
  const [editedNoteText, setEditedNoteText] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [noteToDelete, setNoteToDelete] = useState(null);

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));

  const playerOptions = players.map(player => (
    <option key={player.id} value={player.id}>{player.name}</option>
  ));

  
  const fetchTeam = async (user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-by-user';
    const url = new URL(endpoint);
    url.searchParams.append("userId", user.id);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error: ');
      }
      const data = await response.json();
      localStorage.setItem('team', data);
      return data
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

 
  useEffect(() => {
    const fetchUserData = async () => {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      if (user) {
        setUserId(user.id);
      }
    };
    fetchUserData();
  }, []);

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const user = JSON.parse(localStorage.getItem('sessionData'));
        const team = await fetchTeam(user);
        if (!selectedTeam || !userId) return;
        const endpoint = `http://localhost:8080/diamond-data/api/notes/get-by-team`;
        const url = new URL(endpoint);
        url.searchParams.append('teamId', team.id);
        url.searchParams.append('userId', user.id);
        const response = await axios.get(url.toString());
        setReportNotes(response.data);
      } catch (error) {
        console.error('Error fetching notes:', error);
      }
    };
    fetchNotes();
  }, [selectedTeam, userId]);



  const handlePlayerSelectionChange = (index, playerId) => {
    const updatedSelection = [...selectedPlayers];
    updatedSelection[index] = playerId;
    setSelectedPlayers(updatedSelection);
  };

  const addNote = async () => {
    try {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      const team = await fetchTeam(user);
      const endpoint = `http://localhost:8080/diamond-data/api/notes/create`;
      const requestBody = {
        note: newReportNote
      };
      const params = {
        userId: user.id,
        teamId: team.id,
      };
      const response = await axios.post(endpoint, requestBody, { params });
      setReportNotes([...reportNotes, { id: response.data.id, text: newReportNote }]);
      setNewReportNote('');
    } catch (error) {
      console.error('Error adding note:', error);
    }
  };

  const editNote = async (noteId, newText) => {
    try {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      const endpoint = `http://localhost:8080/diamond-data/api/notes/update`;
      const url = new URL(endpoint);
      url.searchParams.append('id', noteId);
      url.searchParams.append('userId', user.id);
      url.searchParams.append('newNoteText', newText);
      const response = await axios.put(url.toString());
      const updatedNotes = reportNotes.map(note =>
        note.id === noteId ? { ...note, text: newText } : note
      );
      setReportNotes(updatedNotes);
      setEditedNoteIndex(null);
    } catch (error) {
      console.error('Error updating note:', error);
    }
  };
  
  const deleteNote = async () => {
    try {
      // Ensure noteToDelete is not null
      if (!noteToDelete) return;

      const user = JSON.parse(localStorage.getItem('sessionData'));
      const endpoint = `http://localhost:8080/diamond-data/api/notes/delete`;
      const url = new URL(endpoint);
      url.searchParams.append('userId', user.id);
      url.searchParams.append('noteId', noteToDelete.id);
      await axios.delete(url.toString());
      const updatedNotes = reportNotes.filter(note => note.id !== noteToDelete.id);
      setReportNotes(updatedNotes);

      // Close modal and reset noteToDelete
      closeModal();
    } catch (error) {
      console.error('Error deleting note:', error);
      closeModal();
    }
  };

  const openModal = (note) => {
    setIsModalOpen(true);
    setNoteToDelete(note);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setNoteToDelete(null);
  };

  const handleNoteInputChange = (event, isEditing) => {
    if (isEditing) {
      setEditedNoteText(event.target.value);
    } else {
      setNewReportNote(event.target.value);
    }
  };

  return (  
    <div>
      <Navbar />
      <div className="insights">
        <h1 className="title">Team Insights</h1>
        <div className="content">
            <div className="column">
              <h2>AI-generated Roster Lineup</h2>
              <div className="scrollable-cards">
                <div className="card">Player 1 Info</div>
                <div className="card">Player 2 Info</div>
                <div className="card">Player 3 Info</div>
                <div className="card">Player 4 Info</div>
                <div className="card">Player 5 Info</div>
                <div className="card">Player 6 Info</div>
                <div className="card">Player 7 Info</div>
                <div className="card">Player 8 Info</div>
                <div className="card">Player 9 Info</div>
              </div>
            </div>
            <div className="column">
              <h2>Team Records</h2>
              <p className='teamRecordsData'>Team records data goes here...</p>
            </div>
            <div className="column">
              <h2>Coach's Notes</h2>
              <div>
                <textarea className='addNote'
                  value={newReportNote}
                  onChange={handleNoteInputChange}
                  placeholder="Type your note here..."
                />
                <button className='addNoteButton' onClick={addNote}>Add Note</button>
              </div>
              {}
              {console.log(reportNotes)}
              {reportNotes.map((note, index) => (
              <div className="note-container" key={note.id}>
                {editedNoteIndex === index ? (
                  <div>
                    <textarea className='notes'
                      value={editedNoteText} 
                      onChange={(e) => handleNoteInputChange(e, true)} 
                      placeholder="Edit your note here..."
                    />
                    <div className="note-actions">
                      <button className="save-note" onClick={() => editNote(note.id, editedNoteText)}>Save</button>
                      <button className="cancel-edit" onClick={() => setEditedNoteIndex(null)}>Cancel</button>
                    </div>
                  </div>
                ) : (
                  <div>
                    <p className="note-text">{note.text}</p>
                    <div className="note-actions">
                      <button className="edit-note" onClick={() => setEditedNoteIndex(index)}>Edit</button>
                      <button className="delete-note" onClick={() => openModal(note)}>Delete</button>
                    </div>
                  </div>
                )}
              </div>
            ))}
            </div>
          {isModalOpen && (
          <ConfirmModal
            isOpen={isModalOpen}
            onClose={closeModal}
            onConfirm={deleteNote}
            message="Are you sure you want to delete this note?"
          />
        )}
        </div>
      </div>
      <Footer />
    </div>
  );
}
export default Insights;
