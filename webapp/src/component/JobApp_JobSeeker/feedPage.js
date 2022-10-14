
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
import ReactPaginate from 'react-paginate';
import JobService from '../../services/JobService'
import AuthService from '../../services/AuthService';
import MessageService from '../../services/MessageService';

export default function FeedPage() {
    const [data, setData] = useState([])
    const [page, setPage] = useState(0)
    const [dummydata, setDummydata] = useState([])
    const [searchvalue, setSearchValue] = useState('')
    const [jobid, setJobid] = useState(0)
    const [file, setFile] = useState()
    const [expectedsalary, setExpectedsalary] = useState('')


    let calculatepage = async () => {
        let raw = await axios.get(`${JobService.getApiForAllJob()}?page=${page}&size=${3}&searchParam`)
        setPage(raw.data.data.totalPages)
    }

    let getdata = async (page = 0) => {
        calculatepage()
        let raw = await axios.get(`${JobService.getApiForAllJob()}?page=${page}&size=${3}&searchParam`)


        if (raw.data.length !== 0) {
            setData(raw.data.data.paginatedData)
            setDummydata(raw.data.data.totalNoOfElements)
        }

    }
    useEffect(() => {
        getdata()


    }, [])

    function capitalize(word) {
        const lower = word.toLowerCase();
        return word.charAt(0).toUpperCase() + lower.slice(1);
    }


    let handleSearch = async (e) => {
        let searchinput = capitalize(searchvalue)
        e.preventDefault();
        return await axios.get(`${JobService.getApiForAllJob()}?size=${3}&searchParam=${searchinput}`)//`localhost:8085/v1/getAllJobPostings?q=${searchvalue}`
            .then((response) => {
                // console.log(response.data.data.paginatedData)
                setData(response.data.data.paginatedData)
                setSearchValue('')
            })
            .catch((err) => console.log(err))
    }
    // let handleSort = async (e) => {
    //     let value = e.target.value
    //     console.log(`${JobService.getAllJob()}?_sort=${value}&_order=asc`)
    //     return await axios.get(`${JobService.getAllJob()}?_sort=${value}&_order=asc`)//http://localhost:3001/jobPost?_sort=${value}&_order=asc
    //         .then((response) => {
    //             setData(response.data)
    //         })
    //         .catch((err) => console.log(err))
    // }


    let handleFilter = (e) => {

        var checkbox = document.getElementById(e.target.id);
        if (checkbox.checked == true) {



            let filtereddata = dummydata.filter((elem) => {
                return elem.Package == e.target.value;
            })

            setData(filtereddata)


        }
        else {
            getdata()
        }

    }

    let handleLocationfilter = (e) => {



        let filtereddata = dummydata.filter((elem) => {
            return elem.location == e.target.value;
        })

        setData(filtereddata)



        // else {
        //     getdata()
        // }

        // console.log(e.target.value)
        // return await axios.get(`http://localhost:3001/jobPost?location=${e.target.value}`)
        //     .then((response) => {
        //         setData(response.data)
        //     })
        //     .catch((err) => console.log(err))
    }

    let handlePageClick = (data) => {
        let currPage = data.selected
        getdata(currPage)


    }
    let handleFilechange = (e) => {
        console.log(e.target.files)
        setFile(e.target.files)

    }

    let handleSalaryChange = (e) => {
        setExpectedsalary(e.target.value)
    }
    let handleApply = (id) => {
        setJobid(id)
    }

    let applyForJob = (e) => {
        e.preventDefault()
        let formData = new FormData(e.target);

        formData.append('jobId', jobid)
        console.log(e.target.parentElement.parentElement.parentElement.parentElement.id)
        console.log([...formData])


        // console.log(e.target.id)
        // console.log(AuthService.getUserEmail())

        // var applyjob = {
        //     jobId: e.target.id,
        //     appliedBy: AuthService.getUserEmail(),


        //   };

        // axios({
        //     method: "post",
        //     url: JobService.getApiApplyJob(), formData

        //   }).then((response) => {
        //     console.log(response);
        //     if (response.data.code === "0") {
        //       console.log(response.data);
        //       alert("Applied");
        //     }
        //     else {
        //       alert("Something goes wrong. Please try again later.");
        //     }
        //   }).catch((err) => {
        //     alert(err);
        //   });

        // axios.post(JobService.getApiApplyJob(), formData).then((response) => {
        //     console.log(response);
        //     if (response.data.code === "0") {
        //         console.log(response.data);
        //         alert("Applied");
        //     }
        //     else {
        //         alert("Something goes wrong. Please try again later.");
        //     }
        // }).catch((err) => {
        //     alert(err);
        // });

        axios({
            method: "post",
            url: JobService.getApiApplyJob(),
            data: formData,
            headers: { "Content-Type": "multipart/form-data" },
        }).then((response) => {
            console.log(response);
            if (response.data.code === "0") {
                console.log(response.data);
                alert("Applied");
            }
            else {
                alert("Something goes wrong. Please try again later.");
            }
        }).catch((err) => {
            alert(err.response.data.data.errorDetails);
        });

    }
    return (
        <>
            <div className='jobappcontainer'>
                <div className="leftfiltercontainer">
                    <Salaryfilter handleFilter={handleFilter} />
                    <Locationfilter handleLocationfilter={handleLocationfilter} />


                </div>
                <div className='container-m'>
                    <div className='card-container'>
                        <div className='search-box'>
                            <div className='search-container'>
                                <input placeholder='Search for jobs' type="text" className='main-search' value={searchvalue} onChange={(e) => setSearchValue(e.target.value)}></input>
                                <Button variant="primary" value="search" onClick={handleSearch} className='btn-m'>Search</Button>
                            </div>
                            {/* <div className='sort'>
                                <label for="sort">Sort by:</label>
                                <select id="sort" name="sort" onChange={handleSort}>
                                    <option value="initial" style={{ color: "grey" }}>Select value</option>
                                    <option value="company">Name</option>
                                    <option value="expirydate">Expiry Date</option>
                                </select>
                            </div> */}
                        </div>

                        {data.map((item) => (

                            <Card className='card-m' key={item.id}>

                                <Card.Body>
                                    <Card.Title className='card-title-m'>
                                        <div className='logo'>
                                            {item.companyLogo === null ? (<img src="/images/default-company-logo.png" className="img-fluid imgfeedPagelogo" alt="Company Name" />) : (

                                                <img src={`data:image;base64,${item.companyLogo.data}`} className="img-fluid imgfeedPagelogo" alt="Company Name" />
                                            )}
                                        </div>
                                        <div className='titleCard'>
                                            {item.company}-{item.position}</div>
                                    </Card.Title>
                                    <Card.Text>
                                        {item.description}
                                    </Card.Text>
                                    <div className='skiiDesc'>{item.skillSet}</div>
                                    <div className='badges'>
                                        <span className='badge'>Fulltime</span>
                                        <span className='badge'>UI/UX</span>
                                        <span className='badge'>Remote</span>
                                    </div>
                                    <hr />
                                    <div className='cardFooter'>

                                        <div className='subDescription'>
                                            <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-solid fa-sack-dollar" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.jobPackage}</span>
                                            <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-solid fa-briefcase" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.experiencedRequired} years</span>
                                            <span style={{ color: 'GrayText', marginRight: '15px' }} ><i class="fa-sharp fa-solid fa-location-dot" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{item.location}</span>
                                           {(AuthService.isEmployer())?'':<Link to='/message'><button className='messageTo' onClick={()=>{addToChat(item.employerEmail)}}></button></Link>}
                                        </div>
                                        
                                        <div style={{ width: '100%' }} >
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myId" onClick={() => handleApply(item.id)}>
                                                Apply
                                            </button>
                                            <div class="modal fade" id="myId" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content" id={item.id}>
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">Submit details</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form id={item.id} onSubmit={applyForJob}>
                                                                <div class="mb-3">
                                                                    <label for="appliedBy" class="form-label">Email address</label>
                                                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name='appliedBy' value={AuthService.getUserEmail()} />
                                                                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                                                                </div>
                                                                <div class="mb-3">
                                                                    <label for="expectedSalary" class="form-label" onChange={handleSalaryChange} value={expectedsalary}>Expected salary</label>
                                                                    <input type="text" class="form-control" id="exampleInputPassword1" name='expectedSalary' placeholder='Enter expected salary' />
                                                                </div>
                                                                <div class="mb-3">
                                                                    <label for="exampleInputPassword1" class="form-label">Upload Resume</label>
                                                                    <input type="file" class="form-control" id="exampleInputFile" onChange={handleFilechange} name="updatedResume" />
                                                                </div>
                                                                <div class="modal-footer">

                                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                                </div>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </Card.Body>
                            </Card>
                        ))}
                    </div>




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
                <Skillsuggestion />
            </div>
        </>
    );

    function addToChat(empid){
        
     
      axios({
        method: 'post',
        url: MessageService.getApiPostNewChat(),
        data: {
          seekerEmail : AuthService.getUserEmail(),
          employerEmail : empid
        }
    })
    }
}

