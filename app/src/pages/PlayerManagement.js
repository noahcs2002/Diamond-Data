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
  const [rawPlayerData, setRawPlayerData] = useState([]);

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
    fetchPlayers();
    fetchPitchers();
  }, []);

  const fetchPlayers = async () => {
    const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team';
    const url = new URL(endpoint);

    // url.searchParams.append("userId", 252);
    url.searchParams.append('teamId', 852);

    const data = await fetch(url)
      .then(res => {
        if (!res.ok) {
          throw new Error("network error")
        }
        return res.json();
      })
      
      let offensiveData = [];
      let defensiveData = [];
      data.map(r => {
        r.offensivePlayer.firstName = r.firstName;
        r.offensivePlayer.lastName = r.lastName;

        r.defensivePlayer.lastName = r.lastName;
        r.defensivePlayer.firstName = r.firstName;

        console.log('Player id: ', r.id);

        offensiveData.push(r.offensivePlayer);
        defensiveData.push(r.defensivePlayer);
      })

      setDefensiveData(defensiveData);
      setOffensiveData(offensiveData);
      setRawPlayerData(data);
  }

  const fetchPitchers = async () => {
    const endpoint = 'http://localhost:8080/diamond-data/api/pitchers/get-by-team';
    const url = new URL(endpoint);
    url.searchParams.append('teamId', 852);
    url.searchParams.append("userId", 252);
    const data = await fetch(url)
      .then(res => {
        if (!res.ok) {
          throw new Error("network error")
        }
        return res.json();
      })
      setPitcherData(data);
  }

  const handlePlayerDelete = async (id) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/players/delete';
    const url = new URL(endpoint);

    url.searchParams.append('id', id);

    await fetch(url)
      .then(res => {
        if(!res.ok) {
          throw new Error("Bad network");
        }
        return res.json();
      })
  }

  const handlePitcherDelete = async (id) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/pitcher/delete';
    const url = new URL(endpoint);

    url.searchParams.append('id', id);

    await fetch(url)
      .then(res => {
        if(!res.ok) {
          throw new Error("Bad network");
        }
        return res.json();
      })
  } 

  const handlePlayerCreate = async (playerCreationRequestModel) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/players/create';
    const url = new URL(endpoint);

    await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body: JSON.stringify(playerCreationRequestModel)
    })
      .then(res => {
        if(!res.ok) {
          throw new Error("Bad network");
        }
        return res.json();
      })
  }

  const handlePitcherCreate = async (pitcher, userId) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/pitcher/delete';
    const url = new URL(endpoint);

    url.searchParams.append('userId', userId);

    await fetch(url, {
      method : 'POST',
      headers: {  
        'Content-Type': 'application/json'
      },
      body:  JSON.stringify(pitcher)
    })
      .then(res => {
        if(!res.ok) {
          throw new Error("Bad network");
        }
        return res.json();
      })
  } 

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