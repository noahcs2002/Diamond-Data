import React, { useEffect, useState } from 'react';
import '../styles/TeamStats.scss';
import Navbar from '../components/Navbar';
import { useTable } from 'react-table';

function TeamStats() {

  const [offensiveData, setOffensiveData] = useState({});
  const [defensiveData, setDefensiveData] = useState({});
  const [pitcherData, setPitcherData] = useState({});

  useEffect(() => {
    getOffensiveData()
    getDefensiveData()
    getPitcherData()
  }, [])

  const getOffensiveData = async () => {
    const id = 'E9123497-4899-49EB-A26B-1D09B81B0693';
    const endpointUrl = 'http://localhost:8080/diamond-data/api/offensive-players/get-by-team';
    
    const url = new URL(endpointUrl);
    url.searchParams.append('teamId', id);

    await fetch(url,{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(res => {
      if(!res.ok) {
        console.log('error');
      }
      return res.json();
    })
    .then(data => {
      if(data) {
        setOffensiveData(data);
        console.log(data);
      }
    })
  }

  const getDefensiveData = async () => {
    const id = 'E9123497-4899-49EB-A26B-1D09B81B0693';
    const endpointUrl = 'http://localhost:8080/diamond-data/api/defensive-players/by-team';
    
    const url = new URL(endpointUrl);
    url.searchParams.append('teamId', id);

    await fetch(url,{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(res => {
      if(!res.ok) {
        console.log('error');
      }
      return res.json();
    })
    .then(data => {
      if(data) {
        setDefensiveData(data);
        console.log(data);
      }
    })
  }

  const getPitcherData = async () => {
    const id = 'E9123497-4899-49EB-A26B-1D09B81B0693';
    const endpointUrl = 'http://localhost:8080/diamond-data/api/pitchers/by-team';
    
    const url = new URL(endpointUrl);
    url.searchParams.append('teamId', id);

    await fetch(url,{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(res => {
      if(!res.ok) {
        console.log('error');
      }
      return res.json();
    })
    .then(data => {
      if(data) {
        setPitcherData(data);
        console.log(data);
      }
    })
  }

  // console.log(offensiveData)
  // const data = React

  return (
    <div>
      <Navbar/>
      <div className="teamStats">
        <h1> Team Stats</h1>
        <button onClick={getOffensiveData}>Offensive</button>
        <button onClick={getDefensiveData}>Defensive</button>
        <button onClick={getPitcherData}>Pitchers</button>
        
      </div>  
    </div> 
  )
}

export default TeamStats