import React from 'react';
import '../styles/ConfirmModal.scss'; 

const ConfirmModal = ({ isOpen, onClose, onConfirm, message }) => {
  if (!isOpen) return null;

  return (
    <div className="confirmModal">
      <div className="confirmModalContent">
        <p className='confirmDeleteText'>{message}</p>
        <button onClick={onConfirm}>Yes</button>
        <button onClick={onClose}>No</button>
      </div>
    </div>
  );
};

export default ConfirmModal;