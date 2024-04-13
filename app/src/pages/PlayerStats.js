import React, { useEffect, useState } from 'react';
import '../styles/PlayerStats.scss';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { useTable } from 'react-table';
import PlayerStatsModal from '../components/PlayerStatsModal';
import LoadingScreen from '../components/LoadingScreen';

function PlayerStats() {

  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [teams, setTeams] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(true);


  useEffect(() => {
    fetchAll();
  }, [showModal]);

  const fetchAll = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));
    const teams = await fetchTeams(user);
    await fetchOffensive(selectedTeam || teams[0], user);
    await fetchDefensive(selectedTeam || teams[0], user);
    await fetchPitcher(selectedTeam || teams[0], user);
    setLoading(false);
  }

  const fetchTeams = async (user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-all'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);

    try {
      const res = await fetch(url);

      if(!res.ok) {
        alert('Unable to fetch teams')
      }
      const teams = await res.json();
      setTeams(teams);
      localStorage.setItem('teams', teams);
      localStorage.setItem('selectedTeam', teams[0])
      setSelectedTeam(teams[0]);
      return teams;
    }
    catch(_e) {
      alert("Failure getting teams: ", _e);
    }
  };

  const fetchOffensive = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/offensive-players/get-by-team'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);
      if (!res.ok) {
        alert('Error getting offensive player data, status was not OK')
      }
      const data = await res.json();
      setOffensiveData(data);
    }
    catch(_e) {
      alert('Error getting offensive player data: ', _e);
    }
  }

  const fetchDefensive = async (team, user) => {
    const endpoint = 'http://localhost:8080/diamond-data/api/defensive-players/get-by-team'
    const url = new URL(endpoint);
    url.searchParams.append('userId', user.id);
    url.searchParams.append('teamId', team.id);

    try {
      const res = await fetch(url);
      if (!res.ok) {
        alert('Error getting defensive player data, status was not OK')
      }
      const data = await res.json();
      setDefensiveData(data);
    }
    catch(_e) {
      alert('Error getting defensive player data: ', _e);
    }

  }

  const fetchPitcher = async (team, user) => {
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
    }
    catch(_e) {
      alert('Error getting pitcher player data: ', _e);
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
      Header: "HITS",
      accessor: "hits",
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
      Header: "WALKS",
      accessor: "walks",
    },
    {
      Header: "WILD PITCHES",
      accessor: "wildPitches",
    },
    {
      Header: "WINS",
      accessor: "wins",
    },
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
      Header: "STOLEN BASES ALLOWED",
      accessor: "stolenBasesAllowed",
    },
    {
      Header: "STOLEN BASE ATTEMPTS",
      accessor: "atolenBaseAttempts",
    },
    {
      Header: "TOTAL CHANCES",
      accessor: "totalChances",
    },
    {
      Header: "TRIPLE PLAYS",
      accessor: "triplePlays",
    },
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
      Header: "CAUGHT STEALING",
      accessor: "caughtStealing",
    },
    {
      Header: "CAUGHT STEALIN PERCENTAGE",
      accessor: "caughtStealingPercentage",
    },
    {
      Header: "DOUBLES",
      accessor: "battersFaces",
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
      Header: "PLATE APPEARANCE",
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
  ], []);

  const offensiveTable = useTable({ columns: offensiveColumns, data: offensiveData });
  const defensiveTable = useTable({ columns: defensiveColumns, data: defensiveData });
  const pitcherTable = useTable({ columns: pitcherColumns, data: pitcherData });

  const teamOptions = teams.map(team => (
    <option key={team.id} value={team.id}>{team.name}</option>
  ));


  return (
    <div>
      {loading ? <LoadingScreen/> : <>
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
      <h2 className='headers'>Offensive Data</h2>
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
                        {cell.render("Cell")}
                      </td>
                    ))}
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
      <h2 className='headers'>Defensive Data</h2>
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
                        {cell.render("Cell")}
                      </td>
                    ))}
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
      <h2 className='headers'>Pitcher Data</h2>
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
                        {cell.render("Cell")}
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
    }  </>}
    <Footer />
  </div> 
)
}

export default PlayerStats;