import axios from "axios";
import AuthService from "./AuthService";

const API_CmpRating=AuthService.getServerAddress()+":8084/companyRating";
const API_CompanyComment=AuthService.getServerAddress()+":8084/comment";
const API_CompanyDetails=AuthService.getServerAddress()+":8085/v1/getCompanyByName";

class RatingService{

    getRatingAPI() {
        return API_CmpRating;
    
    }
    getCommentAPI() {
        return API_CompanyComment;
    }
    getCompanyDetailsAPI(){
        return API_CompanyDetails;
    }

    getCompanyRating(cmp)
    {
        axios
            .get(RatingService.getRatingAPI()+"/"+cmp)
            .then((response) => {
                return(response.data);
            })
            .catch((error) => {
                return "";
            });
    }
}


export default new RatingService();