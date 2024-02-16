import React from 'react'
import '../styles/BulkEntry.scss'
import Navbar from '../components/Navbar'

function BulkEntry() {
  return (
    <div>
      <Navbar/>
      <div className="bulkEntry">
        <h1 className="title">Bulk Entry</h1>
      </div>
    </div> 
  )
}

export default BulkEntry