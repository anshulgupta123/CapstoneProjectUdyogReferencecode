


import Footer from "../../Footer";
import "./App.css"
// import "../../../node_modules/slick-carousel/slick/slick.css"
// import "../../../node_modules/slick-carousel/slick/slick-theme.css"



import { CompanyCarousal, Location } from "./Carousals";

export default function Home() {


    return (
        <>
            <div className='top-style'>
                <div className='left-style'>
                    <span className="first-heading">Search jobs</span>
                    <span className="first-heading">Which you prefer.</span>
                    <div style={{ marginTop: '10px' }}>
                        <input type="text" placeholder='Search jobs' className='top-style-search'></input>
                    </div>
                </div>
                {/* <div className='mainImg'>

                </div> */}
                <div>
                    <img src="https://images.pexels.com/photos/927451/pexels-photo-927451.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" className="mainImg" />
                </div>
            </div>
            <div class="custom-shape-divider-up">
                {/* <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                    <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
                    <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
                    <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
                    <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" className="shape-fill"></path>
                </svg> */}
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#0B409C" fill-opacity="1" d="M0,32L48,32C96,32,192,32,288,32C384,32,480,32,576,58.7C672,85,768,139,864,144C960,149,1056,107,1152,90.7C1248,75,1344,85,1392,90.7L1440,96L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path></svg>
            </div>

            <div className="middle-content">
                <div className="company-carousal">
                    <CompanyCarousal />
                </div>
                <div className="location-carousal">
                    <Location />
                </div>
            </div>
            <div className="quotesContainer d-flex align-items-center">
                <div className="container d-flex justify-content-center ">
                    <div id="carouselExampleIndicators" className="carousel slide" data-bs-ride="carousel">
                        <div className="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div className="carousel-inner carousel-inner-m ">
                            <div className="carousel-item active">
                                <div className="quotesCarousal">
                                    <img src="https://i.pinimg.com/236x/95/a0/25/95a0252fdac133a48edf2821c864401a--se%C3%B1ora-mary-mary-kay-ash.jpg" className="quotesImg" alt="..." />
                                    <span className="quotesText">"Don't limit yourself. Many people limit themselves to what they think they can do. You can go as far as your mind lets you. What you believe you can achieve."</span>
                                </div>
                            </div>
                            <div className="carousel-item ">
                                <div className="quotesCarousal">
                                    <img src="https://upload.wikimedia.org/wikipedia/commons/7/7a/Mahatma-Gandhi%2C_studio%2C_1931.jpg" className="quotesImg" alt="..." />
                                    <span className="quotesText">"The future depends on what you do today"</span>
                                </div>
                            </div>
                            <div className="carousel-item" >
                                <div className="quotesCarousal">
                                    <img src="https://imageio.forbes.com/specials-images/imageserve/5b8576db31358e0429c734e3/0x0.jpg?format=jpg&crop=2170,2172,x211,y900,safe&height=416&width=416&fit=bounds" className="quotesImg" alt="..." />
                                    <span className="quotesText">"The only way to do great work is to love what you do. If you haven’t found it yet, keep looking. Don’t settle. "</span>
                                </div>
                            </div>
                        </div>
                        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                            <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span className="visually-hidden">Previous</span>
                        </button>
                        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                            <span className="carousel-control-next-icon" aria-hidden="true"></span>
                            <span className="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </>
    )

}

// <img src="https://i.pinimg.com/236x/95/a0/25/95a0252fdac133a48edf2821c864401a--se%C3%B1ora-mary-mary-kay-ash.jpg"></img>
// <span>"Don't limit yourself. Many people limit themselves to what they think they can do. You can go as far as your mind lets you. What you believe you can achieve."</span>