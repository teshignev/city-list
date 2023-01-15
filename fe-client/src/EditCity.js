import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const EditCity = () => {

  const initialState = {
    cityName: '',
    photoUrl: ''
  };

  const [ city, setCity ] = useState(initialState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    fetch(`/city?id=${ id }`)
    .then(response => response.json())
    .then(data => setCity(data));
  }, [ id, setCity ]);

  const title = <h2>Edit { city.cityName }</h2>;

  const handleChange = (event) => {
    const { name, value } = event.target

    setCity({ ...city, [ name ]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch(`/city?id=${ city.id }&cityName=${ city.cityName }&photoUrl=${ city.photoUrl }`, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(city)
    });
    setCity(initialState);
    navigate('/');
  }

  return (<div>
        <AppNavbar/>
        <Container>
          { title }
          <Form onSubmit={ handleSubmit }>
            <FormGroup>
              <Label for="name">Name</Label>
              <Input type="text" name="cityName" id="cityName" value={ city.cityName || '' }
                     onChange={ handleChange } autoComplete="cityName"/>
            </FormGroup>
            <FormGroup>
              <Label for="Url">Url</Label>
              <Input type="text" name="photoUrl" id="photoUrl" value={ city.photoUrl || '' }
                     onChange={ handleChange } autoComplete="photoUrl"/>
            </FormGroup>
            <FormGroup>
              <Button color="primary" type="submit">Save</Button>{' '}
              <Button color="secondary" tag={ Link } to="/">Cancel</Button>
            </FormGroup>
          </Form>
        </Container>
      </div>
  )
}

export default EditCity;