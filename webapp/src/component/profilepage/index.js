import React from "react";
import "./styles1.css";
import { useEffect, useState } from "react";
import AuthService from "../../services/AuthService";
import axios from "axios";

const Base_Api_url = "http://localhost:8765/v1/getSeeker/";
const Req_id = AuthService.getUserEmail();
const Api_call = Base_Api_url + Req_id;

export default function Profilepage() {
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
  const [description, setDescription] = useState("Enter description");
  const [currentOrganisation, setCurrentOrganisation] = useState(
    "Enter organisation name"
  );
  const [higherEducation, setHigherEducation] = useState(
    "Enter education details"
  );
  const [location, setlocation] = useState("Enter location");
  const [email, setEmail] = useState("Enter email");
  const [noticePeriod, setNoticePeriod] = useState(
    "Enter noticePeriod in month"
  );
  const [createdOn, setuCreatedOne] = useState("usercreated on");
  const [skillSet, setSkillSet] = useState(["skill1", "skill2"]);
  const [mobileNumber, setMobileNumber] = useState("Enter mobile Number");
  const [status, setStatus] = useState("ACTIVE");
  const [currentrole, setCurrentRole] = useState("enter Designation");
  const [education, setEducation] = useState(["education1", "education2"]);
  const [dob, setDob] = useState("Enter date of birth");
  const [resume, setResume] = useState('');
  const [photo, setPhoto] = useState('')




  let getdata = async () => {
    let raw = await axios.get(
        Api_call
    );

    let uemail = await raw.data.data.email;
    let uname = await raw.data.data.name;
    let udescription = await raw.data.data.description;
    let uhigherEducation = await raw.data.data.higherEducation;
    let uskillSet = await raw.data.data.skillSet;
    let ueducation = await raw.data.data.education;
    let ucurrentOrganisation = await raw.data.data.currentOrganisation;
    let unoticePeriod = await raw.data.data.noticePeriod;
    let ustatus = await raw.data.data.status;
    let ulocation = await raw.data.data.location;
    let ucreatedOn = await raw.data.data.createdOn;
    let umobileNumber = await raw.data.data.mobileNumber;
    let ucurrentrole = await raw.data.data.currentRole;
    let udob = await raw.data.data.dob;
    var uresume = await raw.data.data.resume.data;
    var uphoto = await raw.data.data.profilePhoto.data;




    setUname(uname);
    setDescription(udescription);
    setCurrentOrganisation(ucurrentOrganisation);
    setHigherEducation(uhigherEducation);
    setlocation(ulocation);
    setEmail(uemail);
    setNoticePeriod(unoticePeriod);
    setSkillSet(uskillSet);
    setMobileNumber(umobileNumber);
    setStatus(ustatus);
    setCurrentRole(ucurrentrole);
    setEducation(ueducation);
    setResume(uresume);
    setPhoto(uphoto)



    // console.log(skillSet);
    let myDate = new Date(ucreatedOn);
    let myDob = new Date(udob);


    let dateStr =
      myDate.getFullYear() +
      "/" +
      (myDate.getMonth() + 1) +
      "/" +
      myDate.getDate() +
      " " +
      myDate.getHours() +
      ":" +
      myDate.getMinutes() +
      ":" +
      myDate.getSeconds();


      let dobStr =
      myDob.getFullYear() +
      "/" +
      (myDob.getMonth() + 1) +
      "/" +
      myDob.getDate()
    setuCreatedOne(dateStr);
    setDob(dobStr)
  };
  useEffect(() => {
    getdata();
  }, []);

  // console.log(Api_call);
  const [toggleState, setToggleState] = useState(1);

  const toggleTab = (index) => {
    setToggleState(index);
  };
  function loadresume (){
    var link =  document.createElement('a');
    link.innerHTML = '<button>Download resume<button/>';
    link.download = `${uname} Resume.pdf`;
    link.href = 'data:application/octet-stream;base64,' + resume;
    document.body.appendChild(link);
    document.getElementsByClassName('downloadsection')[0].appendChild(link);
  }

  let imgsrc = `data:image/png;base64,${photo}`
useEffect(loadresume,[resume])

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
              <h5>{uname}</h5>
              <h6>{currentrole}</h6>
            </div>
          </div>

          <div id="bio">
            <p >{description}</p>
          </div>
        </div>
      </form>
      <div className="main-container">
        <div class="col">
          <div class="profile-work">
            <h5>SKILLS</h5>
            <p>
              {skillSet.map((item) => (
                <p>{item}</p>
              ))}
            </p>
            <br />
          </div>
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
              Experience
            </button>
            <button
              className={toggleState === 3 ? "tabs active-tabs" : "tabs"}
              onClick={() => toggleTab(3)}
            >
              Education
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
                  <label>Date of birth</label>
                </div>
                <div class="col-md-6">
                  <p>{dob}</p>
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
                  <label>location</label>
                </div>
                <div class="col-md-6">
                  <p>{location}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label>profile creadted on</label>
                </div>
                <div class="col-md-6">
                  <p>{createdOn}</p>
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
                  <p>{currentOrganisation}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label>Designation</label>
                </div>
                <div class="col-md-6">
                  <p>{currentrole}</p>
                </div>
                <div class="col-md-6">
                  <label>Notice period</label>
                </div>
                <div class="col-md-6">
                  <p>{noticePeriod} Months</p>
                </div>
              </div>
            </div>
            <div
              className={
                toggleState === 3 ? "content  active-content" : "content"
              }
            >
              <div class="row">
                <div class="col-md-6">
                  <label>Higest qualification</label>
                </div>
                <div class="col-md-6">
                  <p>{higherEducation}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <label>other Education qualification</label>
                </div>
                <div class="col-md-6">
                    <div>
                    <p>
                    {education.map((items) => (
                      <p>{items}</p>
                    ))}
                  </p>
                    </div>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
<div className="downloadsection">
</div>



    </div>
  );
}
