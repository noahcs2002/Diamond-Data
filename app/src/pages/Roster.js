import React from 'react'
import '../styles/Roster.scss'
import Navbar from '../components/Navbar'

function Roster() {
  return (
    <div>
      <Navbar/>
      <div className="roster">
        <h1 className="title">Roster</h1>
      </div>
    </div> 
  )
}

export default Roster