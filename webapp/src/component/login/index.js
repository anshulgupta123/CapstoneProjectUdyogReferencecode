import React, { useEffect, useState } from "react";
import axios from "axios";
import './style.css'
import { useNavigate } from "react-router-dom";
import Modal from 'react-bootstrap/Modal';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { Form, Button, InputGroup, Alert } from "react-bootstrap";
import Footer from "../../Footer";
import AuthService from "../../services/AuthService";
import Dashboard from "../Dashboard";

export default function Login() {


  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const [tabval, setTabVal] = useState("login");
  const [errormsz, setErrorMsz] = useState("");
  const [loginValidated, setLoginValidated] = useState(false);
  const [signUpValidated, setSignupValidated] = useState(false);

  const [alert, setAlert] = useState({
    variant: '',
    title: '',
    text: '',
    show: false
  })

  function CloseAlert() {
    setAlert({
      variant: '',
      title: '',
      text: '',
      show: false,
      classDiv: ""
    })
  }

  function ShowAlert(type, titleval, msz, showalert) {
    setAlert({
      variant: type,
      title: titleval,
      text: msz,
      show: showalert,
      classDiv: "login-alert-overlap"
    })
    setTimeout(() => {
      CloseAlert()
    }, 2000);
  }

  const indianState = [
    {
      "key": "AN",
      "name": "Andaman and Nicobar Islands"
    },
    {
      "key": "AP",
      "name": "Andhra Pradesh"
    },
    {
      "key": "AR",
      "name": "Arunachal Pradesh"
    },
    {
      "key": "AS",
      "name": "Assam"
    },
    {
      "key": "BR",
      "name": "Bihar"
    },
    {
      "key": "CG",
      "name": "Chandigarh"
    },
    {
      "key": "CH",
      "name": "Chhattisgarh"
    },
    {
      "key": "DH",
      "name": "Dadra and Nagar Haveli"
    },
    {
      "key": "DD",
      "name": "Daman and Diu"
    },
    {
      "key": "DL",
      "name": "Delhi"
    },
    {
      "key": "GA",
      "name": "Goa"
    },
    {
      "key": "GJ",
      "name": "Gujarat"
    },
    {
      "key": "HR",
      "name": "Haryana"
    },
    {
      "key": "HP",
      "name": "Himachal Pradesh"
    },
    {
      "key": "JK",
      "name": "Jammu and Kashmir"
    },
    {
      "key": "JH",
      "name": "Jharkhand"
    },
    {
      "key": "KA",
      "name": "Karnataka"
    },
    {
      "key": "KL",
      "name": "Kerala"
    },
    {
      "key": "LD",
      "name": "Lakshadweep"
    },
    {
      "key": "MP",
      "name": "Madhya Pradesh"
    },
    {
      "key": "MH",
      "name": "Maharashtra"
    },
    {
      "key": "MN",
      "name": "Manipur"
    },
    {
      "key": "ML",
      "name": "Meghalaya"
    },
    {
      "key": "MZ",
      "name": "Mizoram"
    },
    {
      "key": "NL",
      "name": "Nagaland"
    },
    {
      "key": "OR",
      "name": "Odisha"
    },
    {
      "key": "PY",
      "name": "Puducherry"
    },
    {
      "key": "PB",
      "name": "Punjab"
    },
    {
      "key": "RJ",
      "name": "Rajasthan"
    },
    {
      "key": "SK",
      "name": "Sikkim"
    },
    {
      "key": "TN",
      "name": "Tamil Nadu"
    },
    {
      "key": "TS",
      "name": "Telangana"
    },
    {
      "key": "TR",
      "name": "Tripura"
    },
    {
      "key": "UK",
      "name": "Uttar Pradesh"
    },
    {
      "key": "UP",
      "name": "Uttarakhand"
    },
    {
      "key": "WB",
      "name": "West Bengal"
    }
  ];


  const handleOpenModal = () => setShow(true);
  const handleCloseModal = () => setShow(false);
  const handleSelectSignup = () => { setTabVal("signup"); }

  const handleSelectsLogin = () => setTabVal("login");


  const NavigateTo = (url) => {
    navigate(url, { replace: true });
  };

  const [loginDetails, setLoginDetails] = useState({
    email: "",
    password: "",
  });

  const [regDetails, setRegDetails] = useState({
    fname: "",
    lname: "",
    email: "",
    password: "",
    mobile: "",
    usertype: "",
    state: "",
    city: "",
    pincode: ""
  });

  const handleRegisterFName = (e) => {
    setRegDetails({ ...regDetails, fname: e.target.value });
  };
  const handleRegisterLName = (e) => {
    setRegDetails({ ...regDetails, lname: e.target.value });
  };
  const handleRegisterPassword = (e) => {
    setRegDetails({ ...regDetails, password: e.target.value });
  };
  const handleRegisterEmail = (e) => {
    setRegDetails({ ...regDetails, email: e.target.value });
  };

  const handleRegisterMobile = (e) => {
    setRegDetails({ ...regDetails, mobile: e.target.value });
  };

  const handleRegisterState = (e) => {
    setRegDetails({ ...regDetails, state: e.target.value });
  };

  const handleRegisterCity = (e) => {
    setRegDetails({ ...regDetails, city: e.target.value });
  };
  const handleRegisterPincode = (e) => {
    setRegDetails({ ...regDetails, pincode: e.target.value });
  };

  const handleRegisterUserType = (e) => {
    setRegDetails({ ...regDetails, usertype: e.target.value == 1 ? "jobseeker" : "employer" });
  };

  const handleChangeEmail = (e) => {
    setLoginDetails({ ...loginDetails, email: e.target.value });
  };
  const handleChangePassword = (e) => {
    setLoginDetails({ ...loginDetails, password: e.target.value });
  };

  const handleLoginRequest = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }
    else {
      axios
        .post(AuthService.getLoginApi(), loginDetails)
        .then((response) => {
          if (response.data.token) {
            AuthService.login(response.data)
            ShowAlert("success", "Info", "Login Successfully");
            handleCloseModal();
            window.location.href = "/home";
          }
          else {
            AuthService.reset();
            ShowAlert("danger", "Alert", "Invalid username / password");
          }
          return response.data;
        }).catch(() => {
          AuthService.reset();
          ShowAlert("danger", "Alert", "Invalid username / password");
        });
    }
    setLoginValidated(true);
  };

  const handleSignupRequest = (event) => {
    event.preventDefault();
    let fullname = regDetails.fname + " " + regDetails.lname;

    // let reguser = {
    //   name: fullname.trim(),
    //   email: regDetails.email,
    //   mobile: regDetails.mobile,
    //   usertype: regDetails.usertype,
    //   state: regDetails.state,
    //   city: regDetails.city,
    //   pincode: regDetails.pincode
    // };


    let reguser = {
      name: fullname.trim(),
      email: regDetails.email,
      mobileNumber: regDetails.mobile,
      location: regDetails.city,
      password: regDetails.password
    };

    var regApi = (regDetails.usertype === "employer" ? AuthService.getEmployerRegAPI() : ((regDetails.usertype === "jobseeker" ? AuthService.getSeekerRegAPI() : "")))

    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }
    else {
      axios({
        method: "post",
        url: regApi,
        data: reguser,
        headers: { "Content-Type": "multipart/form-data" },
      }).then((response) => {
          console.log(response);
          if (response.data.code === "0") {
            ShowAlert("success", "Info", response.data.description);
            handleCloseModal();
            setRegDetails({
              fname: "",
              lname: "",
              email: "",
              password: "",
              mobile: "",
              usertype: "",
              state: "",
              city: "",
              pincode: ""
            });
          }
          else {
            ShowAlert("danger", "Alert", "Something goes wrong. Please try again later.");
            handleCloseModal();
          }
        }).catch((err) => {
          ShowAlert("danger", "Alert",err.response.data.data.errorDetails);
        });
    }
    setSignupValidated(true);
  };

  return (
    <>
      <div className={alert.classDiv + " d-flex justify-content-center"}>
        {/* <Alert show={alert.show} variant={alert.variant} onClose={() => CloseAlert()} className="login-alert" dismissible> */}
        <Alert show={alert.show} variant={alert.variant} className="login-alert">
          <Alert.Heading>{alert.title}</Alert.Heading>
          <p>
            {alert.text}
          </p>
        </Alert>
      </div>
      <div className="custom-shape-divider-top-1663144222">
        <img src="./images/udoyg-logo.png" className="img-fluid login-cmp-logo" />
        <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
          <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" className="shape-fill"></path>
        </svg>
      </div>
      <div className="container col-md-12 px-4 py-5 mt-4">
        <div className="row flex-lg-row-reverse align-items-center g-5 py-5">
          <div className="col-10 col-sm-8 col-lg-6">
            <img src="/images/landing-p-img1.png" className="d-block mx-lg-auto img-fluid" alt="Udyog Job Portal" loading="lazy" />
          </div>
          <div className="col-lg-6">
            <h1 className="display-5 fw-bold lh-1 mb-3">Udyog Job Portal</h1>
            <p className="lead">Technology has changed the way job seekers search for jobs and employers find qualified employees. While employers still advertise job openings through traditional advertising mediums, such as local newspapers and magazines, today employers and job seekers turn to online job portals to find employment matches. Job seekers can advertise their skills and search for available positions, and employers can announce employment openings through our portal<b> Udyog</b></p>
            <div className="d-grid gap-2 d-md-flex justify-content-md-start">
              <Button className="btn btn-primary btn-lg px-4 me-md-2" onClick={handleOpenModal}>Login / Sign Up</Button>
            </div>
          </div>
        </div>
      </div>
      <Modal show={show} onHide={handleCloseModal} fullscreen={true} >
        <Modal.Body className="p-0 overflow-hidden">
          <div className="row justify-content-evenly h-100 loginregfrmModal">
            <div className="col-md-8 d-flex">
              <img alt="" src="/images/login-page-img1.png" className="img-fluid m-auto" />
            </div>
            <div className="col-md-4 text-white loginregfrm h-100 ">
              <div className='row'>
                <div className="col-sm-12">
                  <div className="p-3 float-end">
                    <button type="button" className="btn text-white" onClick={handleCloseModal}>X</button>
                  </div>
                </div>
              </div>
              <Tabs
                defaultActiveKey="login"
                className="mb-3 loginfrmtab "
                justify
                onSelect={(k) => setTabVal(k)}
                activeKey={tabval}
              >
                <Tab eventKey="login" title="Login" >
                  <Form noValidate validated={loginValidated} autoComplete="off" className="row align-items-center p-4" onSubmit={handleLoginRequest}>
                    <div className="col-md-12 d-flex justify-content-center mt-2">
                      <h3>Login Form</h3>
                    </div>
                    <Form.Group md="4" className="mt-4 mb-4">
                      <InputGroup hasValidation>
                        <InputGroup.Text ><i className="fa-solid fa-user"></i></InputGroup.Text>
                        <Form.Control
                          type="text"
                          placeholder="Username"
                          required
                          onChange={handleChangeEmail}
                          value={loginDetails.email}
                          pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,5})+$"
                          title="Please enter valid email-id"
                        />
                      </InputGroup>
                    </Form.Group>
                    <Form.Group md="4" className="mb-4">
                      <InputGroup hasValidation>
                        <InputGroup.Text><i className="fa-solid fa-key"></i></InputGroup.Text>
                        <Form.Control
                          type="password"
                          placeholder="Password"
                          required
                          onChange={handleChangePassword}
                          value={loginDetails.password}
                          //pattern='^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$'
                          title="Please enter at valid password"
                        />
                      </InputGroup>
                    </Form.Group>
                    <div className="col-sm-12">
                      <div className="mb-4 d-grid">
                        <button type="submit" className="btn btn-primary">Login</button>
                      </div>
                      <div className="mt-4 text-center">
                        <a className="loginredirectlink">Forgot Password ?</a><hr />
                        <a className="loginredirectlink" onClick={handleSelectSignup}>Don't have an account? Sign up and get started!</a>
                      </div>
                    </div>
                  </Form>
                </Tab>
                <Tab eventKey="signup" title="Register">
                  <Form noValidate validated={signUpValidated} className="row align-items-center p-4" onSubmit={handleSignupRequest}>
                    <div className="col-md-12 d-flex justify-content-center mt-2">
                      <h3>Registration</h3>
                    </div>
                    <Form.Group md="4" className="mt-4 col-md-6 col-sm-12">
                      <InputGroup hasValidation>
                        <Form.Control
                          type="text"
                          placeholder="First Name"
                          required
                          onChange={handleRegisterFName}
                          value={regDetails.fname}
                          pattern='[A-Za-z]{3,}'
                          title="Minimum 3 characters are requrired"
                        />
                      </InputGroup>
                    </Form.Group>
                    <Form.Group md="4" className="mt-4 col-md-6 col-sm-12">
                      <InputGroup hasValidation>
                        <Form.Control
                          type="text"
                          placeholder="Last Name (optional)"
                          onChange={handleRegisterLName}
                          value={regDetails.lname}
                        />
                      </InputGroup>
                    </Form.Group>
                    <Form.Group md="4" className="mt-4 col-md-12 col-sm-12">
                      <InputGroup hasValidation>
                        <Form.Control
                          type="email"
                          placeholder="Email Address"
                          required
                          onChange={handleRegisterEmail}
                          value={regDetails.email}
                          pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,5})+$"
                          title="Please enter valid email-id"
                        />
                      </InputGroup>
                    </Form.Group>
                    <Form.Group md="4" className="mt-4 col-md-12 col-sm-12">
                      <InputGroup hasValidation>
                        <Form.Control
                          type="password"
                          placeholder="Password"
                          required
                          onChange={handleRegisterPassword}
                          value={regDetails.password}
                          pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                          title='Password should be minimum 8 character and should contain atleast one : small letter, capital letter, number & any speical character'
                        />
                      </InputGroup>
                    </Form.Group>

                    <Form.Group md="4" className="mt-4 mb-4 col-md-12 col-sm-12">
                      <InputGroup hasValidation>
                        <InputGroup.Text >+91-</InputGroup.Text>
                        <Form.Control
                          type="text"
                          placeholder="Mobile No."
                          required
                          onChange={handleRegisterMobile}
                          value={regDetails.mobile}
                          className="removenumberarror"
                          pattern='[0-9]{10}'
                          title="Please enter valid 10 digit mobile no."
                        />
                      </InputGroup>
                    </Form.Group>
                    <div className="col-md-12 mb-4">
                      <select className="form-select" defaultValue={""} onChange={handleRegisterUserType} required>
                        <option value={""} disabled>You Are :</option>
                        <option value="1">Job Seekeer</option>
                        <option value="2">Employer</option>
                      </select>
                    </div>
                    <div className="col-md-5 mb-4">
                      <div className="input-group">
                        <select className="form-select" defaultValue={""} onChange={handleRegisterState} required>
                          <option value={""} disabled>State</option>
                          {indianState.map(({ key, name }, index) => <option value={key} >{name}</option>)}
                        </select>
                      </div>
                    </div>
                    <div className="col-md-4 mb-4">
                      <div className="input-group">
                        <input type="text" className="form-control" placeholder="City"
                          value={regDetails.city} onChange={handleRegisterCity} required pattern='[A-Za-z]{3,}' title="Please enter valid city name" />
                      </div>
                    </div>
                    <div className="col-md-3 mb-4">
                      <input type="text" className="form-control removenumberarror" placeholder="Pincode" value={regDetails.pincode}
                        onChange={handleRegisterPincode} required pattern='[0-9]{6}' title="Please enter valid pincode" />
                    </div>
                    <div className="col-md-12 ms-auto mb-4">
                      <input type="submit" value="Register" className="form-control btn btn-primary" />
                    </div>
                    <div className="text-center pe-auto">
                      <a className="loginredirectlink" onClick={handleSelectsLogin}>Already Have an Account ? Sign-In </a>
                    </div>
                  </Form>
                </Tab>
              </Tabs>
              <span className="loginfrmerror">{errormsz}</span>
            </div>
          </div>
        </Modal.Body>
      </Modal>
    </>
  )
}