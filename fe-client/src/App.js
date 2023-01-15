import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import CityList from './CityList';
import EditCity from './EditCity';

const App = () => {
  return (
      <Router>
        <Routes>
          <Route exact path="/" element={<CityList/>}/>
          <Route path='/edit-city/:id' element={<EditCity/>}/>
        </Routes>
      </Router>
  )
}

export default App;
