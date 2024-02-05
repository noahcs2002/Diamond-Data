import React, { useEffect } from 'react'
import { Link, useLocation, useHistory, useNavigate } from "react-router-dom";
import '../styles/Home.scss';
import Navbar from '../components/Navbar';

function Home() {

    // Use location to get where we are
    const location = useLocation();
    let data = {};

    useEffect(() => {
      // if location has a state, we have come here from login screen
      // store the session information in the cache (localStorage)
      if (location.state) {
        data = location.state.data;
        localStorage.setItem('data', JSON.stringify(location.state.data));
      }
      // If we don't have a state, we are here from a different screen, load it from the cache
      else {
        data = JSON.parse(localStorage.getItem('data'));
      }

      // Do whatever with it
      console.log(data);

    }, [])

    return (
      <div>
        <Navbar/>
        <div className="home" style={{ backgroundImage: 'url(${})'}}>
          <div className="headerContainer">
            <h1>Header</h1>
            <p>Paragraph</p>
            <Link to="/UserManagement">
              <button>Button</button>
            </Link>
          </div>
        </div>
      </div>
    )
}

export default Home