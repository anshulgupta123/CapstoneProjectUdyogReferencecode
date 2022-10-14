import axios from 'axios'
import React, { useEffect, useState } from 'react'
import AuthService from '../../services/AuthService'
import MessageService from '../../services/MessageService'

import './App.css'
import Chathistory from './Chathistory'


function Messaging() {
const [chatGroupSeeker,setChatGroupSeeker]=useState([])
const [chatGroupEmp,setChatGroupEmp]=useState([])
const [id,setId]=useState('')
const [sender,setSender]=useState('')
const [receiver,setReceiver]=useState('')
//var id2= localStorage.getItem(id);

//console.log(id2)
// const [message,setMessage]=useState([])
const [inputmail,setInputmail]=useState('')
const updateMail = (Event) =>{setInputmail(Event.target.value);
} 
let getdataofSeeker = async () => {
  let raw = await axios.get(MessageService.getApiAllSeekerChat()) 
setChatGroupSeeker(raw.data)
}
let getdataofEmp = async () => {
  let raw = await axios.get(MessageService.getApiAllEmployerChat())
setChatGroupEmp(raw.data)
}
  
useEffect(() => {
  getdataofEmp();
  getdataofSeeker();
 
}, []);
useEffect(() => {
  setId(localStorage.getItem("id"))
},[]);
 useEffect(()=>{
  setSender(JSON.parse(localStorage.getItem("sender")))
 })
 useEffect(()=>{
  setReceiver(JSON.parse(localStorage.getItem("receiver")))
 })
// useEffect(() => {
//   axios.get("http://localhost:3001/Messages").then(res=>{setMessage(res.data)})
// }, [])
return (
  <div className='mainDiv1'>
  <div className='mainMsgDiv'>
 
     {(AuthService.isEmployer()===true) ? (chatGroupEmp.map((chatGroupEmp)=>{
      return(
        <>
       
    <div className='chatDiv'>
      <button onClick={() =>(pushId(chatGroupEmp.id,chatGroupEmp.employerEmail,chatGroupEmp.seekerEmail))}><p className='chatDivTab'>{(chatGroupEmp.seekerEmail).slice(0,-10)}</p></button>
    </div>
   
    </>)
  })): (chatGroupSeeker.map((chatGroupSeeker)=>{
    return(
      <>
     
  <div className='chatDiv'>
    <button onClick={() =>(pushId(chatGroupSeeker.id,chatGroupSeeker.seekerEmail,chatGroupSeeker.employerEmail))}><p className='chatDivTab'>{(chatGroupSeeker.employerEmail).slice(0,-10)}</p></button>
  </div>
 
  </>)
}))}
    <div className='addUser'>
          <input type='text' onChange={updateMail}  value={inputmail} placeholder='search'className='searchUser'></input><button className='newChat' onClick={()=>(newChat({inputmail}))}>+</button>
        </div>
    </div>
    <div>
  <Chathistory id={id} sender={sender} receiver={receiver}/> 
    </div>
    </div>
    )
    function pushId(id,sender,receiver){
      var id2=localStorage.setItem("id",JSON.stringify(id))
      id=localStorage.getItem("id")
     var sender=localStorage.setItem("sender",JSON.stringify(sender))
     var receiver=localStorage.setItem("receiver",JSON.stringify(receiver))
      console.log(id,sender,receiver);
      window.location.reload(true);
     }
     function newChat(inputmail){
      //var mail = {input};
      console.log(inputmail.inputmail)
      {(AuthService.isEmployer()===true)? ( axios({
        method: 'post',
        url: MessageService.getApiPostNewChat(),
        data: {
          seekerEmail : inputmail.inputmail,
          employerEmail : AuthService.getUserEmail()
        }
    })):( axios({
      method: 'post',
      url: MessageService.getApiPostNewChat(),
      data: {
        seekerEmail : AuthService.getUserEmail(),
        employerEmail : inputmail.inputmail
      }
  }))} 
  window.location.reload(true);
     }
}

export default Messaging