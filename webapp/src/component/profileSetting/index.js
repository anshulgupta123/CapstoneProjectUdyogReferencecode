import React from "react";
import "./styles2.css";
import { useState } from "react";
import axios from "axios";
import AuthService from "../../services/AuthService";

const Base_Api_url = "http://localhost:8765/v1/profieUpdationSeeker/";
const Req_email = AuthService.getUserEmail();
const put_Api_call = Base_Api_url;

export default function ProfileSetting() {
  const [userdata, setUserdata] = useState({
    email: Req_email,
    name: "",
    description: "",
    location: "",
    higherEducation: "",
    currentRole: "",
    currentOrganisation: "",
    mobileNumber: "",
    noticePeriod: "",
    dob: "",
    skillSet: "",
    experience: "",
    resume: null,
    profilePhoto: null,

    // resume:null,
  });


  let generatedstamp = new Date(userdata.dob).getTime();

  function handleSubmit(e) {
    e.preventDefault();

    // console.log(generatedstamp);
    let upuser = {
      email: Req_email,
      name: userdata.name,
      description: userdata.description,
      location: userdata.location,
      higherEducation: userdata.higherEducation,
      currentRole: userdata.currentRole,
      currentOrganisation: userdata.currentOrganisation,
      mobileNumber: userdata.mobileNumber,
      noticePeriod: userdata.noticePeriod,
      dob: generatedstamp,
      skillSet: userdata.skillSet,
      experience: userdata.experience,
      resume: userdata.resume,
      profilePhoto: userdata.profilePhoto,
    };

    // axios.put(put_Api_call,upuser,{
    //   headers: { "Content-Type": "multipart/form-data" }
    // })
    axios({
      method: "put",
      url: Base_Api_url,
      data: upuser,
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then(alert("profile updated successfuly"))
      .catch((err) => {
        console.log(err);
      });
    console.log(upuser);
  }
  function handleChange(e) {
    const formsdata = { ...userdata };

    formsdata[e.target.id] = e.target.value;
    setUserdata(formsdata);
  }

  function handleChangeresume(e) {
    const formsdata = { ...userdata, resume: e.target.files[0] };
    setUserdata(formsdata)
    //  let  resume = e.target.value.files[0]

  }

  function handleChangephoto(e) {
    const formsdata = { ...userdata, profilePhoto: e.target.files[0] };
    setUserdata(formsdata)
    //  let  resume = e.target.value.files[0]

  }
  return (
    <div>
      <form
        onSubmit={(e) => {
          handleSubmit(e);
        }}
      >
        <div className="container rounded bg-white mt-5 mb-5">
          <div className="row">
            <div className="col-md-1 border-right"></div>
            <div className="col-md-5 border-right bg-green">
              <div className="p-3 py-5">
                <div className="d-flex justify-content-between align-items-center mb-3">
                  <h4 className="text-right">Edit Profile </h4>
                </div>
                <div className="row mt-2">
                  <div className="col-md-12">
                    <label className="labels">FullName</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="name"
                      value={userdata.name}
                      type="text"
                      className="form-control"
                      placeholder="first name"
                      defaultValue=""
                    />
                  </div>
                </div>
                <div className="row mt-2">
                  <div className="col-md-12">
                    <label className="labels">Date of birth</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="dob"
                      value={userdata.dob}
                      type="date"
                      className="form-control"
                      placeholder="Enter birth date"
                      defaultValue=""
                    />
                  </div>
                </div>
                <div className="row mt-3">
                  <div className="col-md-12">
                    <label className="labels">Description</label>
                    {/* <input rows="12" cols="50"
              type="text"
              className="form-control" id="ps-description"
              placeholder="Add description"
              defaultValue=""
            /> */}
                    <textarea
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="description"
                      value={userdata.description}
                      className="form-control"
                      rows="5"
                      cols="60"
                      name="description"
                    >
                      Enter detailed description here...
                    </textarea>
                  </div>

                  <div className="col-md-12">
                    <label className="labels">Mobile Number</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="mobileNumber"
                      value={userdata.mobileNumber}
                      type="text"
                      className="form-control"
                      placeholder="Add Mob Number"
                      defaultValue=""
                    />
                  </div>
                  <div className="col-md-12">
                    <label className="labels">location</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="location"
                      value={userdata.location}
                      type="text"
                      className="form-control"
                      placeholder="enter city name"
                      defaultValue=""
                    />
                  </div>

                  {/* <div className="col-md-12">
            <label className="labels">Email ID</label>
            <input onChange={(e)=>{handleChange(e)}}
            id="email" value={userdata.email}
              type="text"
              className="form-control"
              placeholder="enter email id"
              defaultValue=""
            />
          </div> */}
                  <div className="col-md-12">
                    <label className="labels">Highest Education</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="higherEducation"
                      value={userdata.higherEducation}
                      type="text"
                      className="form-control"
                      placeholder="education"
                      defaultValue=""
                    />
                  </div>
                </div>
                <div className="row mt-3">
                  <div className="col-md-12" id="skill">
                    <div className=" py-5">
                      <div className="d-flex justify-content-between align-items-center experience">
                        <span>Add Skills</span>
                        {/* <span className="border px-3 p-1 add-experience" onClick={addSkill}>
            <i className="fa fa-plus" />
            &nbsp;
          </span> */}
                      </div>
                      <br />
                      <div className="col-md-12">
                        <label className="labels">Skills</label>
                        <input
                          onChange={(e) => {
                            handleChange(e);
                          }}
                          id="skillSet"
                          value={userdata.skillSet}
                          type="text"
                          className="form-control"
                          placeholder="example: html,css,bootstrap"
                          defaultValue=""
                        />
                      </div>{" "}
                      <br />
                      <div className="col-md-12">
                        <label className="labels">Upload Resume</label>
                        <input
                          onChange={(e) => {
                            handleChangeresume(e);
                          }}
                          id="resume"
                          type="file"
                          className="form-control"
                          placeholder="example: html,css,bootstrap"
                          defaultValue=""
                        />
                      </div>{" "}

                      <div className="col-md-12">
                        <label className="labels">change photo</label>
                        <input
                          onChange={(e) => {
                            handleChangephoto(e);
                          }}
                          id="profilePhoto"
                          type="file"
                          className="form-control"
                          placeholder="example: html,css,bootstrap"
                          defaultValue=""
                        />
                      </div>{" "}
                    </div>
                  </div>
                </div>
                <div className="mt-5 text-center">
                  <button
                    className="btn btn-primary profile-button"
                    type="submit"
                  >
                    Save Profile
                  </button>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="p-3 py-5">
                <div className="d-flex justify-content-between align-items-center experience">
                  <span>Edit Experience</span>
                  {/* <span onClick={onAddBtnClick} className="border px-3 p-1 add-experience">
            <i className="fa fa-plus" />
            &nbsp;Experience
          </span> */}
                </div>
                <br />
                <div className="col-md-12">
                  <label className="labels">Experience</label>
                  <input
                    onChange={(e) => {
                      handleChange(e);
                    }}
                    id="experience"
                    value={userdata.experience}
                    type="text"
                    className="form-control"
                    placeholder="ex: tcs,wipro,GL"
                    defaultValue=""
                  />
                </div>{" "}
                <br />
                <div className="col-md-6">
                  <label className="labels">Current Role</label>
                  <input
                    onChange={(e) => {
                      handleChange(e);
                    }}
                    id="currentRole"
                    value={userdata.currentRole}
                    type="text"
                    className="form-control"
                    placeholder="Role"
                    setResume
                    defaultValue=""
                  />
                  <div className="col-md-6">
                    <label className="labels">notice Period</label>
                    <input
                      onChange={(e) => {
                        handleChange(e);
                      }}
                      id="noticePeriod"
                      value={userdata.noticePeriod}
                      type="number"
                      min="0"
                      max="5"
                      className="form-control"
                      placeholder="In months"
                      defaultValue=""
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}
