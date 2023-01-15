import React, { useEffect, useState } from 'react';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';

const CityList = () => {
  const [ groups, setGroups ] = useState([]);
  const [ loading, setLoading ] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('/city-list')
    .then(response => response.json())
    .then(data => {
      setGroups(data);
      setLoading(false);
    })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  const cityList = () => {
    if (groups.content === undefined) {
      return <p className='App-logo'>Loading...</p>;
    }

    const content = [];

    groups.content.forEach(city => {
      content.push(
          <div>
            <div>{ city.cityName }</div>
            <img src={ city.photoUrl } alt='image not found'/>
            <Button size='sm' color='primary' tag={ Link }
                    to={ '/edit-city/' + city.id }>Edit</Button>
          </div>
      )
    });
    return content;
  }

  return (
      <div className='App'>
        <header className='App-header'>
          <h1>City List</h1>
        </header>
        { cityList() }
      </div>
  );
}

export default CityList;