// export default FeedPage;



function Salaryfilter({ handleFilter }) {
    const [data, setData] = useState([])
    let showValue = (e) => {
        var checkbox = document.getElementById(e.target.id);
        if (checkbox.checked == true) {

        }
    }

    return (
        <div className="skillcard cardLeft">
            {/* <img src="https://cdn-icons-png.flaticon.com/512/3135/3135706.png" className="sideimage"></img> */}
            <div className="skilltitle">Salary</div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="1-3 Lakh" id="salary1" onClick={handleFilter} />
                <label class="form-check-label" for="flexCheckDefault">
                    1-3 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="3-6 Lakh" id="salary2" onClick={handleFilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    3-6 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="6-8 Lakh" id="salary3" onClick={handleFilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    6-8 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="8-10 Lakh" id="salary4" onClick={handleFilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    8-10 Lakh
                </label>
            </div>
        </div>
    )
}

function Locationfilter({ handleLocationfilter }) {

    return (
        <div className="skillcard cardLeft">
            {/* <img src="https://cdn-icons-png.flaticon.com/512/4781/4781517.png" className="sideimage"></img> */}
            <div className="skilltitle">Location</div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Delhi/NCR" name="Location" onClick={handleLocationfilter} />
                <label class="form-check-label" for="flexCheckDefault">
                    Delhi/NCR
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Bangalore/Bengaluru" name="Location" onClick={handleLocationfilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    Bangalore/Bengaluru
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Mumbai/Pune" name="Location" onClick={handleLocationfilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    Mumbai/Pune
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Hyderabad/Secunderabad" name="Location" onClick={handleLocationfilter} />
                <label class="form-check-label" for="flexCheckChecked">
                    Hyderabad/Secunderabad
                </label>
            </div>
        </div>
    )
}


