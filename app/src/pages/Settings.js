import React, { useState } from 'react';
import SettingsIcon from '@mui/icons-material/Settings';
import '../styles/Settings.css';

function Settings() {
  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    name: '',
    sport: 'baseball',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  return (
    <div className="Settings">
      <h1>Settings</h1>
      <form>
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
    </div>
  );
}

export default Settings;
