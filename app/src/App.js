import React, { useState } from "react";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import PlayerManagement from "./pages/PlayerManagement";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import TeamStats from "./pages/TeamStats";
import UserManagement from "./pages/UserManagement";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route  path="/" exact element={<Home />} />
          <Route  path="/playerManagement" exact element={<PlayerManagement />} />
          <Route  path="/teamStats" exact element={<TeamStats />} />
          <Route  path="/userManagement" exact element={<UserManagement />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
