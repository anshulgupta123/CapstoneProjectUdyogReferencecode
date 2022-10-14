import React, {Component} from "react";
import axios from "axios";
import "./Style.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthService from "../../services/AuthService";
import JobService from "../../services/JobService";
class Index2 extends Component {
  constructor(props) {
    super(props);
    this.state = {
    
      position: "",
      skillSet: [],
      jobPackage: "",
      expiredDate: "",
      experiencedRequired: "",
      expectedDateOfJoining: "",
      noOfEmployeesRequired: "",
      description: "",
      location: "",
      company: "",
      companyUrl: "",
      companyLogo:null,
      employerEmail: "",
     

    };
  }
 
  changeHandle14=event=>{
this.setState({
  companyLogo:event.target.files[0]
})
  }
  changeHandler = (event) => {
    this.setState({
      [event.target.className]: event.target.value1,
    });
  };
  changeHandle2 = (event) => {
    this.setState({
      position: event.target.value,
    });
  };
  changeHandle3 = (event) => {
    this.setState({
      skillSet: (event.target.value),
    });
  };
  changeHandle4 = (event) => {
    this.setState({
      jobPackage: (event.target.value),
    });
  };
  changeHandle5 = (event) => {
    this.setState({
      expiredDate: (event.target.value),
    });
  };

  changeHandle6 = (event) => {
    this.setState({
      experiencedRequired: (event.target.value),
    });
  };
    changeHandle7 = (event) => {
    this.setState({
      expectedDateOfJoining: (event.target.value),
    });
  };
   changeHandle8 = (event) => {
    this.setState({
      noOfEmployeesRequired: (event.target.value),
    });
  };
  changeHandle10 = (event) => {
    this.setState({
      description: (event.target.value),
    });
};
 changeHandle11 = (event) => {
    this.setState({
      location: (event.target.value),
    });
};
 changeHandle12 = (event) => {
    this.setState({
      company: (event.target.value),
    });
};
 changeHandle13 = (event) => {
    this.setState({
      companyUrl: (event.target.value),
    });
};

changeHandle15 =(event) => {
    this.setState({
       employerEmail:(event.target.value) 
    })
};
SubmitHand = (event) => {
    event.preventDefault();
    let postdatavalues = {
        employerEmail : AuthService.getUserEmail(),
        expiredDate:new Date(this.state.expiredDate).getTime() /1000,
        expectedDateOfJoining:new Date(this.state.expectedDateOfJoining).getTime() / 1000,
        position : this.state.position,
        skillSet : this.state.skillSet,
        jobPackage:this.state. jobPackage,
        experiencedRequired:this.state.experiencedRequired,
        noOfEmployeesRequired:this.state.noOfEmployeesRequired,
        description:this.state.description,
        location:this.state.location,
        company:this.state.company,
        companyLogo:this.state.companyLogo,
        companyUrl:this.state.companyUrl
    }
 
                console.log(postdatavalues)
            
    axios({
      method: "post",
      url: JobService.getApiPostJob(),
      data: postdatavalues,
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then((response) => {
        console.log(response);
        if (response.data.code === "0") {
          
          alert("successfully posted");
        }
         else {
          alert("Something goes wrong. Please try again later.");
        }
      })
      .catch((err) => {
        alert(err.response.data.data.errDetails);   
      });
}
  //  fileUploadHandler =() =>{
  //   const fd=new FormData();
  //   fd.append('image',this.state.selectedFile,this.state.selectedFile.name);
  //   axios.post(JobService.getApiPostJob(),fd)
  //   .then(res=>{
  //     console.log(res);
  //   })
  //  }
render(){
    return (
      <>
        <div class="container mt-4">
          <h1 className="Head">welcome to Job Application Page</h1>
        </div>
        <div class="container mt-5" id="i-m-l">
          <form onSubmit={this.SubmitHand}>
            <span id="jp-i2-2">*</span>
            <lable>Position:</lable>
            <div>
              <textarea type="text" className="jp-i2-email" value={this.state.position} onChange={this.changeHandle2} onChange1={this.changeHandler} placeholder="position" required />
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Skill set:</lable>
            <div>
              <textarea type="text" className="jp-i2-email" value={this.state.skillSet} onChange={this.changeHandle3} placeholder="Skill set" required />
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Job Pakage:</lable>
            <div>
              <textarea  type="number" className="jp-i2-email" value={this.state.jobPackage} onChange={this.changeHandle4} placeholder="Job Package" required />
            </div>
            <p id="jp-i2-jobPackage"></p>
            <span id="jp-i2-2">*</span>
            <lable>Expired Date:</lable>
            <div>
              <input type="date" className="jp-i2-email" onChange={this.changeHandle5} placeholder="Expired Date" required/>
            </div>
            <span id="jp-i2-2">*</span>
            <lable> Experienced Required:</lable>
            <div>
              <textarea  type="number" className="jp-i2-email" value={this.state.experiencedRequired} onChange={this.changeHandle6} placeholder=" Experienced Required" required/>
              
            </div>
            <p id="jp-i2- experienceRequired"></p>
            <span id="jp-i2-2">*</span>
            <lable>Expected Date Of Joining:</lable>
            <div>
              <input type="date" className="jp-i2-email" onChange={this.changeHandle7} placeholder="Expected DateOfJoining " required />
            </div>
            <p id="jp-i2-expectedDateOfJoining"></p>
            <span id="jp-i2-2">*</span>
            <lable>No Of Employees Required:</lable>
            <div>
              <textarea  type="number" className="jp-i2-email" value={this.state.noOfEmployeesRequired} onChange={this.changeHandle8} placeholder="No  OfEmployees Required " required />
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Description :</lable>
            <div>
              <textarea  type="text" className="jp-i2-D" value={this.state.description} onChange={this.changeHandle10} placeholder="Description " required/>
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Location :</lable>
            <div>
              <textarea type="text"className="jp-i2-email"value={this.state.location}onChange={this.changeHandle11}placeholder="Location "required />
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Company :</lable>
            <div>
              <textarea  type="text" className="jp-i2-email" value={this.state.company} onChange={this.changeHandle12} placeholder="Company" required />
            </div>
            <span id="jp-i2-2">*</span>
            <lable>Company Url :</lable>
            <div>
              <textarea  type="text" className="jp-i2-email" value={this.state.companyUrl} onChange={this.changeHandle13} placeholder="Company Url" required />
            </div>           
            <span id="jp-i2-2">*</span>
            <lable>Employer Email :</lable>
            <div>
              <input type="text" className="jp-i2-email" value={AuthService.getUserEmail()} required />
            </div>
<input type="file"   onChange={this.changeHandle14} required/>
            {/* <input type="file"
       id="avatar" 
       accept="image/png, image/jpeg"/> */}

            {/* <span id="jp-i2-14">*</span>

            <lable>CompanyLogo :</lable>
            <div>
              <input
                type="text"
                className="jp-i2-email"
                value={this.state.companyLogo}
                onChange={this.changeHandle14}
                placeholder="CompanyLogo"
              />
            </div> */}
            <p id="jp-i2-companyLogo"></p>
            <div class="container mt-4" id="pbn">
              <div class="row">
                <div class="col-md-12">
                    <button class="btn btn-primary me-md-2"type="submit"  value="postJob" onClick={this.fileSelectedHandler} id="bt-2" >{" "}PostJob</button>
                </div>
              </div>
            </div>
          </form>
          
        </div>
        <div class="container mt-5">
          <div>
            <div></div>
          </div>
        </div>
      </>
    );
}
}
export default Index2;
