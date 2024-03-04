import React, { useEffect, useState } from 'react'
import PlayerItem from '../components/PlayerItem';
import '../styles/PlayerManagement.scss';
import '../styles/PlayerItem.scss';
import Navbar from '../components/Navbar';
import { useTable } from 'react-table';
import AddCircleIcon from '@mui/icons-material/AddCircle';

function PlayerManagement({onEdit}) {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);

  const handleDelete = (fullName) => {
    // Logic to delete player
    console.log('Player deleted', fullName);
    setOffensiveData(offensiveData.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
    setDefensiveData(defensiveData.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
    setPitcherData(pitcherData.filter(player => `${player.firstName} ${player.lastName}` !== fullName));
  };

  const handleEdit = (oldFullName, newFullName) => {
    console.log('Player edited', oldFullName, 'to', newFullName);
  
    setOffensiveData(prevData => {
      return prevData.map(player => {
        if (`${player.firstName} ${player.lastName}` === oldFullName) {
          return { ...player, firstName: newFullName.split(' ')[0], lastName: newFullName.split(' ')[1] };
        }
        return player;
      });
    });
  
    setDefensiveData(prevData => {
      return prevData.map(player => {
        if (`${player.firstName} ${player.lastName}` === oldFullName) {
          return { ...player, firstName: newFullName.split(' ')[0], lastName: newFullName.split(' ')[1] };
        }
        return player;
      });
    });
  
    setPitcherData(prevData => {
      return prevData.map(player => {
        if (`${player.firstName} ${player.lastName}` === oldFullName) {
          return { ...player, firstName: newFullName.split(' ')[0], lastName: newFullName.split(' ')[1] };
        }
        return player;
      });
    });
  };
  
  

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const id = '202';
    const endpoints = [
      'http://localhost:8080/diamond-data/api/offensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/defensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/pitchers/get-by-team'
    ];

    const requests = endpoints.map(endpoint => {
      const url = new URL(endpoint);
      url.searchParams.append('teamId', id);
      return fetch(url)
        .then(res => {
          if (!res.ok) {
            throw new Error('Network response was not ok');
          }
          return res.json();
        });
    });
  
    try {
      const [offensive, defensive, pitchers] = await Promise.all(requests);
      setOffensiveData(offensive);
      setDefensiveData(defensive);
      setPitcherData(pitchers);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const columns = React.useMemo(() => [
    {
      Header: "FIRST NAME",
      accessor: "firstName",
    },
    {
      Header: "LAST NAME",
      accessor: "lastName",
    },
    // Add other columns as needed
  ], []);

  const offensiveTable = useTable({ columns, data: offensiveData });
  const defensiveTable = useTable({ columns, data: defensiveData });
  const pitcherTable = useTable({ columns, data: pitcherData });

  return (
    <div>
      <Navbar/>
      <div className="playerManagement">
        <h1 className='title'> Player Management</h1>
        <div className="positionList">
          <div className='positionContainer'>
            <h2>Offensive Players</h2>
            <div className='icons'>
              <AddCircleIcon onClick={onEdit} className='addCircleIcon' />
            </div>
            <div className='playerGrid'>
              {offensiveData.map(player => (
                <div className='playerItem' key={player.id}>
                  <PlayerItem
                    key={player.id} 
                    fullName={`${player.firstName} ${player.lastName}`}
                    onDelete={() => handleDelete(`${player.firstName} ${player.lastName}`)}
                    onEdit={(newFullName, editedName) => handleEdit(`${player.firstName} ${player.lastName}`, editedName)}
                  />
                </div>
              ))}
            </div>
          </div>
          <div className='positionContainer'>
            <h2>Defensive Players</h2>
            <div className='icons'>
              <AddCircleIcon onClick={onEdit} className='addCircleIcon' />
            </div>
            <div className='playerGrid'>
              {defensiveData.map(player => (
                <div className='playerItem' key={player.id}>
                  <PlayerItem
                    key={player.id} 
                    fullName={`${player.firstName} ${player.lastName}`}
                    onDelete={() => handleDelete(`${player.firstName} ${player.lastName}`)}
                    onEdit={(newFullName, editedName) => handleEdit(`${player.firstName} ${player.lastName}`, editedName)}
                  />
                </div>
              ))}
            </div>
          </div>
          <div className='positionContainer'>
            <h2>Pitchers</h2>
            <div className='icons'>
              <AddCircleIcon onClick={onEdit} className='addCircleIcon' />
            </div>
            <div className='playerGrid'>
              {pitcherData.map(player => (
                <div className='playerItem' key={player.id}>
                  <PlayerItem
                    key={player.id} 
                    fullName={`${player.firstName} ${player.lastName}`}
                    onDelete={() => handleDelete(`${player.firstName} ${player.lastName}`)}
                    onEdit={(newFullName, editedName) => handleEdit(`${player.firstName} ${player.lastName}`, editedName)}
                  />

                </div>
              ))}
            </div>
          </div>
        </div>

      </div>
    </div> 
  )
}

export default PlayerManagement;