import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import PlayerStatsModal from '../components/PlayerStatsModal';
import { useTable } from 'react-table';
import '../styles/BulkEntry.scss';

function BulkEntry() {
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [currentTeamId, setCurrentTeamId] = useState('');
  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [isEnteringNewGame, setIsEnteringNewGame] = useState(false);
  const [newGameData, setNewGameData] = useState({ offensive: [], defensive: [], pitcher: [] });

  useEffect(() => {
    const fetchTeams = async () => {
      const response = await fetch('http://localhost:8080/diamond-data/api/teams/get-all');
      if (!response.ok) {
        console.error('Failed to fetch teams');
        return;
      }
      const teams = await response.json();
      setTeams(teams);
      if (teams.length > 0) {
        setCurrentTeamId(teams[0].id);
      }
    };
    fetchTeams();
  }, []);

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));


  useEffect(() => {
    const fetchData = async (teamId) => {
      const endpoints = [
        `http://localhost:8080/diamond-data/api/offensive-players/get-by-team?teamId=${teamId}`,
        `http://localhost:8080/diamond-data/api/defensive-players/get-by-team?teamId=${teamId}`,
        `http://localhost:8080/diamond-data/api/pitchers/get-by-team?teamId=${teamId}`
      ];

      try {
        const data = await Promise.all(endpoints.map(endpoint => fetch(endpoint).then(res => res.json())));
        setOffensiveData(data[0]);
        setDefensiveData(data[1]);
        setPitcherData(data[2]);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    if (currentTeamId) {
      fetchData(currentTeamId);
    }
  }, [currentTeamId]);

  const handlePlayerClick = (player) => {
    setSelectedPlayer(player);
    setShowModal(true);
  };

  const handleInputChange = (e, rowIndex, accessor, type) => {
    const updatedData = [...newGameData[type]];
    if (!updatedData[rowIndex]) {
      updatedData[rowIndex] = {};
    }
    updatedData[rowIndex][accessor] = parseInt(e.target.value, 10);
    setNewGameData({ ...newGameData, [type]: updatedData });
  };

  const saveGameData = async () => {
    console.log('Saving game data:', newGameData);
    setIsEnteringNewGame(false);
    setNewGameData({ offensive: [], defensive: [], pitcher: [] });
  };

  const makeColumnsEditable = (columns, type) => columns.map(column => ({
    ...column,
    Cell: ({ row, value }) => {
      return isEnteringNewGame ? (
        <input
          type="number"
          defaultValue={value}
          onChange={e => handleInputChange(e, row.index, column.accessor, type)}
        />
      ) : value;
    }
  }));

  const fetchTeams = async () => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all';
    const url = new URL(endpoint);
    url.searchParams.append("userId", 302);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Network error');
      }
      const data = await response.json();
      setTeams(data);
    } catch (error) {
      console.error('Error fetching teams:', error);
    }
  };
fetchTeams();

  const offensiveColumns = React.useMemo(() => makeColumnsEditable([
    { Header: "FIRST NAME", accessor: "firstName" },
    { Header: "LAST NAME", accessor: "lastName" },
    { Header: "AT BATS", accessor: "atBats" },
    { Header: "CAUGHT STEALING", accessor: "caughtStealing" },
    { Header: "DOUBLES", accessor: "doubles" },
    { Header: "EXTRA BASE HITS", accessor: "extraBaseHits" },
    { Header: "GRAND SLAMS", accessor: "grandSlams" },
    { Header: "GROUND INTO DOUBLE PLAY", accessor: "groundIntoDoublePlay" },
    { Header: "GROUND OUT AIR OUT", accessor: "groundOutAirOut" },
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
  ], 'pitcher'), []);

  const offensiveTableInstance = useTable({ columns: offensiveColumns, data: offensiveData });
  const defensiveTableInstance = useTable({ columns: defensiveColumns, data: defensiveData });
  const pitcherTableInstance = useTable({ columns: pitcherColumns, data: pitcherData });

 
  return (
    <div>
      <Navbar />
      <div className="bulkEntry">
        <h1 className="title">Bulk Entry</h1>
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
        <button onClick={() => setIsEnteringNewGame(true)}>New Game</button>
        {isEnteringNewGame && <button onClick={saveGameData}>Save Game</button>}
        {}
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
                        {isEnteringNewGame ? (
                          <input
                            type="number"
                            defaultValue={cell.value}
                            
                          />
                        ) : cell.render('Cell')}
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
                        {isEnteringNewGame ? (
                          <input
                            type="number"
                            defaultValue={cell.value}
                            
                          />
                        ) : cell.render('Cell')}
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
                        {isEnteringNewGame ? (
                          <input
                            type="number"
                            defaultValue={cell.value}
                          />
                        ) : cell.render('Cell')}
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
    </div>
  );
  
}

export default BulkEntry;