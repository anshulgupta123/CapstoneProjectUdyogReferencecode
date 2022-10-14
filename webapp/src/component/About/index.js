import React from 'react';
import { Container } from 'react-bootstrap';
import './App.css'
import AutoPlay from './CompCarousal';
import Responsive from './CompCarousal';
import CompCarousal from './CompCarousal';

function About() {
  return (
   <>
        <Container>
        <div class="qsb-header-container"><h1 class="qsb-title">Find your dream job now</h1>
        <div class="qsb-byLine">5 lakh+ jobs for you to explore</div>
        <p>Connect with 20000+ employers. Apply to millions of job opportunities across top companies, industries and locations on India's No.1 jo site. Apply online. </p>
        <p>Finding the right job made easy with Udyog.com. Register Now and Apply to the best jobs! Get Headhunted By Top Recruiters and Find the Right Job On Udyog.com. </p><p>Register Now! Finance Jobs. BPO Jobs. Sales Jobs. Marketing Jobs. Engineering Jobs. Fresher Jobs.</p>
        <h2 class="headline">Explore top companies hiring now</h2>
        <Container>
            <AutoPlay/>
        </Container>
         
            <div className='skillClass'>
            <div className="left-section">
                <img src="https://static.naukimg.com/s/0/0/i/role-collection.png" className="role-img" alt="naukri role-collection"></img>
                    <p className="heading">Discover jobs across popular roles</p>
                    <p className="sub-heading">Select a role and we'll show you relevant jobs for it!</p>
            </div>
            <div className='right-section'>
                <ul>
                    <li>Full Stack</li>
                    <li>Front-End</li>
                    <li>Java Developer</li>
                    <li>Technical Manager</li>
                    <li>Hotel-Management</li>
                    <li>Finance</li>
                </ul>        
            </div>
            <div className='right-section2'>
                <ul>
                    <li>Dev Apps</li>
                    <li>Mobile App</li>
                    <li>Mechanical Engineer</li>
                    <li>Data Scince</li>
                    <li>Business Analyst</li>
                    <li>Research Analyst</li>
                </ul>
            </div>
            </div>
           </div>
        </Container>
   </>
  )
}

export default About