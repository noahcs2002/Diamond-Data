import React, { useState, useEffect } from 'react';
import '../styles/PlayerItem.scss';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

function PlayerItem({ id, fullName, onDelete, onEdit, positions, player }) {
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [showEditDialog, setShowEditDialog] = useState(false);
  const [editedName, setEditedName] = useState(fullName);

  useEffect(() => {
  }, [fullName]); 

  const handleDeleteClick = () => {
    setShowConfirmation(true);
  };

  const handleConfirmDelete = () => {
    onDelete(id);
    setShowConfirmation(false);
  };

  const handleCancelDelete = () => {
    setShowConfirmation(false);
  };

  const handleEditClick = () => {
    onEdit(player)
    // setShowEditDialog(true);
    // setEditedName(fullName);
  };

  const handleEditConfirm = () => {
    localStorage.setItem('updatedName', editedName);
    onEdit(id, editedName); 
    setShowEditDialog(false);
    return editedName;
  };

  const handleInputChange = (e) => {
    setEditedName(e.target.value);
  };

  const handleEditCancel = () => {
    setShowEditDialog(false);
  };

  return (
    <div className="playerItem">
      <h3> {fullName} </h3>
      <h4> {positions && positions.map(p => p + " ")}</h4>

      <div className="playerIcons">
        <EditIcon onClick={handleEditClick} className="editIcon" />
        <DeleteIcon onClick={handleDeleteClick} className="deleteIcon" />
      </div>

      {showConfirmation && (
        <div className="confirmationDialog">
          <p>Are you sure you want to delete {fullName}?</p>
          <button className="yesButton" onClick={handleConfirmDelete}>
            Yes
          </button>
          <button className="noButton" onClick={handleCancelDelete}>
            No
          </button>
        </div>
      )}

      {showEditDialog && (
        <div className="editDialog">
          <input
            type="text"
            value={editedName}
            onChange={handleInputChange}
          />
          <button onClick={handleEditConfirm}>Save</button>
          <button onClick={handleEditCancel}>Cancel</button>
        </div>
      )}
    </div>
  );
}

export default PlayerItem;
