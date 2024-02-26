import React from "react";
import PlayerStatsModal from "./PlayerStatsModal";

function PlayerStatsModalTest() {
  // Sample player data
  const player = {
    firstName: "John",
    lastName: "Doe",
    // Add more properties as needed
  };

  return (
    <div>
        <h1>Player Stats Modal Test</h1>
      {/* Render the PlayerStatsModal with sample player data */}
      <PlayerStatsModal player={player} onClose={() => console.log("Modal closed")} />
    </div>
  );
}

export default PlayerStatsModalTest;