import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/Settings.scss';
import ConfirmModal from '../components/ConfirmModal';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function Settings() {
  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    name: '',
    newPassword: '',
    confirmNewPassword: ''
  });
  let currentId = React.useRef(null);

  const [team, setTeam] = useState([]);
  const [newTeamName, setNewTeamName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentTeam, setCurrentTeam] = useState(null);
  const [activeTab, setActiveTab] = useState('userSettings');
  const [confirmMessage, setConfirmMessage] = useState('');
  const [isUserSettingsModalOpen, setIsUserSettingsModalOpen] = useState(false);
  let user = {};

  useEffect(() => {
    user = JSON.parse(localStorage.getItem('sessionData'));
    
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
    } 
    catch (error) {
      toast.error('Error updaing username, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
    } 
    catch (error) {
      toast.error('Error updating phone number, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
    
  };

  const changePassword = async (userId, newPassword, confirmNewPassword) => {
    if (newPassword != confirmNewPassword) {
      toast.dismiss();
      toast.error('Passwords do not match!', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true 
      })
      throw new Error();
    }

    if (newPassword.length < 8) {
      toast.dismiss();
      toast.error('Please use a password longer than 8 characters', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true 
      }) 
      throw new Error();
    }

    const endpoint = 'http://localhost:8080/diamond-data/api/auth/change-password';
    const url = new URL(endpoint);
    url.searchParams.append('userId', userId);
    url.searchParams.append('newPassword', formData.newPassword);
    url.searchParams.append('confirmNewPassword', confirmNewPassword);

    const res = await fetch(url, {
      method: 'PUT',
    })

    const newUser = await res.json();
  }

  const handleSaveChangesClick = () => {
    setConfirmMessage('Are you sure you want to save these changes?');
    setIsUserSettingsModalOpen(true);
  };

  const confirmSaveChanges = async () => {
    setIsUserSettingsModalOpen(false); // Close the modal
    currentId.current = toast.loading('Saving changes...', {
      position:'bottom-right',
      autoClose: 1500,
      hideProgressBar:true,
      closeOnClick:true
    })
    try {
      const user = JSON.parse(localStorage.getItem('sessionData'));
      if (user) {
        await updateUserName(user.id, formData.name);
        await updateUserPhoneNumber(user.id, formData.phoneNumber);
        await changePassword(user.id, formData.newPassword, formData.confirmNewPassword);
  
        localStorage.setItem('sessionData', JSON.stringify({
          ...user,
          name: formData.name,
          phoneNumber: formData.phoneNumber
        }));
        
      }
      else {
        toast.error('Error saving data, please try again', {
          position:'bottom-right',
          autoClose: 2500,
          hideProgressBar:true,
          closeOnClick:true 
        }) 
      }
      toast.dismiss();
      toast.success('User information changed successfully!', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
      setIsUserSettingsModalOpen(false);
    } 
    catch (error) {
      toast.error('Error saving data, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
      toast.error('Error fetching teams, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
    toast.dismiss();
    toast.loading('Saving changes...', {
        position:'bottom-right',
        hideProgressBar:true,
    })
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
  
      const team = await fetchTeam(user);
      localStorage.setItem('cachedTeam', JSON.stringify(team));
      closeModal();
      toast.dismiss();
      toast.success('Team updated successfully!', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    } 
    catch (error) {
      toast.error('Error updating team, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
      console.error('Error updating team:', error);
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
      <ToastContainer/>
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
              <label>
                New Password:
                <input type="password" name="newPassword" value={formData.newPassword} onChange={handleChange} />
              </label>
              <label>
                Confirm New Password:
                <input type="password" name="confirmNewPassword" value={formData.confirmNewPassword} onChange={handleChange} />
              </label>
              <button className="saveChangesButton" type="button" onClick={handleSaveChangesClick}>Save Changes</button>
            </form>
          </div>
        )}
        {activeTab === 'teamManagement' && (
          <div className='team-management-tab'>
            <h1>Team Management</h1>
            <div className="teamList">
              <h2>Edit your Team</h2>
              <div className="teamItem" key={team.id}>
                  <div className="teamName">{team.name}</div>
                  <div className='current-team-buttons'>
                    <button onClick={() => selectTeam(team)}>Edit</button>
                  </div>
                </div>
            </div>
            {isModalOpen && (
              <div className="settingsModal">
                <div className="settingsModalContent">
                  <span className="close" onClick={closeModal}>&times;</span>
                  {currentTeam ? (
                    <>
                      <h2>Update Team Name</h2>
                      <input className='changeTeamName' type="text" value={currentTeam.name} onChange={(e) => setCurrentTeam({ ...currentTeam, name: e.target.value })} placeholder="Edit Team Name" />
                      <button className='updateTeamButton' onClick={handleUpdateTeam}>Update Team</button>
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
      <ConfirmModal 
      isOpen={isUserSettingsModalOpen}
      onClose={() => setIsUserSettingsModalOpen(false)}
      onConfirm={confirmSaveChanges}
      message={confirmMessage}
      />
      <Footer />
    </div>
  );
  
}

export default Settings;
