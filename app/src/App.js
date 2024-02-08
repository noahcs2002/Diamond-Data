import React, { useState } from "react";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import PlayerManagement from "./pages/PlayerManagement";
import {BrowserRouter as Router, Route, Routes, Redirect} from 'react-router-dom';
import './App.scss';
import TeamStats from "./pages/TeamStats";
import UserManagement from "./pages/UserManagement";
import Settings from "./pages/Settings";
import Login from "./pages/Login";
import CreateAccount from "./pages/CreateAccount";
import { Switch } from "@mui/material";

function App() {

  const [isLoggedIn, setLoggedIn] = useState(false);
  const [isSignUpScreenActive, setSignUpScreenActive] = useState(false);

  return (
    <>
        <div className="App">
          <Router>
            {/* <Navbar /> */}
              <Routes>
                <Route  path="/" exact element={<Login/>} />
                <Route  path="/home" exact element={<Home/>} />
                <Route  path="/playerManagement" exact element={<PlayerManagement />} />
                <Route  path="/teamStats" exact element={<TeamStats />} />
                <Route  path="/userManagement" exact element={<UserManagement />} />
                <Route  path="/Settings" exact element={<Settings />} />
              </Routes>
            <Footer />
          </Router>
        </div>
    </>
  );
}

export default App;
