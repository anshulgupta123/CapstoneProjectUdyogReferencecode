
import './App.css';

import Button from 'react-bootstrap/Button';
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge';
import { useEffect, useState } from 'react';
import axios from 'axios'
import { Link } from 'react-router-dom';
import Skillsuggestion from './Skillsuggestion';
import LeftSidefilters from './LeftSidefilters';
// import { Link } from 'react-router-dom'


function Acceptedjobs() {
    const [data, setData] = useState([])
    let getdata = async () => {
      let raw = await axios.get("http://localhost:3001/jobAccepted")
      
      
    setData(raw.data)
  }
  useEffect(() => {
    getdata()
  }, [])

  return (
    <>
      <div className='jobappcontainer'>
     <LeftSidefilters/>
        <div className='container-m'>

          <div className='myButtons'>

            <Link to='/jobsapplied'>   <Button variant="outline-dark" className="hoverButton">Applied</Button></Link>

            <Link to='/acceptedjobs'>   <Button variant="outline-success" className="hoverButton active"  >Accepted</Button>{' '}</Link>
            <Link to='/rejectjobs'>  <Button variant="outline-danger" className="hoverButton">Rejected</Button>{' '}</Link>
          </div>




          {data.map((item) => (
            
            <Card className='card-m'>

              <Card.Body>
                <Card.Title className='card-title-m'>
                  <div className='logo'>
                    <img className='img-m' src={item.companylogo}></img>
                  </div>
                  <div className='titleCard'>
                    {item.company}</div>
                </Card.Title>
                <Card.Text>
                  {item.description}
                </Card.Text>
                <div className='skiiDesc'>{item.skillset}</div>
                <div className='badges'>
                  <span className='badge'>Fulltime</span>
                  <span className='badge'>UI/UX</span>
                  <span className='badge'>Remote</span>
                </div>
                <hr />
                <div className='cardFooter'>
                  <div className='subDescription'>
                    <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-solid fa-sack-dollar" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.Package}</span>
                    <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-solid fa-briefcase" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.experiencedRequired}</span>
                    <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-sharp fa-solid fa-location-dot" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.location}</span>
                  </div>
                  <div style={{ width: '100%' }}>
                    <Button variant="primary" className='btn-m'>Contact</Button>
                  </div>
                </div>
              </Card.Body>
            </Card>
          ))}

        </div>
          <Skillsuggestion/>
      </div>
    </>
  );
}

export default Acceptedjobs;
