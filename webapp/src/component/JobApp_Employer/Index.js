
import './App.css';

import Button from 'react-bootstrap/Button';
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge';
import { useEffect, useState } from 'react';
import axios from 'axios';
import JobService from '../../services/JobService';
import AuthService from '../../services/AuthService';

function JobApp() {
  const [alldata, setAllData] = useState([])
  const [allapplied, setAllApplied] = useState([])
  const [allmail, setAllMail] = useState([])
  const getData = async () => {
   

    const allpostAPI = `${JobService.getApiForAllJob()}?page&size=&searchParam`;
    const allAppliedAPI = JobService.getAPIAppliedJobs();
  
    const getAllPost = axios.get(allpostAPI)
    const getAllApplied = axios.get(allAppliedAPI)

    axios.all([getAllPost, getAllApplied]).then(
      axios.spread((...allData) => {

        const allDataPost = allData[0].data.data.paginatedData
        const allDataApplied = allData[1].data.data.paginatedData

        setAllData(allDataPost)
        setAllApplied(allDataApplied)
        
      })
    )

  }
  const func=(id)=>{
    let mail=allapplied.map((item)=>{
    
      if( item.jobId==id){
        setAllMail(current => [...current, item])
      }
    })
    console.log(allmail)
  }
  const show=()=>{
    console.log(alldata)
    console.log(allapplied)
    alldata.map((item)=>  func(item.id))
    console.log(AuthService.getUserEmail())
    
  }


  useEffect(() => {
    getData()
   
  }, [])

  return (
    <>
     <button onClick={show}>click</button>
    {allmail.map((item,index)=>{
      if(item==AuthService.getUserEmail()){
      return <h1>{item}{index}</h1>
    }
   
    })}
    </>
  );
}

export default JobApp;
