import React from 'react'

function PlayerItem({name, stat1, stat2}) {
  return (
    <div className="playerItem">
        <h1> {name} </h1>
        <p> {stat1} </p>
        <p> {stat2} </p>
        </div>
  )
}

export default PlayerItem