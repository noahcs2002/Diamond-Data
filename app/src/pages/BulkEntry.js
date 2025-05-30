import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import PlayerStatsModal from '../components/PlayerStatsModal';
import { useTable } from 'react-table';
import '../styles/BulkEntry.scss';
import LoadingScreen from '../components/LoadingScreen';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function BulkEntry() {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [isEnteringNewGame, setIsEnteringNewGame] = useState(false);
  const [newGameData, setNewGameData] = useState({ offensive: [], defensive: [], pitcher: [] });
  const [loading, setLoading] = useState(true);
  const nav = useNavigate();

  useEffect(() => {
    prop();
  }, []);

  const prop = async () => {
    const user = await JSON.parse(localStorage.getItem('sessionData'));

    if(user === undefined || user === null) {
      nav('/')
      return;
    }
    
    const team = await fetchTeam(user);
    await fetchPitcherData(team, user)
    const players = await fetchPlayers(team, user);
    await propogate(players);
    setLoading(false);
    toast.success('Data loaded successfully!', {
      position:'bottom-right',
      autoClose: 1500,
      hideProgressBar:true,
      closeOnClick:true
    })
  }

  const propogate = async (players) => {
    let off = [];
    let def = [];

    players.map((player) => {
      let o = player.offensivePlayer;
      let d = player.defensivePlayer;
      o.firstName = player.firstName;
      o.lastName = player.lastName;
      d.firstName = player.firstName;
      d.lastName = player.lastName;
      off.push(o);
      def.push(d);
    });

    setOffensiveData(off);
    setDefensiveData(def);
  }

  const fetchTeam = async (user) => {
    const endpoint = "http://localhost:8080/diamond-data/api/teams/get-by-user"
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);

    try {
      const res = await fetch(url);

      if (!res.ok) {
        alert('Network not OK after fetching teams');
      }

      const data = await res.json();
      localStorage.setItem('team', data);
      return data
    }
    catch(_e) {
      toast.error('Error loading team', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  }

  const fetchPlayers = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);

      if (!res.ok) {
        alert('Not ok in player fetching')
      }

      const players = await res.json();
      
      return players;
    }
    catch(_e) {
      toast.error('Error loading players', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  }

  const fetchPitcherData = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/pitchers/get-by-team'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);
      if (!res.ok) {
        alert('Error getting pitcher player data, status was not OK')
      }
      const data = await res.json();
      setPitcherData(data);
      return data;
    }
    catch(_e) {
      toast.error('Error loading pitcher data, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  }

  const handleInputChange = (e, rowIndex, accessor, type, playerId) => {
    const updatedData = [...newGameData[type]];
    if (!updatedData[rowIndex]) {
      updatedData[rowIndex] = {id: playerId};
    }
    updatedData[rowIndex][accessor] = parseFloat(e.target.value, 10);
    setNewGameData({ ...newGameData, [type]: updatedData});
    
  };

  const saveGameData = async () => {
    toast.loading('Saving new game data', {
      position:'bottom-right',
      hideProgressBar:true,
    })
    setLoading(true);
    setIsEnteringNewGame(false);
    setNewGameData({ offensive: [], defensive: [], pitcher: [] });
    newGameData.offensive = newGameData.offensive.filter(o => o !== undefined);
    newGameData.defensive = newGameData.offensive.filter(o => o !== undefined);
    newGameData.pitcher = newGameData.pitcher.filter(o => o !== undefined);
    // Iterate over offensive data
    newGameData.offensive.forEach(offense => {
      // Find the offensive player with the matching id
      const player = offensiveData.find(player => player.id === offense.id);
      if (player) {
        // Update the player's stats
        Object.keys(offense).forEach(stat => {
          if (stat !== 'id') { // Skip 'id' property
            player[stat] = offense[stat];
          }
        });
      }
    });

    // Iterate over defensive data
    newGameData.defensive.forEach(defense => {
      // Find the defensive player with the matching id
      const player = defensiveData.find(player => player.id === defense.id);
      if (player) {
        // Update the player's stats
        Object.keys(defense).forEach(stat => {
          if (stat !== 'id') { // Skip 'id' property
            player[stat] = defense[stat];
          }
        });
      }
    });

    // Iterate over pitcher data
    newGameData.pitcher.forEach(pitcher => {
      // Find the pitcher player with the matching id
      const player = pitcherData.find(player => player.id === pitcher.id);
      if (player) {
        // Update the player's stats
        Object.keys(pitcher).forEach(stat => {
          if (stat !== 'id') { // Skip 'id' property
            player[stat] = pitcher[stat];
          }
        });
      }
    });
    const endpoint = 'http://localhost:8080/diamond-data/api/stats/record-new-game';
    const user = await JSON.parse(localStorage.getItem('sessionData'));
    let team = {};

    try {
      team = await JSON.parse(localStorage.getItem('cachedTeam'));
      if (team === null || team === undefined) {
        throw new Error();
      }
    }
    catch {
      team = await fetchTeam(user);
    }

    const url = new URL(endpoint);
    url.searchParams.append('teamId', team.id);
    url.searchParams.append('userId', user.id);
    const body = {newPitchers: pitcherData, newOffensivePlayers: offensiveData, newDefensivePlayers: defensiveData};
    

    try {
      const res = await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(body),
        headers: {
          'Content-Type' : 'application/json'
        }
      })

      
    }
    catch(_e) {
      toast.error('Error saving game, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }

    const updatedPitchers = await fetchPitcherData(team, user);
    const updatedPlayers = await fetchPlayers(team, user);
    localStorage.setItem('cachedPlayers', JSON.stringify(updatedPlayers));
    localStorage.setItem('cachedPitchers', JSON.stringify(updatedPitchers));
    await propogate(updatedPlayers);
    setPitcherData(updatedPitchers);
    setLoading(false);
    toast.dismiss();
    toast.success('New game data saved successfully!', {
      position:'bottom-right',
      autoClose: 2500,
      hideProgressBar:true,
      closeOnClick:true 
    })
  };

  const cancelNewGame = () => {
    setIsEnteringNewGame(false);
    setNewGameData({ offensive: [], defensive: [], pitcher: [] });
  };

  const makeColumnsEditable = (columns, type) =>
  columns.map(column => ({
    ...column,
    Cell: ({ row, value, column: { id } }) => {
      return isEnteringNewGame && id !== 'firstName' && id !== 'lastName' ? (
        <input
          type="number"
          defaultValue={value}
          onChange={e => handleInputChange(e, row.index, id, type, row.original.id)}
        />
      ) : (
        <span>{value}</span>
      );
    },
  }));

  const offensiveColumns = React.useMemo(() => makeColumnsEditable([
    { Header: "FIRST NAME", accessor: "firstName" },
    { Header: "LAST NAME", accessor: "lastName" },
    { Header: "AT BATS", accessor: "atBats" },
    { Header: "RUNS", accessor: "runs" },
    { Header: "SINGLES", accessor: "singles" },
    { Header: "DOUBLES", accessor: "doubles" },
    { Header: "TRIPLES", accessor: "triples" },
    { Header: "HOME RUNS", accessor: "homeRuns" },
    { Header: "RUNS BATTED IN", accessor: "runsBattedIn" },
    { Header: "INTENTIONAL WALKS", accessor: "intentionalWalks" },
    { Header: "STRIKEOUTS", accessor: "strikeouts" },
    { Header: "STOLEN BASES", accessor: "stolenBases" },
    { Header: "CAUGHT STEALING", accessor: "caughtStealing" },
    // { Header: "EXTRA BASE HITS", accessor: "extraBaseHits" },
    { Header: "HIT BY PITCH", accessor: "hitByPitch" },
    { Header: "SACRIFICE BUNT", accessor: "sacrificeBunt" },
    { Header: "SACRIFICE FLY", accessor: "sacrificeFly" },
    { Header: "GRAND SLAMS", accessor: "grandSlams" },
    { Header: "GROUND INTO DOUBLE PLAY", accessor: "groundIntoDoublePlay" },
    { Header: "LEFT ON BASE", accessor: "leftOnBase" },
    { Header: "PLATE APPEARANCES", accessor: "plateAppearances" },
    { Header: "REACHED ON ERROR", accessor: "reachedOnError" },
    { Header: "WALKS", accessor: "walks" },
    { Header: "WALK OFFS", accessor: "walkOffs" },
    // { Header: "ID", accessor: "id" }
  ], 'offensive'), []);

  const defensiveColumns = React.useMemo(() => makeColumnsEditable([
    { Header: "FIRST NAME", accessor: "firstName" },
    { Header: "LAST NAME", accessor: "lastName" },
    { Header: "ASSISTS", accessor: "assists" },
    { Header: "DOUBLE PLAY", accessor: "doublePlay" },
    { Header: "ERRORS", accessor: "errors" },
    { Header: "INNINGS PLAYED", accessor: "inningsPlayed" },
    { Header: "OUTS", accessor: "outs" },
    { Header: "OUTFIELD ASSISTS", accessor: "outfieldAssists" },
    { Header: "PASSED BALLS", accessor: "passedBalls" },
    { Header: "PUTOUTS", accessor: "putouts" },
    { Header: "TRIPLE PLAYS", accessor: "triplePlays" },
    // { Header: "ID", accessor: "id" }
  ], 'defensive'), []);

  const pitcherColumns = React.useMemo(() => makeColumnsEditable([
    { Header: "FIRST NAME", accessor: "firstName" },
    { Header: "LAST NAME", accessor: "lastName" },
    { Header: "WINS", accessor: "wins" },
    { Header: "LOSSES", accessor: "losses" },
    { Header: "APPEARANCES", accessor: "appearances" },
    { Header: "GAMES STARTED", accessor: "gamesStarted" },
    { Header: "GAMES FINISHED", accessor: "gamesFinished" },
    { Header: "SHUTOUTS", accessor: "shutouts" },
    { Header: "SAVES", accessor: "saves" },
    { Header: "SAVE OPPORTUNITIES", accessor: "saveOpportunities" },
    { Header: "INNINGS PITCHED", accessor: "inningsPitched" },
    { Header: "HITS", accessor: "hits" },
    { Header: "UNEARNED RUNS", accessor: "unearnedRuns" },
    { Header: "EARNED RUNS", accessor: "earnedRuns" },
    { Header: "STRIKEOUTS", accessor: "strikeouts" },
    { Header: "WALKS", accessor: "walks" },
    { Header: "NUMBER OF PITCHES", accessor: "numberOfPitches" },
    { Header: "WILD PITCHES", accessor: "wildPitches" },
    { Header: "BATTERS FACED", accessor: "battersFaced" },
    { Header: "FLYOUTS", accessor: "flyouts" },
    { Header: "GROUNDOUTS", accessor: "groundouts" },
    { Header: "BLOWN SAVES", accessor: "blownSaves" },
    { Header: "INHERITED RUNNERS", accessor: "inheritedRunners" },
    { Header: "HOLDS", accessor: "holds" },
    { Header: "PICKOFFS", accessor: "pickoffs" },
    { Header: "QUALITY STARTS", accessor: "qualityStarts" },
    { Header: "RELIEF WINS", accessor: "reliefWins" },
    { Header: "BALKS", accessor: "balks" },
    // { Header: "ID", accessor: "id" }
  ], 'pitcher'), []);

  const offensiveTableInstance = useTable({ columns: offensiveColumns, data: offensiveData });
  const defensiveTableInstance = useTable({ columns: defensiveColumns, data: defensiveData });
  const pitcherTableInstance = useTable({ columns: pitcherColumns, data: pitcherData });
 
  return (
    <div>
      <ToastContainer/>
      {loading ? <LoadingScreen/> : <>
      <Navbar />
      <div className="bulkEntry">
        <div className='bulkEntryHeading'>
          <h1 className="title">Game Day</h1>
          <button  className='bulkEntryButtons' onClick={() => setIsEnteringNewGame(true)}>New Game</button>
          {isEnteringNewGame && <button className='bulkEntryButtons' onClick={saveGameData}>Save Game</button>}
          {isEnteringNewGame && <button className='bulkEntryButtons' onClick={cancelNewGame}>Cancel</button>}
          {}
        </div>
        <h2>Offensive Data</h2>
          <div className="tableWrapper">
            <div className='offensiveTable'>
            <table {...offensiveTableInstance.getTableProps()}>
              <thead>
                {offensiveTableInstance.headerGroups.map(headerGroup => (
                  <tr {...headerGroup.getHeaderGroupProps()}>
                    {headerGroup.headers.map(column => (
                      <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                    ))}
                  </tr>
                ))}
              </thead>
              <tbody {...offensiveTableInstance.getTableBodyProps()}>
                {offensiveTableInstance.rows.map(row => {
                  offensiveTableInstance.prepareRow(row);
                  return (
                    <tr {...row.getRowProps()}>
                      {row.cells.map(cell => (
                        <td {...cell.getCellProps()}>
                          {isEnteringNewGame && cell.column.id !== 'firstName' && cell.column.id !== 'lastName' ? (
                            <input
                              type="number"
                              defaultValue={cell.value}
                              min="0"
                              onChange={e => handleInputChange(e, row.index, cell.column.id, 'offensive', row.original.id)}
                              onKeyDown={(e) => {
                              if (e.key === '-' || e.key === 'e') {
                                 e.preventDefault();
                              }
                              }}
                                />) : (
                            cell.render('Cell')
                             )}
                          </td>
                      ))}
                    </tr>
                  );
                })}
              </tbody>
            </table>
            </div>
          </div>
        {}
        <h2>Defensive Data</h2>
<div className="tableWrapper">
  <div className='defensiveTable'>
  <table {...defensiveTableInstance.getTableProps()}>
    <thead>
      {defensiveTableInstance.headerGroups.map(headerGroup => (
        <tr {...headerGroup.getHeaderGroupProps()}>
          {headerGroup.headers.map(column => (
            <th {...column.getHeaderProps()}>{column.render('Header')}</th>
          ))}
        </tr>
      ))}
    </thead>
    <tbody {...defensiveTableInstance.getTableBodyProps()}>
      {defensiveTableInstance.rows.map(row => {
        defensiveTableInstance.prepareRow(row);
        return (
          <tr {...row.getRowProps()}>
            {row.cells.map(cell => (
              <td {...cell.getCellProps()}>
                {isEnteringNewGame && cell.column.id !== 'firstName' && cell.column.id !== 'lastName' ? (
                  <input
                    type="number"
                    min="0"
                    defaultValue={cell.value}
                    onChange={e => handleInputChange(e, row.index, cell.column.id, 'defensive', row.original.id)}
                    onKeyDown={(e) => {
                      if (e.key === '-' || e.key === 'e') {
                         e.preventDefault();
                      }
                      }}
                  />
                ) : (
                  cell.render('Cell')
                )}
              </td>
            ))}
          </tr>
        );
      })}
    </tbody>
  </table>
  </div>
</div>
        {}
        
<h2>Pitcher Data</h2>
<div className="tableWrapper">
  <div className='pitcherTable'>
  <table {...pitcherTableInstance.getTableProps()}>
    <thead>
      {pitcherTableInstance.headerGroups.map(headerGroup => (
        <tr {...headerGroup.getHeaderGroupProps()}>
          {headerGroup.headers.map(column => (
            <th {...column.getHeaderProps()}>{column.render('Header')}</th>
          ))}
        </tr>
      ))}
    </thead>
    <tbody {...pitcherTableInstance.getTableBodyProps()}>
      {pitcherTableInstance.rows.map(row => {
        pitcherTableInstance.prepareRow(row);
        return (
          <tr {...row.getRowProps()}>
            {row.cells.map(cell => (
              <td {...cell.getCellProps()}>
              {isEnteringNewGame && cell.column.id !== 'firstName' && cell.column.id !== 'lastName' ? (
                cell.column.id === 'inningsPitched' ? (
                  <input
                    type="number"
                    defaultValue={cell.value}
                    step="0.1" // Allow decimals
                    min="0"
                    onChange={e => handleInputChange(e, row.index, cell.column.id, 'pitcher', row.original.id)}
                  />
                ) : (
                  <input
                    type="number"
                    defaultValue={cell.value}
                    min="0"
                    onChange={e => handleInputChange(e, row.index, cell.column.id, 'pitcher', row.original.id)}
                  />
                )
              ) : (
                cell.render('Cell')
              )}
            </td>
            ))}
          </tr>
        );
      })}
    </tbody>
  </table>
  </div>
</div>
      </div>
      {showModal && <PlayerStatsModal player={selectedPlayer} onClose={() => setShowModal(false)} />}
      <Footer /></>}
    </div>
  );
  
}

export default BulkEntry;
