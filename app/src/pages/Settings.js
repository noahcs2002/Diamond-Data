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
  const [editTeamId, setEditTeamId] = useState(null);
  const [editTeamName, setEditTeamName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentTeam, setCurrentTeam] = useState(null); // For storing the currently selected team

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

  const handleCreateTeam = async () => {
    try {
      const response = await fetch('http://localhost:8080/diamond-data/api/teams/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: newTeamName }),
      });
      if (!response.ok) {
        throw new Error('Failed to create team');
      }
      fetchTeams();
      setNewTeamName('');
    } catch (error) {
      console.error('Error creating team:', error);
    }
  };

  const handleUpdateTeam = async () => {
    try {
      const response = await fetch(`http://localhost:8080/diamond-data/api/teams/update?id=${currentTeam.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id: currentTeam.id, name: currentTeam.name }),
      });
      if (!response.ok) {
        throw new Error('Failed to update team');
      }
      fetchTeams();
      closeModal(); // Close modal after update
    } catch (error) {
      console.error('Error updating team:', error);
    }
  };

  const handleDeleteTeam = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/diamond-data/api/teams/delete?id=${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete team');
      }
      fetchTeams();
      closeModal(); // Close modal after delete
    } catch (error) {
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

  // New method to open modal with selected team
  const selectTeam = (team) => {
    setCurrentTeam(team);
    setIsModalOpen(true);
  };

  // New method to close modal
  const closeModal = () => {
    setIsModalOpen(false);
    setCurrentTeam(null); // Reset current team when modal is closed
  };

  return (
    <div>
      <Navbar/>
      <div className="Settings">
        <h1>Settings</h1>
        <form className="settings-form">
          <label>
            Name:
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
            />
          </label>
          <label>
            Email:
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
          </label>
          <label>
            Phone Number:
            <input
              type="tel"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
            />
          </label>
          <label>
            Choose Sport:
            <select
              name="sport"
              value={formData.sport}
              onChange={handleChange}
            >
              <option value="baseball">Baseball</option>
              <option value="softball">Softball</option>
            </select>
          </label>
        </form>

        <h2>Team Management</h2>
        <div className="teamManagement">
          <input
            type="text"
            value={newTeamName}
            onChange={(e) => setNewTeamName(e.target.value)}
            placeholder="New Team Name"
          />
          <button onClick={handleCreateTeam}>Add Team</button>
        </div>
        <div className="teamList">
          {teams.length > 0 ? (
            teams.map((team) => (
              <div key={team.id} className="teamItem">
                <div className="teamInfo">
                  <div className="teamName">{team.name}</div>
                  <button onClick={() => selectTeam(team)}>Edit</button> {/* Change Delete to Edit */}
                </div>
              </div>
            ))
          ) : (
            <div>No teams available</div>
          )}
        </div>
        {isModalOpen && (
          <div className="modal">
            <div className="modalContent">
              <h3>Edit Team</h3>
              <input
                type="text"
                value={currentTeam?.name}
                onChange={(e) => setCurrentTeam({ ...currentTeam, name: e.target.value })}
                placeholder="Edit Team Name"
              />
              <button onClick={handleUpdateTeam}>Update Team</button>
              <button onClick={() => handleDeleteTeam(currentTeam.id)}>Delete Team</button>
              <button onClick={closeModal}>Close</button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default Settings;
