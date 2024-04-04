import React, { useEffect, useState } from 'react';
import '../styles/PlayerStats.scss';
import Navbar from '../components/Navbar';
import { useTable } from 'react-table';
import PlayerStatsModal from '../components/PlayerStatsModal';

function PlayerStats() {

  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [showModal, setShowModal] = useState(false);
 

  
  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    console.log("showModal state:", showModal);
  }, [showModal]);

  const fetchData = async () => {
    const id = '202';
    const endpoints = [
      'http://localhost:8080/diamond-data/api/offensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/defensive-players/get-by-team',
      'http://localhost:8080/diamond-data/api/pitchers/get-by-team'
    ];

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
}

const handlePlayerClick = (player) => {
  console.log('Player clicked:', player);
  setSelectedPlayer(player);
  setShowModal(true);
}


const handleSearch = (playerName) => {
  let foundPlayer = null;
  // Search for the player by name
  offensiveData.concat(defensiveData, pitcherData).some(player => {
    if (
      player.firstName.toLowerCase().includes(playerName.toLowerCase()) ||
      player.lastName.toLowerCase().includes(playerName.toLowerCase())
    ) {
      foundPlayer = player;
      return true; // Stop searching after finding the first match
    }
    return false;
  });

  if (foundPlayer) {
    setSelectedPlayer(foundPlayer);
  } else {
    console.error('Player not found');
  }
}

  const pitcherColumns = React.useMemo(() => [
    {
      Header: "FIRST NAME",
      accessor: "firstName",
    },
    {
      Header: "LAST NAME",
      accessor: "lastName",
    },
    {
      Header: "PREFERENCE",
      accessor: "preference",
    },
    {
      Header: "APPEARANCES",
      accessor: "appearances",
    },
    {
      Header: "BALKS",
      accessor: "balks",
    },
    {
      Header: "BATTERS FACES",
      accessor: "battersFaces",
    },
    {
      Header: "BLOWN SAVES",
      accessor: "blownSaves",
    },
    {
      Header: "COMPLETE GAMES",
      accessor: "completeGames",
    },
    {
      Header: "EARNED RUNS",
      accessor: "earnedRuns",
    },
    {
      Header: "EARNED RUN AVERAGE",
      accessor: "earnedRunAverage",
    },
    {
      Header: "FLYOUTS",
      accessor: "flyouts",
    },
    {
      Header: "GAMES FINISHED",
      accessor: "gamesFinished",
    },
    {
      Header: "GAMES STARTED",
      accessor: "gamesStarted",
    },
    {
      Header: "GROUNDOUTS",
      accessor: "groundouts",
    },
    {
      Header: "HOLDS",
      accessor: "holds",
    },
    {
      Header: "INHERITED RUNNERS",
      accessor: "inheritedRunners",
    },
    {
      Header: "INNINGS PITCHED",
      accessor: "inningsPitched",
    },
    {
      Header: "LOSSES",
      accessor: "losses",
    },
    {
      Header: "NUMBER OF PITCHES",
      accessor: "numberOfPitches",
    },
    {
      Header: "PICKOFFS",
      accessor: "pickoffs",
    },
    {
      Header: "QUALITY STARTS",
      accessor: "qualityStarts",
    },
    {
      Header: "RELIEF WINS",
      accessor: "reliefWins",
    },
    {
      Header: "SAVES",
      accessor: "saves",
    },
    {
      Header: "SAVE OPPORTUNITIES",
      accessor: "saveOpportunities",
    },
    {
      Header: "SAVE PERCENTAGE",
      accessor: "savePercentage",
    },
    {
      Header: "SHUTOUTS",
      accessor: "shutouts",
    },
    {
      Header: "STRIKEOUTS",
      accessor: "strikeouts",
    },
    {
      Header: "UNEARNED RUNS",
      accessor: "unearnedRuns",
    },
    {
      Header: "WALKS AND HITS PER INING PITCHED",
      accessor: "walksAndHitsPerInningPitched",
    },
    {
      Header: "WILD PITCHES",
      accessor: "wildPitches",
    },
    {
      Header: "WINS",
      accessor: "wins",
    },
    {
      Header: "WINNING PERCENTAGE",
      accessor: "winningPercentage",
    },
    {
      Header: "WALKS",
      accessor: "walks",
    }
  ],
  []);

  const defensiveColumns = React.useMemo(() => [
    {
      Header: "FIRST NAME",
      accessor: "firstName",
    },
    {
      Header: "LAST NAME",
      accessor: "lastName",
    },
    {
      Header: "ASSISTS",
      accessor: "assists",
    },
    {
      Header: "CAUGHT STEALING PERCENT",
      accessor: "caughtStealingPercent",
    },
    {
      Header: "DOUBLE PLAY",
      accessor: "doublePlay",
    },
    {
      Header: "ERRORS",
      accessor: "errors",
    },
    {
      Header: "FIELDING PERCENTAGE",
      accessor: "fieldingPercentage",
    },
    {
      Header: "INNINGS PLAYED",
      accessor: "inningsPlayed",
    },
    {
      Header: "OUTS",
      accessor: "outs",
    },
    {
      Header: "OUTFIELD ASSISTS",
      accessor: "outfieldAssists",
    },
    {
      Header: "PASSED BALLS",
      accessor: "passedBalls",
    },
    {
      Header: "PUTOUTS",
      accessor: "putouts",
    },
    {
      Header: "TOTAL CHANCES",
      accessor: "totalChances",
    },
    {
      Header: "TRIPLE PLAYS",
      accessor: "triplePlays",
    },
    // {
    //   Header: "GHOSTED DATE",
    //   accessor: "ghostedDate",
    // },
  ], []);

  const offensiveColumns = React.useMemo(() => [
    {
      Header: "FIRST NAME",
      accessor: "firstName",
    },
    {
      Header: "LAST NAME",
      accessor: "lastName",
    },
    {
      Header: "AT BATS",
      accessor: "atBats",
    },
    {
      Header: "BATTING AVERAGE",
      accessor: "battingAverage",
    },
    {
      Header: "CAUGHT STEALING",
      accessor: "caughtStealing",
    },
    {
      Header: "DOUBLES",
      accessor: "doubles",
    },
    {
      Header: "EXTRA BASE HITS",
      accessor: "extraBaseHits",
    },
    {
      Header: "GAMES PLAYED",
      accessor: "gamesPlayed",
    },
    {
      Header: "GRAND SLAMS",
      accessor: "grandSlams",
    },
    {
      Header: "GROUND INTO DOUBLE PLAY",
      accessor: "groundIntoDoublePlay",
    },
    {
      Header: "GROUND OUT AIR OUT",
      accessor: "groundOutAirOut",
    },
    {
      Header: "HIT BY PITCH",
      accessor: "hitByPitch",
    },
    {
      Header: "HITS",
      accessor: "hits",
    },
    {
      Header: "HOME RUNS",
      accessor: "homeRuns",
    },
    {
      Header: "INTENTIONAL WALKS",
      accessor: "intentionalWalks",
    },
    {
      Header: "LEFT ON BASE",
      accessor: "leftOnBase",
    },
    {
      Header: "ON BASE PERCENTAGE",
      accessor: "onBasePercentage",
    },
    {
      Header: "ON BASE PLUS SLUGGING",
      accessor: "onBasePlusSlugging",
    },
    {
      Header: "PLATE APPEARANCES",
      accessor: "plateAppearances",
    },
    {
      Header: "REACHED ON ERROR",
      accessor: "reachedOnError",
    },
    {
      Header: "RUNS",
      accessor: "runs",
    },
    {
      Header: "RUNS BATTED IN",
      accessor: "runsBattedIn",
    },
    {
      Header: "SACRIFICE BUNT",
      accessor: "sacrificeBunt",
    },
    {
      Header: "SACRIFICE FLY",
      accessor: "sacrificeFly",
    },
    {
      Header: "SINGLES",
      accessor: "singles",
    },
    {
      Header: "SLUGGING PERCENTAGE",
      accessor: "sluggingPercentage",
    },
    {
      Header: "STOLEN BASES",
      accessor: "stolenBases",
    },
    {
      Header: "TOTAL BASES",
      accessor: "totalBases",
    },
    {
      Header: "TRIPLES",
      accessor: "triples",
    },
    {
      Header: "WALKS",
      accessor: "walks",
    },
    {
      Header: "WALK OFFS",
      accessor: "walkOffs",
    },
    // {
    //   Header: "GHOSTED DATE",
    //   accessor: "ghostedDate",
    // },

  ], []);

  const offensiveTable = useTable({ columns: offensiveColumns, data: offensiveData });
  const defensiveTable = useTable({ columns: defensiveColumns, data: defensiveData });
  const pitcherTable = useTable({ columns: pitcherColumns, data: pitcherData });

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));


  return (
    <div>
      <Navbar/>
      <div className="playerStats">
        <h1 className='title'> Player Stats</h1>
        <div className="teamSelection">
          <select
            id="teamSelect"
            value={selectedTeam}
            onChange={e => setSelectedTeam(e.target.value)}
          >
            {teamOptions}
          </select>
        </div>
        <h2>Offensive Data</h2>
        <div className='tableWrapper'>
          <div className='offensiveTable'>
            <table {...offensiveTable.getTableProps()}>
              <thead>
                {offensiveTable.headerGroups.map((headerGroup) => (
                  <tr {...headerGroup.getHeaderGroupProps()}>
                    {headerGroup.headers.map((column) => (
                      <th {...column.getHeaderProps()}>
                        {column.render("Header")}
                      </th>
                    ))}
                  </tr>
                ))}
              </thead>
              <tbody {...offensiveTable.getTableBodyProps()}>
                {offensiveTable.rows.map((row) => {
                  offensiveTable.prepareRow(row)
                  return (
                    <tr {...row.getRowProps()}>
                      {row.cells.map((cell) => (
                        <td key={cell.column.id} {...cell.getCellProps()}>
                          {cell.column.id === 'firstName' || cell.column.id === 'lastName' ? (
                            <span onClick={() => handlePlayerClick(row.original)}>
                              {cell.render("Cell")}
                            </span>
                          ) : (
                            cell.render("Cell")
                          )}
                        </td>
                      ))}
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </div>
        </div>
        <h2>Defensive Data</h2>
        <div className='tableWrapper'>
          <div className='defensiveTable'>

            <table {...defensiveTable.getTableProps()}>
              <thead>
                {defensiveTable.headerGroups.map((headerGroup) => (
                  <tr {...headerGroup.getHeaderGroupProps()}>
                    {headerGroup.headers.map((column) => (
                      <th {...column.getHeaderProps()}>
                        {column.render("Header")}
                      </th>
                    ))}
                  </tr>
                ))}
              </thead>
              <tbody {...defensiveTable.getTableBodyProps()}>
                {defensiveTable.rows.map((row) => {
                  defensiveTable.prepareRow(row)
                  return (
                    <tr {...row.getRowProps()}>
                      {row.cells.map((cell) => (
                        <td key={cell.column.id} {...cell.getCellProps()}>
                          {cell.column.id === 'firstName' || cell.column.id === 'lastName' ? (
                            <span onClick={() => handlePlayerClick(row.original)}>
                              {cell.render("Cell")}
                            </span>
                          ) : (
                            cell.render("Cell")
                          )}
                        </td>
                      ))}
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </div>
        </div>
        <h2>Pitcher Data</h2>
        <div className='tableWrapper'>
          <div className='pitcherTable'>
            <table {...pitcherTable.getTableProps()}>
              <thead>
                {pitcherTable.headerGroups.map((headerGroup) => (
                  <tr {...headerGroup.getHeaderGroupProps()}>
                    {headerGroup.headers.map((column) => (
                      <th {...column.getHeaderProps()}>
                        {column.render("Header")}
                      </th>
                    ))}
                  </tr>
                ))}
              </thead>
              <tbody {...pitcherTable.getTableBodyProps()}>
                {pitcherTable.rows.map((row) => {
                  pitcherTable.prepareRow(row)
                  return (
                    <tr {...row.getRowProps()}>
                      {row.cells.map((cell) => (
                        <td key={cell.column.id} {...cell.getCellProps()}>
                          {cell.column.id === 'firstName' || cell.column.id === 'lastName' ? (
                            <span onClick={() => handlePlayerClick(row.original)}>
                              {cell.render("Cell")}
                            </span>
                          ) : (
                            cell.render("Cell")
                          )}
                        </td>
                      ))}
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
      {showModal && 
        <PlayerStatsModal show={showModal} onClose={() => setShowModal(false)} />
      }  
    </div> 
  )
}

export default PlayerStats;