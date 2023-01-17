import React from 'react';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';

const CityList = ({ cityList=[] }) => {

  return (
      <>
        { cityList.map(city => (
            <div className='City' key={ city.id }>
              <div className='CityHeader'>
                <a className='CityName'>{ city.cityName }</a>
                <Button size='sm' color='primary' tag={ Link }
                        to={ '/edit-city/' + city.id }>Edit</Button>
              </div>

              <img src={ city.photoUrl } alt='not found'/>

            </div>
        )) }
      </>
  );
}

export default CityList;