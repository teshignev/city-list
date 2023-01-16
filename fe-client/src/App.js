import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import EditCity from './components/EditCity';
import SearchPage from './components/SearchPage';

const App = () => {
  return (
      <Router>
        <Routes>
          <Route exact path='/' element={ <SearchPage/> }/>
          <Route path='/edit-city/:id' element={ <EditCity/> }/>
        </Routes>
      </Router>
  )
}

export default App;
