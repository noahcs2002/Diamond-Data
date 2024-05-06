import React, { useEffect, useState } from "react";
import Navbar from '../components/Navbar';
import LoadingScreen from "../components/LoadingScreen";
import '../styles/QuickEntry.scss';
import PlayerDropdownSelector from "../helpers/PlayerDropdownSelector";
import { ToastContainer, toast } from "react-toastify";
import { editableInputTypes } from "@testing-library/user-event/dist/utils";

const QuickEntry = () => {

    const [loading, setLoading] = useState(true);
    const [players, setPlayers] = useState([]);
    const [pitchers, setPitchers] = useState([]);
    const [selectedPlayer, setSelectedPlayer] = useState({});
    const [selectedPitcher, setSelectedPitcher] = useState({});
    const [hits, setHits] = useState(0);
    const [walks, setWalks] = useState(0)
    const [homeruns, setHomeruns] = useState(0)
    const [pitchCount, setPitchCount] = useState(0);
    const [strikeouts, setStrikeouts] = useState(0)
    const [inningsPitched, setInningsPitched] = useState(0);
    const [pitcherDecision, setPitcherDecision] = useState('W')
    const [atBatResult, setAtBatResult] = useState('SO')

    const user = JSON.parse(localStorage.getItem('sessionData'));
    let team = {};

    useEffect(() => {
        prop();
    }, [])

    const prop = async () => {
        team = await fetchTeam(user);
        const players = await fetchPlayers(user, team);
        const pitchers = await fetchPitchers(user, team);
        setPlayers(players);
        setPitchers(pitchers);
        setSelectedPlayer(players[0])
        setSelectedPitcher(pitchers[0])
        console.log(players);
        setLoading(false);
    }

    const fetchTeam = async (user) => {

        try {
            team = await JSON.parse(localStorage.getItem('cachedTeam'));
            if (team === undefined || team === null) {
                throw new Error();
            }
            return team;
        }
        catch {
            const endpoint = 'http://localhost:8080/diamond-data/api/teams/get-by-user';
            const url = new URL(endpoint);
            url.searchParams.append('userId', user.id);
            const res = await fetch(url);
            const t = await res.json();
            localStorage.setItem('cachedTeam', JSON.stringify(t));
            return team;
        }
    }

    const fetchPlayers = async (user, team) => {
        let p = [];
        try {
            p = await JSON.parse(localStorage.getItem('cachedPlayers'));
            if (p === undefined || p === null) {
                throw new Error();
            }
            return p;
        }
        catch {
            const endpoint = 'http://localhost:8080/diamond-data/api/players/get-by-team';
            const url = new URL(endpoint);
            url.searchParams.append('userId', user.id);
            url.searchParams.append('teamId', team.id);
            const res = await fetch(url);
            const resJson = await res.json();
            localStorage.setItem('cachedPlayers', JSON.stringify(resJson))
            return resJson;
        }
    }

    const fetchPitchers = async (user, team) => {
        let p = [];
        try {
            p = await JSON.parse(localStorage.getItem('cachedPitchers'));
            if (p === undefined || p === null) {
                throw new Error();
            }
            return p;
        }

        catch {
            const endpoint = 'http://localhost:8080/diamond-data/api/pitchers/get-by-team';
            const url = new URL(endpoint);
            url.searchParams.append('userId', user.id);
            url.searchParams.append('teamId', team.id);

            try {
                const res = await fetch(url);
                const pitcherRes = await res.json();
                setPitchers(pitcherRes);
                localStorage.setItem('cachedPitchers', JSON.stringify(pitcherRes));
                return pitcherRes;
            }
            catch {
                toast.error('Error fetching players, please try again', {
                    position: 'bottom-right',
                    autoClose: 2500,
                    hideProgressBar: true,
                    closeOnClick: true 
                })
                return [];
            }
        }
    }

    const handleSelectPlayer = (player) => {
        setSelectedPlayer(player);
    }

    const handleSelectPitcher = (pitcher) => {
        setSelectedPitcher(pitcher);
    }

    const validate = (e) => {
        const inputValue = e.target.value;
        if (/^-?\d*\.?\d*$/.test(inputValue) && inputValue >= 0) {
          return inputValue; 
        }
        return 0;
    };

    const handleHitsChange = (e) => {
        setHits(validate(e))
    }

    const handlePitchCountChange = (e) => {
        setPitchCount(validate(e))
    }

    const handleStrikeoutChange = (e) => {
        setStrikeouts(validate(e));
    }

    const handleWalkChange = (e) => {
        setWalks(validate(e));
    }

    const handleInningsPitchedChanged = (e) => {
        setInningsPitched(validate(e));
    }

    const recordPitcherGame = async (e) => {
        e.preventDefault();
        
        const gameUpdate = {
            decision: pitcherDecision,
            pitchCount: pitchCount,
            inningsPitched: inningsPitched,
            walks: walks,
            strikeouts: strikeouts,
            hits: hits,
        }

        const endpoint = 'http://localhost:8080/diamond-data/api/rosters/record-game-pitched'
        const url = new URL(endpoint);
        url.searchParams.append('userId', user.id);


    }

    const recordAtBat = async (e) => {
        e.preventDefault();

        const update = {
            result: atBatResult
        }

        const endpoint = 'http://localhost:8080/diamond-data/api/rosters/record-at-bat';
        const url = new URL(endpoint);
        url.searchParams.append('userId', user.id);
        url.searchParams.append('playerId', selectedPlayer);
        console.log('userId: ', user.id)

        try {
            const res = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify(update)
            })

            const resJson = await res.json();
            console.log(resJson);
        }
        catch (e){
            console.log(e);
            toast.error('Error recording AB, please try again', {
                position: 'bottom-right',
                autoClose: 2500,
                hideProgressBar: true,
                closeOnClick: true
            })
        }

    }

    return (
        <div>
            <ToastContainer/>
            <Navbar />
            {loading ? <LoadingScreen/> : <>
                <PlayerDropdownSelector options={players} onSelect={handleSelectPlayer} message={"Select a player"}/> 
                    
                <div className="modalContent">
                    <form onSubmit={recordAtBat}>
                        <input name='result' id='strikeout' type='radio' onChange={() => setAtBatResult('SO')}></input>
                        <label htmlFor='strikeout'>SO</label>

                        <input name='result' id='bunt' type='radio' onChange={() => setAtBatResult('BU')}></input>
                        <label htmlFor="bunt">Bunt</label>

                        <input name='result' id='sac-fly' type='radio' onChange={() => setAtBatResult('SF')}></input>
                        <label htmlFor="sac-fly">FO</label>

                        <input name='result' id='single' type='radio' onChange={() => setAtBatResult('1B')}></input>
                        <label htmlFor="single">1B</label>

                        <input name='result' id='double' type='radio' onChange={() => setAtBatResult('2B')}></input>
                        <label htmlFor="double">2B</label>

                        <input name='result' id='triple' type='radio' onChange={() => setAtBatResult('3B')}></input>
                        <label htmlFor="triple">3B</label>

                        <input name='result' id='homerun' type='radio' onChange={() => setAtBatResult('HR')}></input>
                        <label htmlFor="homerun">HR</label>

                        <input name='result' id='hit-by-pitch' type='radio' onChange={() => setAtBatResult('HBP')}></input>
                        <label htmlFor="hit-by-pitch">HBP</label>

                        <input name='result' id='walk' type='radio' onChange={() => setAtBatResult('BB')}></input>
                        <label htmlFor='walk'>BB</label>

                        <button type="submit">Record AB</button>
                    </form>
                </div>

                <div className="modalContent">
                    <PlayerDropdownSelector options={pitchers} onSelect={handleSelectPitcher} message={"Select a pitcher"}/>
                    <form onSubmit={recordPitcherGame}>
                        <label htmlFor="win">W</label>
                        <input name='pitching-res-buttons' type="radio" id='win' onChange={() => {setPitcherDecision('W')}} checked={pitcherDecision==='W'}></input> 
                        
                        <label htmlFor="loss">L</label>
                        <input name='pitching-res-buttons' type="radio" id='loss' onChange={() => {setPitcherDecision('L')}}></input> 

                        <label htmlFor="no-decision">No Decision</label>
                        <input name='pitching-res-buttons' type="radio" id='no-decision' onChange={() => {setPitcherDecision('ND')}}></input> 

                        <label htmlFor="inningsPitched">inningsPitched</label>
                        <input name='pitching-res' type='number' id='inningsPitched' onChange={handleInningsPitchedChanged} value={inningsPitched} step={.1}></input>

                        <label htmlFor="pitchCount">Pitch Count</label>
                        <input name='pitching-res' type='number' id='pitch-count' onChange={handlePitchCountChange} value={pitchCount}></input>

                        <label htmlFor="walks">Walks</label>
                        <input name="pitching-res" type='number' id='walks' onChange={handleWalkChange} value={walks}></input>

                        <label htmlFor="strikeouts">Strikeouts</label>
                        <input name='pitching-res' type='number' id='pitch-count' onChange={handleStrikeoutChange} value={strikeouts}></input>

                        <label htmlFor="hits">Hits</label>
                        <input name='pitching-res' type='number' id='hits' onChange={handleHitsChange} value={hits}></input>

                        <button type='submit'>Record Game</button> 
                    </form>
                </div>
            </>}
        </div>
    )
}

export default QuickEntry;