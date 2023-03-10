import React from 'react';

const SearchBar = ({ input, onChange }) => {
  const BarStyling = { width:"20rem", background:"#cac9d5", border:"none", padding:"0.5rem" };
  return (
      <input className='SearchBar'
          style={ BarStyling }
          key="SearchBar"
          value={ input }
          placeholder={ "search country" }
          onChange={ (e) => onChange(e.target.value) }
      />
  );
}

export default SearchBar