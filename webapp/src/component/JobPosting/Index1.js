
import React from 'react';
import axios from 'axios';
import "./Style1.css"
import { useEffect, useState } from 'react'
import AuthService from '../../services/AuthService';
import JobService from '../../services/JobService';


function Index1() {

  const [jobpostdata, setJobPostData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const { data: response } = await axios.get(JobService.getApiForAllJob());

      // console.log(response.data.paginatedData)
      setJobPostData(response.data.paginatedData)
    }
    fetchData();
  }, []);

  const jobdata = (event) => {
    event.preventDefault()

    var currid = event.target.jpid.value;

    var applyjob = {
      jobId: currid,
      appliedBy: AuthService.getUserEmail()
    };

    axios({
      method: "post",
      url: JobService.getApiApplyJob(),
      data: applyjob,
      headers: { "Content-Type": "multipart/form-data" },
    }).then((response) => {
      // console.log(response);
      if (response.data.code === "0") {
        // console.log(response.data);
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
      <div class="container mt-3" id="jp-in-1">
        <div class="row">
          <div col-sm-12>
            {/* {console.log(jobpostdata)} */}
            {jobpostdata.map((x) => {
              return (
                <form onSubmit={jobdata}>
                  <div class="col-md-12  mb-3">
                    <div class="card" id="jp-in-2">
                      <div class="card-body" >
                        <div class="logo">
                          {x.companyLogo === null ? (<img src="/images/default-company-logo.png" className="img-fluid imgJPlogo" alt="Company Name" />) : (

                            <img src={`data:image;base64,${x.companyLogo.data}`} className="img-fluid imgJPlogo" alt="Company Name" />
                          )}
                        </div>
                        <h1 class="title-text ">{x.company}</h1>
                        <h3 class="card-text">Position : {x.position}</h3>
                        <h6 class="card-text text-secondary"><i class='fas fa-file-alt' style={{ color: 'var(--header)', marginRight: '10px' }}></i> {x.description}</h6>
                        <h6 class="card-text text-secondary"><i class='fas fa-user-graduate' style={{ color: 'var(--header)', marginRight: '10px' }}></i>{x.skillSet.toString()}</h6>
                        <h6 class="card-text text-secondary"><i class="fa-solid fa-briefcase" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{x.experiencedRequired} years</h6>
                        <h6 class="card-text text-secondary"> <i class="fa-solid fa-sack-dollar" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{x.jobPackage}/-</h6>
                        <h6 class="card-text text-secondary"><i class="fa-sharp fa-solid fa-location-dot" style={{ color: 'var(--header)', marginRight: '10px' }}></i>{x.location}</h6>
                        <div class="container" >
                          <div class="row">
                            <div class="col-sm-12" id="jp-in-3">
                              <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <input type="text" value={x.id} name="jpid" hidden />
                                <button class="btn btn-primary me-md-2" type="submit" value="PostJob">Apply Now</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </form>
              );
            })}
          </div>
        </div>
      </div>
    </>
  )
}
export default Index1;
