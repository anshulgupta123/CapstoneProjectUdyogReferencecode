import AuthService from "./AuthService";

const API_getAllJobs = AuthService.getServerAddress()+":8085/v1/getAllJobPostings";

const API_ApplyJob = AuthService.getServerAddress()+":8085/v1/jobApplicationCreation";

const API_AppliedJob = AuthService.getServerAddress()+":8085/v1/getAllJobApplications?searchParam"

const API_AppliedJob_Bymail = AuthService.getServerAddress()+":8085/v1/getJobApplicationByEmail/"+AuthService.getUserEmail();

const API_PostJob = AuthService.getServerAddress()+":8085/v1/jobPosting"

class JobService {
    getApiForAllJob() {
        return API_getAllJobs;
    }
    getApiApplyJob(){
        return API_ApplyJob;
    }
    getAPIAppliedJobs(){
        
        return API_AppliedJob;
    }
    getApiPostJob(){
        return API_PostJob;
    }
    getApiAppliedJobsEmail(){
        return API_AppliedJob_Bymail;
    }
}
export default new JobService();