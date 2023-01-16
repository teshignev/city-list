import React, { useEffect, useState } from 'react';
import SearchBar from './SearchBar';
import CityList from './CityList';
import { Button } from 'reactstrap';

const SearchPage = () => {
  const [ input, setInput ] = useState('');
  const [ cityListPage, setCityListPage ] = useState([]);

  useEffect( () => { fetchData() },[] );

  const fetchData = async () => {
    return await fetch('/city-list')
    .then(response => response.json())
    .then(data => {
      setCityListPage(data);
    });}

  const updateInput = async (input) => {
    setInput(input);

    return await fetch(`/filtered-city-list?cityName=${ input }`)
    .then(response => response.json())
    .then(data => {
      setCityListPage(data);
    });
  }

  const switchPage = async (nextPage) => {
    let url = chooseUrl()

    return await fetch(`${ url }pageNumber=${ nextPage }`)
    .then(response => response.json())
    .then(data => {
      setCityListPage(data);
    });
  }

  const chooseUrl = () => {
    if (input) {
      return `/filtered-city-list?cityName=${ input }&`;
    }
    return '/city-list?';
  }

  return (
      <div className='App'>
        <header className='App-header'>
          <h1>City List</h1>
        </header>
        <div className='SubHeader'>
          <SearchBar input={ input } onChange={ updateInput }/>

          <Button className='SwitchPage' color='primary'
                  onClick={ () => switchPage(cityListPage.number - 1) }
                  disabled={ cityListPage.first === true }>Previous</Button>

          <Button color='primary'
                  onClick={ () => switchPage(cityListPage.number + 1) }
                  disabled={ cityListPage.last === true }>Next</Button>

          <label className='PageNumber'>{ cityListPage.number + 1 } / { cityListPage.totalPages }</label>
        </div>
        <CityList cityList = { cityListPage.content }/>
      </div>
  );
}

export default SearchPage;