
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
import JobService from '../../services/JobService';
import ReactPaginate from 'react-paginate';
// import { Link } from 'react-router-dom'

//`${JobService.getApiForAllJob()}?page&size=&searchParam`
//JobService.getApiAppliedJobsEmail()


function Index() {
  // const ar=[];
  const [data, setData] = useState([])
  const [page, setPage] = useState(0)
  const [alldata, setAllData] = useState([])
  const [jobArr, setJobArr] = useState([])
  const [arr, setArr] = useState([])



  let func1 = (jId, status) => {
    console.log(jId, alldata)
    alldata.map((item) => {
      if (item.id == jId && status == "APPLIED") {
        console.log(status)
        // ar.push(item)
        // console.log(prevArr())
        setData(current => [...current, item])
      }
    })
  }

  // let getdata = () => {
  //   axios
  //   .get(JobService.getApiAppliedJobsEmail())
  //   .then(function (response) {
  //     // console.log(response.data.data.paginatedData);
  //     setJobArr(response.data.data.paginatedData)
  //   });

  // }

  let getFinalAppliedArr =  () => {
    setData([])
    setTimeout(()=>{
      console.log(jobArr)
    },1000) 
    jobArr.map((item) => func1(item.jobId, item.status))
  }
  // let getFinalRejectArr=async()=>{
  //   console.log(jobArr)
  //   jobArr.map((item) => func2(item.jobId))
  // }


  let calculatepage = async () => {
    let raw = await axios.get(`${JobService.getApiAppliedJobsEmail()}?page=${page}&size=${3}&searchParam`)
    setPage(raw.data.data.totalPages)
  }
  const fetchData = (page = 0) => {
    calculatepage()

    const allpostAPI = `${JobService.getApiForAllJob()}?page&size=&searchParam`;
    const appliedAPI = `${JobService.getApiAppliedJobsEmail()}?page=${page}&size=${3}`;
    console.log(appliedAPI)
    const getAllPost = axios.get(allpostAPI)
    const getAllApplied = axios.get(appliedAPI)

    axios.all([getAllPost, getAllApplied]).then(
      axios.spread((...allData) => {

        const allDataPost = allData[0].data.data.paginatedData
        const allDataApplied = allData[1].data.data.paginatedData

        setAllData(allDataPost)
        setJobArr(allDataApplied)
        getFinalAppliedArr()
      })
    ).then(()=>{getFinalAppliedArr()})
  }
  useEffect(() => {
    fetchData()

  }, [])

  let show = () => {
    console.log(arr)
  }
  let handlePageClick = (data) => {
    console.log(data.selected)
    let currPage = data.selected
    fetchData(currPage)
  }

  return (
    <>
    <div>

      <div className='jobappcontainer'>

        <div className='container-m'>

          <div className='myButtons'>

            <Button variant="outline-dark" className="hoverButton active" onClick={getFinalAppliedArr}>Applied</Button>

            {/* <Button variant="outline-success" className="hoverButton"  >Accepted</Button> */}
            <Button variant="outline-danger" className="hoverButton">Rejected</Button>
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

      <div className='my-2'>
          <ReactPaginate
            pageCount={page}
            onPageChange={handlePageClick}
            containerClassName={'pagination'}
            pageClassName={"page-item"}
            pageLinkClassName={"page-link"}
            previousClassName={"page-item"}
            previousLinkClassName={"page-link"}
            nextClassName={"page-item"}
            nextLinkClassName={"page-link"}
            activeClassName={"active"}
          />
        </div>
        </div>


      </div>

        </div>
    </>
  );
}

export default Index;
