import jwt_decode from "jwt-decode";

const serveraddress = isLocalServer()?"http://localhost:8080/":"https://udyog.stackroute.io/";
const API_Login = serveraddress+"authservice/api/v1/login";
const API_SeekerReg = serveraddress+"userservice/v1/seekerRegistration";
const API_EmploerReg = serveraddress+"userservice/v1/employerRegistration";


function isLocalServer(){
    if (window.location.hostname === "localhost" || window.location.hostname === "127.0.0.1" || window.location.hostname === "") {
        return true;
    }
    return false;
}
class AuthService {

    // {
    //     "userType": "seeker",
    //     "message": "Authentication Successful",
    //     "email": "anil5@gmail.com",
    //     "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJVRFlPRyIsInN1YiI6ImFuaWw1QGdtYWlsLmNvbSIsImlhdCI6MTY2NDAyOTk2OCwidXNlclR5cGUiOiJzZWVrZXIifQ.RkKOijfHkHeKPCoG_f9lArQJ85BhNozMdEByTgr6orA"
    // }

    getUser() {
        try {
            let user = localStorage.getItem("user");
            if (user !== undefined && user !== null) {
                return user;
            }
        } catch {
            return "";
        }
        return "";
    }
    isLocalServer(){
        if (window.location.hostname === "localhost" || window.location.hostname === "127.0.0.1" || window.location.hostname === "") {
            return true;
        }
        return false
    }
    getServerAddress(){
        return serveraddress;
    }
    login(user) {
        try {
            localStorage.setItem("user", JSON.stringify(user));
        } catch {
            localStorage.removeItem("user");
        }
    }

    getToken() {
        try {
            return JSON.parse(localStorage.getItem("user")).token;
        }
        catch {
            return "";
        }
    }

    decodeToken() {
        try {
            return jwt_decode(this.getToken());
        }
        catch {
            return "";
        }
    }

    getUserEmail() {
        try {
            var user = this.decodeToken();
            return user.sub;
        } catch {
            return "";
        }
    }

    getLoginTime() {
        try {
            var user = this.decodeToken();
            return user.iat;
        } catch {
            return "";
        }
    }

    getUserType() {
        try {
            var user = this.decodeToken();
            return user.userType;
        } catch {
            return "";
        }

    }

    isEmployer() {
        try {
            var user = this.decodeToken();
            return user.userType == "employer" ? true : false;;
        } catch {
            return false;
        }
    }

    isJobSeeker() {
        try {
            var user = this.decodeToken();
            return user.userType == "seeker" ? true : false;;
        } catch {
            return false;
        }
    }

    isLogedIn() {
        try {
            return this.getToken() !== "" ? true : false;
        }
        catch {
            return false;
        }
    }

    getLoginApi() {
        return API_Login;
    }

    logout() {
        localStorage.removeItem("user");
    }

    reset() {
        localStorage.removeItem("user");
    }

    getSeekerRegAPI() {
        return API_SeekerReg;
    }

    getEmployerRegAPI() {
        return API_EmploerReg;
    }
}

export default new AuthService();