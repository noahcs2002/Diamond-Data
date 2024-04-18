import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import PlayerStatsModal from '../components/PlayerStatsModal';
import { useTable } from 'react-table';
import '../styles/BulkEntry.scss';
import LoadingScreen from '../components/LoadingScreen';

function BulkEntry() {
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [isEnteringNewGame, setIsEnteringNewGame] = useState(false);
  const [newGameData, setNewGameData] = useState({ offensive: [], defensive: [], pitcher: [] });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    prop();
  }, []);

  const prop = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    const team = await fetchTeam(user);
    await fetchPitcherData(team, user)
    const players = await fetchPlayers(team, user);
    await propogate(players);
    setLoading(false);
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
      alert('Unable to fetch teams: ', _e);
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
      console.log('Players: ', players);
      return players;
    }
    catch(_e) {
      console.error(_e);
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
      alert('Error getting pitcher player data: ', _e);
    }
  }

  const handleInputChange = (e, rowIndex, accessor, type, playerId) => {
    const updatedData = [...newGameData[type]];
    if (!updatedData[rowIndex]) {
      updatedData[rowIndex] = {id: playerId};
    }
    updatedData[rowIndex][accessor] = parseInt(e.target.value, 10);
    setNewGameData({ ...newGameData, [type]: updatedData});
    console.log(newGameData);
  };

  const saveGameData = async () => {
    setLoading(true);
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
    console.log(body);

    try {
      const res = await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(body),
        headers: {
          'Content-Type' : 'application/json'
        }
      })

      console.log(res);
    }
    catch(_e) {
      console.error(_e);
    }


    setIsEnteringNewGame(false);
    setNewGameData({ offensive: [], defensive: [], pitcher: [] });
    const updatedPitchers = await fetchPitcherData(team, user);
    const updatedPlayers = await fetchPlayers(team, user);
    localStorage.setItem('cachedPlayers', JSON.stringify(updatedPlayers));
    localStorage.setItem('cachedPitchers', JSON.stringify(updatedPitchers));
    await propogate(updatedPlayers);
    setPitcherData(updatedPitchers);
    setLoading(false);
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
    { Header: "CAUGHT STEALING", accessor: "caughtStealing" },
    { Header: "DOUBLES", accessor: "doubles" },
    { Header: "EXTRA BASE HITS", accessor: "extraBaseHits" },
    { Header: "GRAND SLAMS", accessor: "grandSlams" },
    { Header: "GROUND INTO DOUBLE PLAY", accessor: "groundIntoDoublePlay" },
    { Header: "HIT BY PITCH", accessor: "hitByPitch" },
    { Header: "HITS", accessor: "hits" },
    { Header: "HOME RUNS", accessor: "homeRuns" },
    { Header: "INTENTIONAL WALKS", accessor: "intentionalWalks" },
    { Header: "LEFT ON BASE", accessor: "leftOnBase" },
    { Header: "PLATE APPEARANCES", accessor: "plateAppearances" },
    { Header: "REACHED ON ERROR", accessor: "reachedOnError" },
    { Header: "RUNS", accessor: "runs" },
    { Header: "RUNS BATTED IN", accessor: "runsBattedIn" },
    { Header: "SACRIFICE BUNT", accessor: "sacrificeBunt" },
    { Header: "SACRIFICE FLY", accessor: "sacrificeFly" },
    { Header: "SINGLES", accessor: "singles" },
    { Header: "STOLEN BASES", accessor: "stolenBases" },
    { Header: "TOTAL BASES", accessor: "totalBases" },
    { Header: "TRIPLES", accessor: "triples" },
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
    { Header: "TOTAL CHANCES", accessor: "totalChances" },
    { Header: "TRIPLE PLAYS", accessor: "triplePlays" },
    // { Header: "ID", accessor: "id" }
  ], 'defensive'), []);

  const pitcherColumns = React.useMemo(() => makeColumnsEditable([
    { Header: "FIRST NAME", accessor: "firstName" },
    { Header: "LAST NAME", accessor: "lastName" },
    { Header: "APPEARANCES", accessor: "appearances" },
    { Header: "BALKS", accessor: "balks" },
    { Header: "BATTERS FACED", accessor: "battersFaced" },
    { Header: "BLOWN SAVES", accessor: "blownSaves" },
    { Header: "EARNED RUNS", accessor: "earnedRuns" },
    { Header: "FLYOUTS", accessor: "flyouts" },
    { Header: "GROUNDOUTS", accessor: "groundouts" },
    { Header: "HOLDS", accessor: "holds" },
    { Header: "INHERITED RUNNERS", accessor: "inheritedRunners" },
    { Header: "INNINGS PITCHED", accessor: "inningsPitched" },
    { Header: "NUMBER OF PITCHES", accessor: "numberOfPitches" },
    { Header: "PICKOFFS", accessor: "pickoffs" },
    { Header: "QUALITY STARTS", accessor: "qualityStarts" },
    { Header: "RELIEF WINS", accessor: "reliefWins" },
    { Header: "SAVES", accessor: "saves" },
    { Header: "SAVE OPPORTUNITIES", accessor: "saveOpportunities" },
    { Header: "SHUTOUTS", accessor: "shutouts" },
    { Header: "STRIKEOUTS", accessor: "strikeouts" },
    { Header: "UNEARNED RUNS", accessor: "unearnedRuns" },
    { Header: "WALKS AND HITS PER INNING PITCHED", accessor: "walksAndHitsPerInningPitched" },
    { Header: "WILD PITCHES", accessor: "wildPitches" },
    { Header: "WINS", accessor: "wins" },
    { Header: "WALKS", accessor: "walks" },
    // { Header: "ID", accessor: "id" }
  ], 'pitcher'), []);

  const offensiveTableInstance = useTable({ columns: offensiveColumns, data: offensiveData });
  const defensiveTableInstance = useTable({ columns: defensiveColumns, data: defensiveData });
  const pitcherTableInstance = useTable({ columns: pitcherColumns, data: pitcherData });
 
  return (
    <div>
      {loading ? <LoadingScreen/> : <>
      <Navbar />
      <div className="bulkEntry">
        <div className='bulkEntryHeading'>
          <h1 className="title">Bulk Entry</h1>
          <button  className='bulkEntryButtons' onClick={() => setIsEnteringNewGame(true)}>New Game</button>
          {isEnteringNewGame && <button className='bulkEntryButtons' onClick={saveGameData}>Save Game</button>}
          {isEnteringNewGame && <button className='bulkEntryButtons' onClick={cancelNewGame}>Cancel</button>}
          {}
        </div>
        <h2>Offensive Data</h2>
          <div className="tableWrapper">
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
        {}
        <h2>Defensive Data</h2>
<div className="tableWrapper">
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
                    defaultValue={cell.value}
                    onChange={e => handleInputChange(e, row.index, cell.column.id, 'defensive', row.original.id)}
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
        {}
        
<h2>Pitcher Data</h2>
<div className="tableWrapper">
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
                  <input
                    type="number"
                    defaultValue={cell.value}
                    onChange={e => handleInputChange(e, row.index, cell.column.id, 'pitcher', row.original.id)}
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
      {showModal && <PlayerStatsModal player={selectedPlayer} onClose={() => setShowModal(false)} />}
      <Footer /></>}
    </div>
  );
  
}

export default BulkEntry;