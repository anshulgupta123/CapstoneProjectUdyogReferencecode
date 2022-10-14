import React from "react";
import './employersettingindex.css'
import  { useState } from "react";
import axios from "axios";
import AuthService from "../../services/AuthService";


const Base_Api_url = "http://localhost:8765/v1/profieUpdationEmployer";
const Req_email = AuthService.getUserEmail();

export default function Employersettingindex() {

const [userdata, setUserdata] = useState({
  email:Req_email,
  name:"",
  company:"",
  yearsOfExperience: "",
  companyUrl: "",
  mobileNumber:"",
  profilePhoto:null,
})
// console.log(userdata.profilePhoto);

// console.log(generatedstamp);
let upuser = {
  email:Req_email,
  name:userdata.name,
  company:userdata.company,
  yearsOfExperience: userdata.yearsOfExperience,
  companyUrl: userdata.companyUrl,
  mobileNumber:userdata.mobileNumber,
  profilePhoto:userdata.profilePhoto,

}



function handleSubmit (e){
e.preventDefault();
// axios.put(put_Api_call,upuser,{
//   headers: { "Content-Type": "multipart/form-data" }
// })
axios({
  method: "put",
  url: "http://localhost:8765/v1/profieUpdationEmployer/",
  data: upuser,
  headers: { "Content-Type": "multipart/form-data" },
}).then((res)=>{console.log(res);})
.catch((err) => {
  console.log(err);
});
console.log(Req_email);
}
function handleChange (e){
const formsdata = {...userdata}
formsdata[e.target.id] = e.target.value;
setUserdata(formsdata)

}

function handlePhotoChange (e){
  const formsdata = {...userdata, profilePhoto: e.target.files[0]}
  setUserdata(formsdata)

  }

  return (
  <div>
<form onSubmit={(e)=>{handleSubmit(e)}}>
<div className="container rounded bg-white mt-5 mb-5">
  <div className="row">
    <div className="col-md-1 border-right">

    </div>
    <div className="col-md-5 border-right bg-green">
      <div className="p-3 py-5">
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h4 className="text-right">Employer Profile </h4>
        </div>
        <div className="row mt-2">
          <div className="col-md-12">
            <label className="labels">FullName</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="name" value={userdata.name}
              type="text"
              className="form-control"
              placeholder="first name"
              defaultValue=""
            />
          </div>

        </div>

        <div className="row mt-3">


          <div className="col-md-12">
            <label className="labels">Mobile Number</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="mobileNumber" value={userdata.mobileNumber}
              type="number"
              className="form-control"
              placeholder="Add Mob Number"
              defaultValue=""
            />
          </div>

          <div className="col-md-12">
            <label className="labels">Comapany URL</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="companyUrl" value={userdata.companyUrl}
              type="text"
              className="form-control"
              placeholder="comapany url"
              defaultValue=""
            />
          </div>

          <div className="col-md-12">
            <label className="labels">Total years of Experience</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="yearsOfExperience" value={userdata.yearsOfExperience}
              type="text"
              className="form-control"
              placeholder="Experience "
              defaultValue=""
            />
          </div>

          <div className="col-md-12">
            <label className="labels">Comapany name</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="company" value={userdata.company}
              type="text"
              className="form-control"
              placeholder="name of the organisation "
              defaultValue=""
            />
          </div>
          <div className="col-md-12">
            <label className="labels">Upload profile photo</label>
            <input onChange={(e)=>{handlePhotoChange(e)}}
            id="profilePhoto"
              type="file"
              className="form-control"
              placeholder="profile photo "
              defaultValue=""
            />
          </div>
        </div>
        <div className="row mt-3">



        </div>
        <div className="mt-3 text-center">
          <button className="btn btn-primary profile-button" type="submit">
            Save Profile
          </button>
        </div>
      </div>
    </div>

  </div>
</div>

  </form>
  </div>



 )


}






