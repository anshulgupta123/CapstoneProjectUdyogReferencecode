import React, {  } from 'react';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js';
import Dashboard from './component/Dashboard';
import Footer from './Footer';

function App() {

  return (
    <><div><Dashboard /></div><div className="mainfooter"><Footer /></div></>
  );
}

export default App;
