import React from 'react'
import { Link } from "react-router-dom";
import '../styles/Home.scss';

function Home() {
  return (
    <div className="home" style={{ backgroundImage: 'url(${})'}}>
      <div className="headerContainer">
        <h1>Header</h1>
        <p>Paragraph</p>
        <Link to="/UserManagement">
          <button>Button</button>
        </Link>
      </div>
    </div>
  )
}

export default Home