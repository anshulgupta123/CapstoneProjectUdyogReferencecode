import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import './App.css';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { Link, Navigate, Route, Routes } from 'react-router-dom';
import Index from '../JobApp_JobSeeker';
import Index2 from '../JobPosting/Index2';
import LandingPage from '../LandingPage';
import JobApp from '../JobApp_Employer/Index';
import Login from '../login';
import Index1 from '../JobPosting/Index1';
import Profilepage from '../profilepage';
import { useEffect, useState } from 'react';
import About from '../About';
import Acceptedjobs from '../JobApp_JobSeeker/index1';
import Rejectedjobs from '../JobApp_JobSeeker/index2';
import feedPage from '../JobApp_JobSeeker/feedPage';
import FeedPage from '../JobApp_JobSeeker/feedPage';
import Home from '../Home/Home';
import CmpFeedBack from '../CmpFeedback';
import AuthService from '../../services/AuthService';
import ProfileSetting from '../profileSetting';
import Messaging from '../Messaging'
import Employerprofile from '../employerprofile/employerindex';
import Employersettingindex from '../employersetting/employersettingindex';



function Dashboard() {

  const [user, setUser] = useState(undefined);
  // const [employer, setEmp] = useState('');

  // useEffect(() => {
  //   setEmp(AuthService.getUserType())
  // }, [])

  useEffect(() => {
    setUser(AuthService.getUser())
  }, [])
  const logout = () => {
    setUser(undefined)
    AuthService.logout();
    alert("Log-Out successfully");
    localStorage.clear();
    Navigate("/login")
  };

  if (!AuthService.isLogedIn()) {
    return (<Login />)
  }

  return (

    <>
      {(!AuthService.isLogedIn()) ? "" : (
        <div className='dashboardDiv'>
          <Navbar collapseOnSelect expand="md" sticky="top" variant="light">
            <Container className='mainNav'>
              {/* <Navbar.Brand ><div className='logoClass'></div></Navbar.Brand> */}
              <Navbar.Brand ><Link to='/home'><img src="./images/udoyg-logo.png" className="img-fluid newlogo" /></Link></Navbar.Brand>

        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">

                  <Nav.Link ><Link to='/home'><span className='navTab'>Home</span></Link></Nav.Link>

                  <Nav.Link ><Link to='/feedpage'><span className='navTab'>Jobs</span></Link></Nav.Link>
                  <Nav.Link ><Link to='/about'><span className='navTab'>About Us</span></Link></Nav.Link>
                  <Nav.Link ><Link to='/feedback'><span className='navTab'>Feedback</span></Link></Nav.Link>

                </Nav>
                <Nav className='me-auto'></Nav>
                <Nav>
                  <Nav.Link ><Link to='/message'><div className='msgIcon'></div></Link></Nav.Link>
                  {(AuthService.isEmployer() == true) ? (<DropdownButton id="dropdown-basic-button" title={AuthService.getUserEmail()}>
                    <Dropdown.Item ><Link to="/emplyerprofile">My Profile</Link></Dropdown.Item>

                    <Dropdown.Item ><Link to="/myprofile">Edit Profile</Link></Dropdown.Item>
                    <Dropdown.Item ><Link to="/postjob">Post A Job</Link></Dropdown.Item>
                    {/* <Dropdown.Item ><Link to='/jobsapplied'>Jobs Applied</Link></Dropdown.Item> */}
                    <Dropdown.Item ><Link to='/jobsposted'>jobs Posted</Link></Dropdown.Item>
                    <Dropdown.Item ><a onClick={logout}>Log Out</a></Dropdown.Item>
                  </DropdownButton>) : (<DropdownButton id="dropdown-basic-button" title={AuthService.getUserEmail()}>
                    <Dropdown.Item ><Link to="/Profilepage">My Profile</Link></Dropdown.Item>
                    <Dropdown.Item ><Link to="/Profilesetting">Edit Profile</Link></Dropdown.Item>


                    <Dropdown.Item ><Link to='/jobsapplied'>Jobs Applied</Link></Dropdown.Item>
                    {/* <Dropdown.Item ><Link to='/jobsposted'>jobs Posted</Link></Dropdown.Item> */}
                    <Dropdown.Item ><a onClick={logout}>Log Out</a></Dropdown.Item>
                  </DropdownButton>)}
                </Nav>
              </Navbar.Collapse>
            </Container>
          </Navbar>
        </div>
      )}
      <Routes>
        <Route path='/jobsapplied' element={<Index />}></Route>
        <Route path='/home' element={<Home />}></Route>
        <Route path='/feedpage' element={<FeedPage />}></Route>
        <Route path="/jobsposted" element={<Index1 />}></Route>
        <Route path='/postjob' element={<Index2 />}></Route>
        <Route path='/' element={AuthService.isLogedIn() ? <Home /> : <Login />}></Route>
        <Route path='/profilepage' element={<Profilepage />}></Route>
        <Route path='/profilesetting' element={<ProfileSetting />}></Route>
        <Route path='/application' element={<JobApp />}></Route>

       
        <Route path='/about' element={<About />}></Route>
        <Route path='/acceptedjobs' element={<Acceptedjobs />}></Route>
        <Route path='/rejectjobs' element={<Rejectedjobs />}></Route>

        <Route path="/login" element={AuthService.isLogedIn() ? <Home /> : <Login />}></Route>
        <Route path='/about' element={<About />}></Route>
        <Route path='/acceptedjobs' element={<Acceptedjobs />}></Route>
        <Route path='/rejectjobs' element={<Rejectedjobs />}></Route>
        <Route path='/feedback' element={<CmpFeedBack/>}></Route>
        <Route path='/message' element={<Messaging />}></Route>
        
        <Route path='/emplyerprofile' element={<Employerprofile />}></Route>
        <Route path='/myprofile' element={<Employersettingindex />}></Route>



      </Routes>
    </>
  );
}



export default Dashboard;