import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { Radar } from 'react-chartjs-2';
import '../styles/Insights.scss'; // Import the Insights styling

function Insights() {
  return (  
    <div>
      <Navbar />
      <div className="insights">
        <h1 className="title">Insights</h1>
        <div className="content">
          <div className="left-column">
            <div className="column">
              <h2>AI-generated Roster Lineup</h2>
              <div className="scrollable-cards">
                {/* Dummy card components */}
                <div className="card">Player 1 Info</div>
                <div className="card">Player 2 Info</div>
                <div className="card">Player 3 Info</div>
                <div className="card">Player 4 Info</div>
                <div className="card">Player 5 Info</div>
                <div className="card">Player 6 Info</div>
                <div className="card">Player 7 Info</div>
                <div className="card">Player 8 Info</div>
                <div className="card">Player 9 Info</div>
              </div>
            </div>
            <div className="column">
              <h2>Team Records</h2>
              {/* Dummy team records data */}
              <p>Team records data goes here...</p>
            </div>
            <div className="column">
              <h2>Reports</h2>
              {/* Dummy reports data */}
              <p>Reports data goes here...</p>
            </div>
          </div>
          <div className="right-column">
            <div className="comparison">
              <h2>Player Comparison</h2>
              {/* Player comparison component */}
              <div className="comparison-cards">
                {/* First card */}
                <div className="card">Player Comparison Card 1 Info</div>
                {/* Second card */}
                <div className="card">Player Comparison Card 2 Info</div>
              </div>
            </div>
            <div className="stats-graph">
              <h2>Stats Graph</h2>
              {/* Graph components */}

              {/* Fake graph  */}
              <div class="fake-baseball-graph">
                <div class="graph-title">Baseball Stats</div>
                <div class="graph">
                  <div className="bar" style={{ height: '70%' }}></div>
                  <div className="bar" style={{ height: '60%' }}></div>
                  <div className="bar" style={{ height: '80%' }}></div>
                  <div className="bar" style={{ height: '50%' }}></div>
                  <div className="bar" style={{ height: '90%' }}></div>
                </div>
                <div class="graph-labels">
                  <span>Player 1</span>
                  <span>Player 2</span>
                  <span>Player 3</span>
                  <span>Player 4</span>
                  <span>Player 5</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Insights;
