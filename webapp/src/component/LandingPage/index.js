import React from 'react'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import Login from '../login'
//import Login from '../login'
import './App.css'

function LandingPage() {
  return (
    <>
 
    <div>
    <div className='mainLanding'>
        <div className='subLanding'></div>
        <div className='subLanding'>
            <h1 className='lHeading'>Looking for right Opportunity or Candidate!!!</h1><h1 className='lHeading'>Why Wait??</h1>
            <br/> 
        <Link to='/login'><button className='lLogin'>Login</button></Link>
        <Link to='/login'><button className='lSignup'>Sign Up</button></Link>
        </div>
    </div>
    <Routes>
      <Route path='/login' element={<Login/>}></Route>
    </Routes>
    </div>
   
    </>
  )
}

export default LandingPage