import AuthService from "./AuthService";

const API_getAllSeekerChat = AuthService.getServerAddress()+":8081/api/v1/getChatOfSeeker/"+AuthService.getUserEmail();

const API_getAllEmployerChat = AuthService.getServerAddress()+":8081/api/v1/getChatOfEmployer/"+AuthService.getUserEmail();

const API_getChat = AuthService.getServerAddress()+":8081/api/v1/chat/id/";

const API_PostNewChat = AuthService.getServerAddress()+":8081/api/v1/chatservicemodel";

const API_PostMessage = AuthService.getServerAddress()+":8081/api/v1/chat/";

class MessageService {
    getApiAllSeekerChat() {
        return API_getAllSeekerChat;
    }
    getApiAllEmployerChat(){
        return API_getAllEmployerChat;
    }
    getAPIgetChat(){
        
        return API_getChat;
    }
    getApiPostNewChat(){
        return API_PostNewChat;
    }
    getApiPostMessage(){
        return API_PostMessage;
    }
}
export default new MessageService();