import React, { Component } from "react";
import Slider from "react-slick";

 export default class AutoPlay extends Component {
        render() {
          const settings = {
            className: "slider variable-width",
            dots: true,
            infinite: true,
            centerMode: true,
            slidesToShow: 1,
            slidesToScroll: 1,
            variableWidth: true,
            autoplay: true,
            speed: 2000,
            autoplaySpeed: 2000,
    };
    return (
      <div>
        <Slider {...settings}>
                <div className='compCard'>
                    <img src="https://res.cloudinary.com/crunchbase-production/image/upload/c_lpad,f_auto,q_auto:eco,dpr_1/bmpcglw3rxipjxnctyan" className='clogo img-fluid'></img>
                    {/* <p className='cname'>GlobalLogic</p> */}
                </div>
            <div className='compCard'>
                <img src='https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Hitachi_logo_large.gif/640px-Hitachi_logo_large.gif' className='clogo img-fluid'></img>
                {/* <p className='cname'>HITACHI</p> */}
            </div>
            <div className='compCard'>
                <img src='https://media.glassdoor.com/sqll/13461/tata-consultancy-services-squareLogo-1634801936679.png' className='clogo img-fluid'></img>
                {/* <p className='cname'>TCS</p> */}
            </div>
            <div className='compCard'>
                <img src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/1200px-Google_%22G%22_Logo.svg.png' className='clogo img-fluid'></img>
                {/* <p className='cname'>Google</p> */}
            </div>
            <div className='compCard'>
                <img src="https://i.pinimg.com/originals/01/ca/da/01cada77a0a7d326d85b7969fe26a728.jpg" className='clogo img-fluid'></img>
                {/* <p className='cname'>Amazon</p> */}
            </div>
            <div className='compCard'>
                <img src='https://s3.amazonaws.com/www-inside-design/uploads/2018/04/walmart-square.jpg' className='clogo img-fluid'></img>
                {/* <p className='cname'>walmart</p> */}
            </div>
            <div className='compCard'>
                <img src='https://logosandtypes.com/wp-content/uploads/2022/03/Cognizant.png' className='clogo img-fluid'></img>
                {/* <p className='cname'>Cognizant</p> */}
            </div>
            <div className='compCard'>
                <img src='https://equitybulls.com/equitybullsadmin/uploads/HCL%20Technologies%20Limited.jpg' className='clogo img-fluid'></img>
                {/* <p className='cname'>HCL</p> */}
            </div>
            <div className='compCard'>
                <img src='http://luminalearning.com/wp-content/uploads/2018/11/tesco-logo.png' className='clogo img-fluid'></img>
                {/* <p className='cname'>Tesco</p> */}
            </div>
            <div className='compCard'>
                <img src='https://www.accenture.com/_acnmedia/Thought-Leadership-Assets/Images/mainpage/Accenture-acn-mobile-logo-2' className='clogo img-fluid'></img>
                {/* <p className='cname'>accenture</p> */}
            </div>
        </Slider>
      </div>
    );
  }
}