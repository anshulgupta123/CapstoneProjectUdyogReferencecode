import "../../../node_modules/slick-carousel/slick/slick.css"
import "../../../node_modules/slick-carousel/slick/slick-theme.css"
import Slider from "react-slick";

import { useEffect, useState } from 'react';
import axios from 'axios'

export function CompanyCarousal() {
    const [data, setData] = useState([])
    let getdata = async () => {
        let raw = await axios.get("http://localhost:3001/jobPost")

        setData(raw.data)
    }
    useEffect(() => {
        getdata()
    }, [])
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        autoplay: true,
        autoplaySpeed: 2000,
        slidesToScroll: 1
    };
    return (
        <div>
            <h2 style={{ textAlign: 'center' }}>Top Companies hiring!!</h2>
            <Slider {...settings} style={{ marginTop: "2rem" }}>
                {data.map((item) => (
                    <div>
                        <div className="comp-card">
                            <img src={item.companylogo} className="logo-card"></img>
                            <span className="box-text">{item.company}</span>
                            <span className="box-text"><i class="fa-solid fa-star" style={{ color: "yellow" }}></i> Rating</span>
                            <button className="btn-carousal">View job</button>

                        </div>
                    </div>
                ))
                }

            </Slider>
        </div>
    )
}

export function Location() {

    return (
        <div className="middlediv">
            <div className="skillpic">
                
            </div>
            <div className="skilltitle">
                <h2>Skills</h2>
                <h2>In Trend</h2>
                
            </div>
            <div className="skillbox">
                <div className="skills">
                    <div>
                        <div className="skill-card">
                            <span className="box-text">UI/UX</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">Marketing</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">Data Analyst</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">HR Consultancy</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                   
                </div>
                <div className="skills">
                    <div>
                        <div className="skill-card">
                            <span className="box-text">UI/UX</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">Marketing</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">Data Analyst</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                    <div>
                        <div className="skill-card">
                            <span className="box-text">HR Consultancy</span>

                            <span className="rightArrow"><i class="fa-solid fa-right-long" style={{ color: '#f2f7ff' }}></i> </span>

                        </div>
                    </div>
                   
                </div>

            </div>
        </div >
    )
}