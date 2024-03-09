import React, { useState, useEffect } from 'react';
import '../styles/PlayerItem.scss';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

function PlayerItem({ id, fullName, onDelete, onEdit }) {
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [showEditDialog, setShowEditDialog] = useState(false);
  const [editedName, setEditedName] = useState(fullName);

  useEffect(() => {
    console.log("PlayerItem rendered with fullName:", fullName);
  }, [fullName]); // Log when fullName prop changes

  const handleDeleteClick = () => {
    setShowConfirmation(true);
  };

  const handleConfirmDelete = () => {
    onDelete(fullName);
    setShowConfirmation(false);
  };

  const handleCancelDelete = () => {
    setShowConfirmation(false);
  };

  const handleEditClick = () => {
    setShowEditDialog(true);
    setEditedName(''); 
  };

  const handleEditConfirm = () => {
    console.log("Edited Name:", editedName);
    onEdit(fullName, editedName); 
    setShowEditDialog(false);
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
