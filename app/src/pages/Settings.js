import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/Settings.scss';

function Settings() {
  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    name: '',
  });

  const [team, setTeam] = useState([]);
  const [newTeamName, setNewTeamName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentTeam, setCurrentTeam] = useState(null);
  const [activeTab, setActiveTab] = useState('userSettings');

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    if (user) {
      setFormData({
        email: user.email,
        phoneNumber: user.phoneNumber,
        name: user.name
      });
      fetchTeam(user);
    }
  }, []);

  const updateUserName = async (userId, newName) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/auth/change-name';
    const url = new URL(endpoint);
    url.searchParams.append('userId', userId);
    url.searchParams.append('newName', newName);
  
    try {
      const response = await fetch(url, {
        method: 'PUT', 
      });
  
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(`Error updating user name: ${errorData.message}`);
      }
    } catch (error) {
      console.error('Error updating user name:', error);
      throw error;
    }
  };
  
  const updateUserPhoneNumber = async (userId, phoneNumber) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/auth/change-phone-number';
    const url = new URL(endpoint);
    url.searchParams.append('userId', userId);
    url.searchParams.append('phoneNumber', phoneNumber);
  
    try {
      const response = await fetch(url, {
        method: 'PUT', 
      });
  
      if (!response.ok) {
        const errorData = await response.json(); 
        throw new Error(`Error updating user phone number: ${errorData.message}`);
      }
    } catch (error) {
      console.error('Error updating user phone number:', error);
      throw error; 
    }
  };

  const handleSaveChanges = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    if (user) {
      try {
        await updateUserName(user.id, formData.name);
        await updateUserPhoneNumber(user.id, formData.phoneNumber);
        alert('User settings updated successfully.');
      } catch (error) {
        console.error('An error occurred while updating settings:', error);
        alert('An error occurred while updating settings. Please try again.');
      }
    } else {
      alert('User information not found. Please log in again.');
    }
  };
 
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
      setTeam(data); 
      return data;
    } 
    catch (error) {
      console.error('Error fetching teams:', error);
    }
  };


  const handleCreateTeam = async () => {
    const sessionInfo = JSON.parse(localStorage.getItem('sessionData'));
    delete sessionInfo.ghostedDate;
    const response = await fetch('http://localhost:8080/diamond-data/api/teams/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: newTeamName,
        user: sessionInfo
      }),
    });

    if (!response.ok) {
      console.error('Error creating team');
      return;
    }
    fetchTeam(JSON.parse(localStorage.getItem('sessionData'))); 
    setNewTeamName(''); 
    setIsModalOpen(false); 
  };

  const handleUpdateTeam = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    const url = new URL(`http://localhost:8080/diamond-data/api/teams/update`);
    url.searchParams.append('id', currentTeam.id);
    url.searchParams.append('userId', user.id); 
  
    try {
      const response = await fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: currentTeam.name,
          user: JSON.parse(localStorage.getItem('sessionData'))
        }),
      });
  
      if (!response.ok) {
        throw new Error('Error updating team');
      }
  
      fetchTeam(user);
      closeModal();
    } catch (error) {
      console.error('Error updating team:', error);
    }
  };

  const handleDeleteTeam = async (id) => {
    const url = new URL(`http://localhost:8080/diamond-data/api/teams/delete`)    
    const user = JSON.parse(localStorage.getItem('sessionData'));
    url.searchParams.append('id', id);
    url.searchParams.append('userId', user.id); 
    console.log(url);
  
    try {
      const response = await fetch(url, { method: 'DELETE' });
  
      if (!response.ok) {
        throw new Error(`Failed to delete team with id: ${id}`);
      }

      await fetchTeam(user); 
      closeModal(); 
    } 
    catch (error) {
      console.error('Error deleting team:', error);
    }
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
      <Navbar />
      <div className="Settings">
        <div className="tab">
          <div className={`slider ${activeTab === 'userSettings' ? 'left' : 'right'}`}></div>
          <button onClick={() => setActiveTab('userSettings')} className={activeTab === 'userSettings' ? 'active' : ''}>User Settings</button>
          <button onClick={() => setActiveTab('teamManagement')} className={activeTab === 'teamManagement' ? 'active' : ''}>Team Management</button>
        </div>
        {activeTab === 'userSettings' && (
          <div className='user-settings-tab'>
            <h1>User Settings</h1>
            <form className="settings-form" onSubmit={(e) => e.preventDefault()}>
              <label>
                Name:
                <input type="text" name="name" value={formData.name} onChange={handleChange} />
              </label>
              <label>
                Email:
                <input type="email" name="email" value={formData.email} onChange={handleChange} disabled />
              </label>
              <label>
                Phone Number:
                <input type="tel" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} />
              </label>
              <button type="button" onClick={handleSaveChanges} className="save-changes-btn">Save Changes</button>
            </form>
          </div>
        )}
        {activeTab === 'teamManagement' && (
          <div className='team-management-tab'>
            <h1>Team Management</h1>
            <div className="teamList">
              <h2>Edit your Team</h2>
              {team.map((teamItem) => (
                <div className="teamItem" key={teamItem.id}>
                  <div className="teamName">{teamItem.name}</div>
                  <div className='current-team-buttons'>
                    <button onClick={() => selectTeam(teamItem)}>Edit</button>
                    <button onClick={() => handleDeleteTeam(teamItem.id)}>Delete</button>
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
                      <h2>Update and Delete Team</h2>
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
      <Footer />
    </div>
  );
  
}

export default Settings;
