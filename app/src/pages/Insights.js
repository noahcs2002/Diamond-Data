import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { Radar } from 'react-chartjs-2';
import '../styles/Insights.scss'; // Import the Insights styling

function Insights() {
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [playerData, setPlayerData] = useState([]);
    const [loading, setLoading] = useState(false);
    const [searchQuery, setSearchQuery] = useState('');
    const [suggestedPlayers, setSuggestedPlayers] = useState([]);
    const [showDropdown, setShowDropdown] = useState(false);
    const searchInputRef = useRef(null);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const [PlayersResponse, pitchersResponse] = await Promise.all([
                    axios.get('http://localhost:8080/diamond-data/api/players/get-all'),
                    axios.get('http://localhost:8080/diamond-data/api/pitchers/get-all')
                ]);
                
                const allPlayers = [
                    ...PlayersResponse.data,
                    ...pitchersResponse.data
                ];

                setPlayerData(allPlayers);
                setSuggestedPlayers(allPlayers);
            } catch (error) {
                console.error('Error fetching player data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    const handlePlayerSelect = (player) => {
        setSelectedPlayer(player);
        setSearchQuery('');
        setShowDropdown(false);
    };

    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        setSearchQuery(query);

        if (playerData.length > 0) {
            const filteredPlayers = playerData.filter(player =>
                player && player.name && player.name.toLowerCase().includes(query)
            );
            setSuggestedPlayers(filteredPlayers);
            setShowDropdown(true); 
        } else {
            setShowDropdown(false); 
        }
    };

    const handleFocus = () => {
        if (suggestedPlayers.length > 0) {
            setShowDropdown(true); 
        }
    };

    const handleBlur = () => {
        setShowDropdown(false); 
    };

    const RadarChart = ({ player }) => {
        if (!player) return null; 

        const data = {
            labels: ['Batting Avg', 'OPS', 'Steals', 'Home Runs', 'Putouts', 'Assists'],
            datasets: [{
                label: player.name,
                data: [
                    player.battingAvg,
                    player.ops,
                    player.steals,
                    player.homeRuns,
                    player.putouts,
                    player.assists
                ],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        };

        const options = {
            scale: {
                ticks: { beginAtZero: true }
            }
        };

        return (
            <div>
                <Radar data={data} options={options} />
            </div>
        );
    };

    return (
        <div>
            <Navbar />
            <div className="insights">
                <h1 className="title">Insights</h1>
                <div className="search-wrapper">
                    <input
                        type="text"
                        placeholder="Search for a player..."
                        value={searchQuery}
                        onChange={handleSearch}
                        onFocus={handleFocus}
                        onBlur={handleBlur}
                        ref={searchInputRef}
                    />
                    {showDropdown && (
                        <div className="dropdown">
                            {suggestedPlayers.map(player => (
                                <div
                                    key={player.id}
                                    onClick={() => handlePlayerSelect(player)}
                                    className="dropdown-item"
                                >
                                    {player.name}
                                </div>
                            ))}
                        </div>
                    )}
                </div>
                {loading && <p>Loading...</p>}
                {selectedPlayer && (
                    <div>
                        <h2>{selectedPlayer.name}</h2>
                        <RadarChart player={selectedPlayer} />
                    </div>
                )}
            </div>
        </div>
    );
}

export default Insights;
