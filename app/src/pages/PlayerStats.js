import React, { useEffect, useState } from 'react';
import '../styles/PlayerStats.scss';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { useTable, useSortBy } from 'react-table';
import PlayerStatsModal from '../components/PlayerStatsModal';
import LoadingScreen from '../components/LoadingScreen';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function PlayerStats() {

  const [offensiveData, setOffensiveData] = useState([]);
  const [defensiveData, setDefensiveData] = useState([]);
  const [pitcherData, setPitcherData] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(true);
  const nav = useNavigate();


  useEffect(() => {
    prop();
  }, [showModal]);

  const prop = async () => {
    const user = JSON.parse(localStorage.getItem('sessionData'));

    if(user === undefined || user === null) {
      nav('/')
      return;
    }

    let team = {};
    let players = {};
    let pitchers = {};

    try {
     team = await JSON.parse(localStorage.getItem('cachedTeam'))

     if(team === undefined || team === null) {
      throw new Error();
     }
    }
    catch {
      team = await fetchTeam(user);
    }

    try {
      players = await JSON.parse(localStorage.getItem('cachedPlayers'));

      if (players === undefined || players === null) {
        throw new Error();
      }
    }
    catch {
      players = await fetchPlayers(user, team);
    }

    try {
      pitchers = await JSON.parse(localStorage.getItem('cachedPitchers'));

      if (pitchers === undefined || pitchers === null) {
        throw new Error();
      }
    }
    catch {
      pitchers = await fetchPitcherData(team, user);
    }

    await propogate(players);
    setPitcherData(pitchers);
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
      localStorage.setItem('cachedTeam', data);
      return data
    }
    catch(_e) {
      toast.error('Error fetching team, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
    }
  }

  const fetchPlayers = async (user, team) => {
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
      
      localStorage.setItem('cachedPlayers', JSON.stringify(players));
      return players;
    }
    catch(_e) {
      toast.error('Error fetching players, please try again', {
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
      localStorage.setItem('cachedPitchers', JSON.stringify(data));
      return data;
    }
    catch(_e) {
      toast.error('Error fetching notes, please try again', {
        position:'bottom-right',
        autoClose: 1500,
        hideProgressBar:true,
        closeOnClick:true
      })
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
      Header: "WINS",
      accessor: "wins",
    },
    {
      Header: "LOSSES",
      accessor: "losses",
    },
    {
      Header: "ERA",
      accessor: "earnedRunAverage",
      Cell: ({value}) => parseFloat(value).toFixed(2)
    },
    {
      Header: "APPEARANCES",
      accessor: "appearances",
    },
    {
      Header: "GAMES STARTED",
      accessor: "gamesStarted",
    },
    {
      Header: "GAMES FINISHED",
      accessor: "gamesFinished",
    },
    {
      Header: "SHUTOUTS",
      accessor: "shutouts",
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
      Header: "INNINGS PITCHED",
      accessor: "inningsPitched",
    },
    {
      Header: "HITS",
      accessor: "hits",
    },
    {
      Header: "UNEARNED RUNS",
      accessor: "unearnedRuns",
    },
    {
      Header: "EARNED RUNS",
      accessor: "earnedRuns",
    },
    {
      Header: "STRIKEOUTS",
      accessor: "strikeouts",
    },
    {
      Header: "WALKS",
      accessor: "walks",
    },
    {
      Header: "NUMBER OF PITCHES",
      accessor: "numberOfPitches",
    },
    {
      Header: "WHIP",
      accessor: "walksAndHitsPerInningPitched",
      Cell: ({value}) => parseFloat(value).toFixed(2)
    },
    {
      Header: "WILD PITCHES",
      accessor: "wildPitches",
    },
    {
      Header: "BATTERS FACED",
      accessor: "battersFaced",
    },
    {
      Header: "FLYOUTS",
      accessor: "flyouts",
    },
    {
      Header: "GROUNDOUTS",
      accessor: "groundouts",
    },
    {
      Header: "BLOWN SAVES",
      accessor: "blownSaves",
    },
    {
      Header: "INHERITED RUNNERS",
      accessor: "inheritedRunners",
    },
    {
      Header: "HOLDS",
      accessor: "holds",
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
      Header: "BALKS",
      accessor: "balks",
    },
  ], []);
  
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
      Header: "DOUBLE PLAY",
      accessor: "doublePlay",
    },
    {
      Header: "ERRORS",
      accessor: "errors",
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
      Header: 'AVG',
      accessor: 'battingAverage',
      Cell: ({value}) => parseFloat(value).toFixed(3).substring(1) 
    },
    {
      Header: "CAUGHT STEALING",
      accessor: "caughtStealing",
    },
    {
      Header: "CAUGHT STEALING PERCENTAGE",
      accessor: "caughtStealingPercentage",
      Cell: ({value}) => parseFloat(value).toFixed(3).substring(1) 
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
      Cell: ({value}) => parseFloat(value).toFixed(3).substring(1) 
    },
    {
      Header: "ON BASE PLUS SLUGGING",
      accessor: "onBasePlusSlugging",
      Cell: ({value}) => parseFloat(value).toFixed(3).substring(1) 
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
      Header: "STRIKEOUTS",
      accessor: "strikeouts",
    },
    {
      Header: "SLUGGING PERCENTAGE",
      accessor: "sluggingPercentage",
      Cell: ({value}) => parseFloat(value).toFixed(3).substring(1) 
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
  

  const offensiveTable = useTable({ columns: offensiveColumns, data: offensiveData }, useSortBy);
  const defensiveTable = useTable({ columns: defensiveColumns, data: defensiveData }, useSortBy);
  const pitcherTable = useTable({ columns: pitcherColumns, data: pitcherData }, useSortBy);

  return (
    <div>
      <ToastContainer/>
      {loading ? <LoadingScreen/> : <>
    <Navbar/>
    <div className="playerStats">
      <h1 className='title'> Player Stats</h1>
      <h2 className='headers'>Offensive Data</h2>
      <div className='tableWrapper'>
        <div className='offensiveTable'>
          <table {...offensiveTable.getTableProps()}>
            <thead>
              {offensiveTable.headerGroups.map((headerGroup) => (
                <tr {...headerGroup.getHeaderGroupProps()}>
                  {headerGroup.headers.map((column) => (
                    <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                      {column.render("Header")}
                      <span>
                        {column.isSorted ? (column.isSortedDesc ? '↓' : '↑') : ''}
                      </span>
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
                    <th {...column.getHeaderProps(column.getSortByToggleProps(column.getSortByToggleProps()))}>
                      {column.render("Header")}
                      <span>
                        {column.isSorted ? (column.isSortedDesc ? '↓' : '↑') : ''}
                      </span>
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
                    <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                      {column.render("Header")}
                      <span>
                        {column.isSorted ? (column.isSortedDesc ? '↓' : '↑') : ''}
                      </span>
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