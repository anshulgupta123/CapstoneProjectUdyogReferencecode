import React from "react";
import "./employerindex.css";
import { useEffect, useState } from "react";
import AuthService from "../../services/AuthService";
import axios from "axios";

const Base_Api_url = "http://localhost:8765/v1/getEmployer/";
const Req_id = AuthService.getUserEmail();
const Api_call = Base_Api_url + Req_id;

export default function Employerprofile() {
  // name: "",
  // description:"add a description",
  // currentOrganisation:"",
  // higherEducation:"",
  // location:"",
  // skillSet:[],
  // education:[],
  // noticePeriod:""
  //status
  //mobileNumber
  //currentRole

  //     })
  const [uname, setUname] = useState("Enter a name");


  const [email, setEmail] = useState("Enter email");
  const [company, setcompany] = useState(
    "Company name"
  );
  const [companyUrl, setcompanyUrl] = useState("company link");
  const [mobileNumber, setMobileNumber] = useState("Enter mobile Number");
  const [status, setStatus] = useState("ACTIVE");
  const [yearsOfExperience, setyearsOfExperience] = useState("0");
  const [photo, setPhoto] = useState('')



console.log(Api_call);
  let getdata = async () => {
    let raw = await axios.get(
        Api_call
    )

    let uemail = await raw.data.data.email;
    let uname = await raw.data.data.name;
    let ucomanyurl = await raw.data.data.companyUrl;
    let ustatus = await raw.data.data.status;
    let umobileNumber = await raw.data.data.mobileNumber;
    let uyearsOfExperience = await raw.data.data.yearsOfExperience;
    let ucomany = await raw.data.data.company;
    var uphoto = await raw.data.data.profilePhoto.data;


console.log(raw.data.data);
    setUname(uname);
    setcompanyUrl(ucomanyurl)
    setEmail(uemail);
    setyearsOfExperience(uyearsOfExperience)
    setMobileNumber(umobileNumber);
    setStatus(ustatus);
    setcompany(ucomany);
    setPhoto(uphoto)


    // console.log(raw.data.data);
    // console.log(skillSet);

  }
  useEffect(() => {
    getdata();
  }, []);

  // console.log(Api_call);
  const [toggleState, setToggleState] = useState(1);

  const toggleTab = (index) => {
    setToggleState(index);
  };
  let imgsrc = `data:image/png;base64,${photo}`
  return (
    <div class="container emp-profile">
      <form method="post">
        <div class="row">
          <div class="col-md-12">
            <div className="change-photo">
              <div class="profile-img">
                <img
                  src={imgsrc}
                  alt=""
                />
                {/* <div class="file btn btn-sm btn-primary" id="change-btn">
                  Change Photo
                  <input type="file" name="file" />
                </div> */}
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="profile-head">
              <h3>{uname}</h3>
              <h5>Recruiter</h5>
            </div>
          </div>


        </div>
      </form>
      <div className="main-container">
        <div class="col">

        </div>

        <div className="pp-container" id="bio-section">
          <div className="bloc-tabs">
            <button
              className={toggleState === 1 ? "tabs active-tabs" : "tabs"}
              onClick={() => toggleTab(1)}
            >
              Basic Details
            </button>
            <button
              className={toggleState === 2 ? "tabs active-tabs" : "tabs"}
              onClick={() => toggleTab(2)}
            >
              Company details
            </button>

          </div>

          <div className="content-tabs">
            <div
              className={
                toggleState === 1 ? "content  active-content" : "content"
              }
            >
              <div class="row">
                <div class="col-md-6">
                  <label>Name</label>
                </div>
                <div class="col-md-6">
                  <p>{uname}</p>
                </div>
              </div>



              <div class="row">
                <div class="col-md-6">
                  <label>Email</label>
                </div>
                <div class="col-md-6">
                  <p>{email}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label>Phone</label>
                </div>
                <div class="col-md-6">
                  <p>{mobileNumber}</p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <label>status</label>
                </div>
                <div class="col-md-6">
                  <p>{status}</p>
                </div>
              </div>
            </div>

            <div
              className={
                toggleState === 2 ? "content  active-content" : "content"
              }
            >
              <div class="row">
                <div class="col-md-6">
                  <label>Organisation Name</label>
                </div>
                <div class="col-md-6">
                  <p>{company}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label>Designation</label>
                </div>
                <div class="col-md-6">
                  <p>HR</p>
                </div>
                <div class="col-md-6">
                  <label>Company URL</label>
                </div>
                <div class="col-md-6">
                  <p>{companyUrl}</p>
                </div>
                <div class="col-md-6">
                  <label>Total years of experience</label>
                </div>
                <div class="col-md-6">
                  <p>{yearsOfExperience}</p>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>



    </div>
  );
}
