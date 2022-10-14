import React,{useEffect,useState} from 'react'
import axios from 'axios'
import MessageService from '../../services/MessageService'
import './App.css'
import AuthService from '../../services/AuthService';

function Chathistory(props) {
 console.log(`${props.id}`);
        const [chathistory,setChat]=useState([]);
        const [id,setId]=useState('');

        useEffect(()=>{
          setId(JSON.parse(localStorage.getItem("id")))
        })
       // var id=JSON.parse(localStorage.getItem("id"));
      // // const [message,setMessage]=useState([])
      const [input,setInput]=useState('')
    console.log(MessageService.getAPIgetChat()+id)
      // let getdata = async () => {
      //   let raw = await axios.get("http://localhost:3001/chatGroup/"+id)
        
        
      // setChat(raw.data)
      // }
      const updateText = (Event) =>{setInput(Event.target.value);
      } 
        // useEffect(()=>{
        //     updateText()
        // },[])
      // useEffect(() => {
      //   getdata()
      // }, [])
    useEffect(()=>{
        axios.get(MessageService.getAPIgetChat()+id)
        .then((res)=>{setChat(res.data)}).catch(err=>console.log(err));
    },[id])
   
      return(
        <>
        <div>
        <div className='msgDisplay'>
      
                {/* <div>
       
            {(chathistory.message?.map((message)=>{return(
              <div>
                <h4 className='msgSender'>{message.sender}</h4>
                <span className='mText'>{message.content}</span>
              </div>
            )}))}
            
         </div> */}
         
      
      <div>

  {(chathistory.message?.map((message)=>{return(
    <div className={message.sender === (AuthService.getUserEmail()).slice(0,-10)?"mRight":"mleft"}>
      <h4 className='msgSender '>{message.sender}</h4>
      <span className={message.sender === (AuthService.getUserEmail()).slice(0,-10)?"tRight":"tLeft"}>{message.content}</span>
    </div>
  )}))}
  
</div>
        {/* // {chathistory.filter(chathistory => (chathistory.chatID == props.chatID))
        //     .map(filteredhistory => (<div>return(<div>{filteredhistory.messages.map((messages)=>{return(<p>{messages.msgcontent}</p>)})}</div>)</div>)) 
        //     } */}
           </div>
           <div className='msgEnter'>
             {/* <input typeof='text'  className='msgText' placeholder='Enter text....'></input>
            <button className="msgSubmit" >Submit</button> */}
            <input typeof='text' onChange={updateText}  value={input} className='msgText' placeholder='Enter text....'></input>
            <button className="msgSubmit" onClick={()=>display({input})}>Submit</button>
           </div> 
           </div>
        </>
      )
        function display(input) {
          axios.get("localhost:8765/v1/getSeeker/"+`${props.sender}`).then((res)=>console.log(res.data));
            console.log(input);
            console.log(`${props.sender}`)
            const sender=`${props.sender}`
            const sender1=sender.slice(0,-10);
            console.log(`${props.receiver}`)
            
            const msg = {
              sender : sender1,
              receiver : `${props.receiver}`,
              message : `${input.input}`
            }
            console.log(msg);
         axios({
              method: 'put',
              url: MessageService.getApiPostMessage()+id,
              data: {
                sender : sender1,
                receiver : `${props.receiver}`,
                content : `${input.input}`
              }
          });
          window.location.reload(true);
            // axios.put(MessageService.getApiPostMessage()+`${props.id}`,msg);
        }
}

export default Chathistory