import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import '../styles/Settings.scss';

function Settings() {
  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    name: '',
    sport: 'baseball',
  });

  const [teams, setTeams] = useState([]);
  const [newTeamName, setNewTeamName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentTeam, setCurrentTeam] = useState(null);
  const [activeTab, setActiveTab] = useState('userSettings');

  useEffect(() => {
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    const response = await fetch('http://localhost:8080/diamond-data/api/teams/get-all');
    if (!response.ok) {
      console.error('Failed to fetch teams');
      return;
    }
    const data = await response.json();
    setTeams(data);
  };

  const handleCreateTeam = async () => {
    const response = await fetch('http://localhost:8080/diamond-data/api/teams/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ name: newTeamName }),
    });
    if (!response.ok) {
      console.error('Error creating team');
      return;
    }
    fetchTeams();
    setNewTeamName('');
    setIsModalOpen(false); // Close modal after adding
  };

  const handleUpdateTeam = async () => {
    const response = await fetch(`http://localhost:8080/diamond-data/api/teams/update?id=${currentTeam.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id: currentTeam.id, name: currentTeam.name }),
    });
    if (!response.ok) {
      console.error('Error updating team');
      return;
    }
    fetchTeams();
    closeModal();
  };

  const handleDeleteTeam = async (id) => {
    const response = await fetch(`http://localhost:8080/diamond-data/api/teams/delete?id=${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) {
      console.error('Error deleting team');
      return;
    }
    fetchTeams();
    closeModal();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const selectTeam = (team) => {
    setCurrentTeam(team);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setCurrentTeam(null);
  };

  return (
    <div>
      <Navbar/>
      <div className="Settings">
        <div className="tab">
          <div className={`slider ${activeTab === 'userSettings' ? 'left' : 'right'}`}></div>
          <button onClick={() => setActiveTab('userSettings')} className={activeTab === 'userSettings' ? 'active' : ''}>User Settings</button>
          <button onClick={() => setActiveTab('teamManagement')} className={activeTab === 'teamManagement' ? 'active' : ''}>Team Management</button>
        </div>
        {activeTab === 'userSettings' && (
          <div className='user-settings-tab'>
            <h1>User Settings</h1>
            <form className="settings-form">
              <label>
                Name:
                <input type="text" name="name" value={formData.name} onChange={handleChange} />
              </label>
              <label>
                Email:
                <input type="email" name="email" value={formData.email} onChange={handleChange} />
              </label>
              <label>
                Phone Number:
                <input type="tel" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} />
              </label>
            </form>
          </div>
        )}
        {activeTab === 'teamManagement' && (
          <div className='team-management-tab'>
            <h1>Team Management</h1>
            <div className="team-form">
              <h2>Add New Team</h2>
              <input type="text" value={newTeamName} onChange={(e) => setNewTeamName(e.target.value)} placeholder="New Team Name" />
              <button onClick={handleCreateTeam} className='add-team-button'>Add Team</button>
            </div>
            <div className="teamList">
              <h2>Current Teams</h2>
              {teams.map((team) => (
                <div key={team.id} className="teamItem">
                  <div className="teamName">{team.name}</div>
                  <div>
                    <button onClick={() => selectTeam(team)}>Edit</button>
                    <button onClick={() => handleDeleteTeam(team.id)}>Delete</button>
                  </div>
                </div>
              ))}
            </div>
            {isModalOpen && (
              <div className="modal">
                <div className="modalContent">
                  <span className="close" onClick={closeModal}>&times;</span>
                  {currentTeam ? (
                    <>
                      <input type="text" value={currentTeam.name} onChange={(e) => setCurrentTeam({ ...currentTeam, name: e.target.value })} placeholder="Edit Team Name" />
                      <button onClick={handleUpdateTeam}>Update Team</button>
                      <button onClick={() => handleDeleteTeam(currentTeam.id)}>Delete Team</button>
                    </>
                  ) : (
                    <>
                      <input type="text" value={newTeamName} onChange={(e) => setNewTeamName(e.target.value)} placeholder="New Team Name" />
                      <button onClick={handleCreateTeam}>Add Team</button>
                    </>
                  )}
                </div>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default Settings;
