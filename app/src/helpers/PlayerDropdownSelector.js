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
            <option value="" disabled>{message}</option>
            {options.map((option, index) => (
            <option key={index} value={option.id}>{option.firstName + ' ' + option.lastName}</option>
            ))}
        </select>
        </div>
    );
}

export default PlayerDropdownSelector;