import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/Insights.scss';
import ConfirmModal from '../components/ConfirmModal';
import LoadingScreen from '../components/LoadingScreen';
import { useLocation } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function Insights() {
  const [reportNotes, setReportNotes] = useState([]); 
  const [newReportNote, setNewReportNote] = useState(''); 
  const [editedNoteIndex, setEditedNoteIndex] = useState(null); 
  const [editedNoteText, setEditedNoteText] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [noteToDelete, setNoteToDelete] = useState(null);
  const [lineupPlayers, setLineupPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
  const nav = useNavigate();

  const location = useLocation();

  const dummyTeamRecords = [
    { name: 'New York Yankees', wins: 98, losses: 64 },
    { name: 'Los Angeles Dodgers', wins: 105, losses: 57 },
    { name: 'Houston Astros', wins: 95, losses: 67 },
    { name: 'Atlanta Braves', wins: 88, losses: 73 },
    { name: 'Chicago White Sox', wins: 93, losses: 69 },
    { name: 'San Francisco Giants', wins: 107, losses: 55 },
    { name: 'Toronto Blue Jays', wins: 91, losses: 71 },
    { name: 'St Louis Cardinals', wins: 106, losses: 56 },
   
  ];

  useEffect(() => {
    toast.dismiss();
    toast.loading('Gathering all your information', {
      position:'bottom-right',
      hideProgressBar:true,
    })
    setLoading(true);
    
    prop();
  }, []);

  const prop = async () => {
    let lineup = [];
    let notes = [];
    let team = {};
    const user = await JSON.parse(localStorage.getItem('sessionData'));

    if(user === undefined || user === null) {
      nav('/')
      return;
    }

    try {
      lineup = await JSON.parse(localStorage.getItem('cachedLineupPlayers'));

      if (lineup === null || lineup === undefined) {
        throw new Error();
      }
    }
    catch {
      lineup = await fetchLineupPlayers();
    }

    team = await fetchTeam(user);

    try {
      notes = await JSON.parse(localStorage.getItem('cachedNotes'));

      if (notes === undefined || notes === null) {
        throw new Error();
      }
    }
    catch { 
      notes = await fetchNotes(user, team)
    }

    toast.dismiss();
    toast.success('Data gathered successfully!', {
      position:'bottom-right',
      autoClose: 2500,
      hideProgressBar:true,
      closeOnClick:true
    })
    setReportNotes(notes);
    setLineupPlayers(lineup);
    setLoading(false);
    console.log('After loading home page, cachedPlayers=', JSON.parse(localStorage.getItem('cachedPlayers')))
  }

  const renderLineupPlayers = () => {
    return lineupPlayers.map((player, index) => (
      <div className="card" key={index}>
        {index + 1}. {player.firstName} {player.lastName}
      </div>
    ));
  };

  const fetchNotes = async (user, team) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/notes/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);
      const notes = await res.json();
      localStorage.setItem('cachedNotes', JSON.stringify(notes));
      console.log(notes);
      return notes;
    }
    catch {
      toast.error('Error fetching notes, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  }

  const fetchLineupPlayers = async () => {
    try {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      const team = await fetchTeam(user);
      if (!team || !user) return;
  
      const lineupPlayers = await fetchPlayers(team, user);
      
      const filteredLineupPlayers = lineupPlayers.filter(player => player.assignment === 'Line-Up');
      localStorage.setItem('cachedLineupPlayers', JSON.stringify(filteredLineupPlayers));
      setLineupPlayers(filteredLineupPlayers);
      return filteredLineupPlayers;
    } 
    catch (error) {
      toast.error('Error fetching players, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  };

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
      console.error(_e);
    }
  }
  
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
      toast.error('Error fetching team, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
      console.error('Error fetching teams:', error);
    }
  };

  const addNote = async () => {
    toast.loading('Adding note', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true 
    })
    try {
      let team = {};
      const user = JSON.parse(localStorage.getItem('sessionData'));

      try {
        team = await JSON.parse(localStorage.getItem('cachedTeam'));

        if(team === undefined || team === null) {
          throw new Error();
        }
      }
      catch {
        team = await fetchTeam(user);
      }

      const endpoint = `http://localhost:8080/diamond-data/api/notes/create`;
      const url = new URL(endpoint);
      url.searchParams.append('userId', user.id);
      url.searchParams.append('teamId', team.id);

      const note = {
        body: newReportNote,
        team: team
      }

      const response = await fetch(url, {
        body: JSON.stringify(note),
        headers: {
          'Content-Type' : 'application/json'
        },
        method: 'POST'
      })
      
      const data = await response.json();
      const notes = await JSON.parse(localStorage.getItem('cachedNotes'));
      notes.filter(n => n.id !== data.id);
      notes.push(data);
      localStorage.setItem('cachedNotes', JSON.stringify(notes));
      setReportNotes(notes);
      setNewReportNote('');
      toast.dismiss();
      toast.success('Note added successfully!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true 
      })
    } 
    catch (error) {
      toast.error('Error adding note, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
      console.error('Error adding note:', error);
    }

  };

  const editNote = async (noteId, newText) => {
    toast.loading('Saving note', {
      position:'bottom-right',
      autoClose: 2500,
      hideProgressBar:true,
      closeOnClick:true 
    })
    let notes = await JSON.parse(localStorage.getItem('cachedNotes'));
    try {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      const endpoint = `http://localhost:8080/diamond-data/api/notes/update`;
      const url = new URL(endpoint);
      url.searchParams.append('id', noteId);
      url.searchParams.append('userId', user.id);
      url.searchParams.append('newNoteText', newText);
      const response = await axios.put(url.toString());

      notes = notes.filter(note => note.id !== noteId);
      notes.push(response.data);
      setReportNotes(notes);
      localStorage.setItem('cachedNotes', JSON.stringify(notes));
      setEditedNoteIndex(null);
      toast.dismiss();
      toast.success('Note saved', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true 
      })
    } 
    catch (error) {
      toast.error('Error editing note, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }

  };
  
  const deleteNote = async () => {
    try {
      toast.loading('Deleting note', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true  
      })

      if (!noteToDelete) return;

      const user = JSON.parse(localStorage.getItem('sessionData'));
      const endpoint = `http://localhost:8080/diamond-data/api/notes/delete`;
      const url = new URL(endpoint);
      url.searchParams.append('userId', user.id);
      url.searchParams.append('noteId', noteToDelete.id);
      await axios.delete(url.toString());
      const updatedNotes = reportNotes.filter(note => note.id !== noteToDelete.id);
      localStorage.setItem('cachedNotes', JSON.stringify(updatedNotes));
      setReportNotes(updatedNotes);

      closeModal();
      toast.dismiss();
      toast.success('Note deleted successfully!', {
        position:'bottom-right',
        autoClose: 2500,
        hideProgressBar:true,
        closeOnClick:true 
      })
    } 
    catch (error) {
      toast.error('Error deleting notes, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
      <ToastContainer/>
    {loading ? <LoadingScreen/> : <>
      <Navbar />
      <div className="insights">
        <h1 className="title">Team Insights</h1>
        <div className="content">
          <div className="column">
            <h2>Team Lineup</h2>
            <div className="scrollable-cards">
              {renderLineupPlayers()}
            </div>
          </div>
            <div className="column">
            <h2>Team Records</h2>
              <ul className='teamRecordsData'>
                {dummyTeamRecords.map((team, index) => (
                <li key={index}>
                  <strong>{team.name}:</strong> {team.wins}-{team.losses}
                </li>
              ))}
              </ul>
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

              {reportNotes.map((note, index) => (
              <div className="note-container" key={note.id+note.body}>
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
                    <p className="note-text">{note.body}</p>
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
      <Footer /></>}
    </div>
  );
}

export default Insights;