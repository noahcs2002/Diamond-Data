import React, { useEffect, useState } from "react";
import Navbar from '../components/Navbar';
import { ToastContainer } from "react-toastify";
import SavingScreen from "../components/SavingScreen";
import LoadingScreen from "../components/LoadingScreen";
import '../styles/PlayerManagement.scss';
import PlayerDropdownSelector from "../helpers/PlayerDropdownSelector";
import { UNSAFE_FetchersContext } from "react-router-dom";
import { Button } from "@mui/material";

const QuickEntry = () => {

    const [loading, setLoading] = useState(false);
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

    let user = {};
    let team = {};

    const [pitcherFormData, setPitcherFormData] = useState({
        hits: 0,
        walks: 0,
        homeruns: 0, 
        inningsPitched: 0,
        decision: 'ND',
        pitchCount: 0,
        walks: 0,
        hits: 0,
        strikeouts: 0,
        homeruns: 0
    })



    useEffect(() => {
        prop();
    }, [])

    const prop = async () => {
        user = await JSON.parse(localStorage.getItem('sessionData'));
        team = await fetchTeam(user);
        const players = await fetchPlayers(user, team);
        setPlayers(players);
        console.log(players);
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

    const handleSelectPlayer = (player) => {
        setSelectedPlayer(player);
        console.log("Selected player: ", player);
    }

    const validate = (e) => {
        const inputValue = e.target.value;
        if (/^-?\d*\.?\d*$/.test(inputValue) && inputValue >= 0) {
          return inputValue; 
        }
        return 0;
    };

    const handleHomerunChange = (e) => {
        setHomeruns(validate(e));
    }

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

    const recordPitcherGame = async () => {
        console.log('Pitcher game recorded')

    }

    const recordAtBat = async () => {
        console.log('At bat recorded')
    }

    const handlePitcherChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value,
        });
      };


    return (
        <div>
            <ToastContainer/>
            <Navbar />
            {loading ? <LoadingScreen/> : <>
                <PlayerDropdownSelector options={players} onSelect={handleSelectPlayer} message={"Select a player"}/> 
                    
                <div className="modalContent">
                    <form onSubmit={recordAtBat}>
                        <label htmlFor='strikeout'>SO</label>
                        <input name='result' id='strikeout' type='radio'></input>

                        <label htmlFor="ground-out">GO</label>
                        <input name='result' id='ground-out' type='radio'></input>

                        <label htmlFor="single">1B</label>
                        <input name='result' id='single' type='radio'></input>

                        <label htmlFor="double">2B</label>
                        <input name='result' id='double' type='radio'></input>

                        <label htmlFor="triple">3B</label>
                        <input name='result' id='triple' type='radio'></input>

                        <label htmlFor="homerun">HR</label>
                        <input name='result' id='homerun' type='radio'></input>

                        <label htmlFor="hit-by-pitch">HBP</label>
                        <input name='result' id='hit-by-pitch' type='radio'></input>

                        <label htmlFor='walk'>BB</label>
                        <input name='result' id='walk' type='radio'></input>

                        <button type="submit">Record AB</button>
                    </form>
                </div>

                <div>
                    <form onSubmit={recordPitcherGame}>
                        <label htmlFor="win">W</label>
                        <input name='pitching-res' type="radio" id='win'></input> 
                        
                        <label htmlFor="loss">L</label>
                        <input name='pitching-res' type="radio" id='loss'></input> 

                        <label htmlFor="no-decision">No Decision</label>
                        <input name='pitching-res' type="radio" id='no-decision'></input> 

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

                        <label htmlFor="homeruns">Homeruns</label>
                        <input name="pitching-res" type='number' id='homeruns' onChange={handleHomerunChange} value={homeruns}></input>

                        <button type='submit'>Record Game</button> 
                    </form>
                </div>
            </>}
        </div>
    )
}

export default QuickEntry;