import React from "react";
import { useState } from "react";
import '../styles/DropdownSelector.scss';

const PlayerDropdownSelector = ({options, onSelect, message}) => {

    const [selectedOption, setSelectedOption] = useState(null);
  
    const handleSelect = (option) => {
        setSelectedOption(option);
        onSelect(option);
    };

    return (
        <div className="dropdown-selector">
        <select
            value={selectedOption}
            onChange={(e) => handleSelect(e.target.value)}
        >
            {options.length !== 0 ? <>
                <option value="" disabled>{message}</option>
            {options.map((option, index) => (
            <option key={index} value={option.id}>{option.firstName + ' ' + option.lastName}</option>
            ))}</> : 
            <><option key="0" value={0} disabled={true}>No players available</option></>}
            
        </select>
        </div>
    );
}

export default PlayerDropdownSelector;