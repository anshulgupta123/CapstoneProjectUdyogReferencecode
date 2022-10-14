import React from 'react'
import './style.css'

export default function Footer() {
  return (
    <>
      <div class="custom-shape-divider-bottom-1663144569">
        <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
          <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
          <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
          <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
        </svg>
      </div>
      <div className="footerBgColor">
        <div class="container">
          <footer class="py-2">
            <div class="row">
              <div class="col-3">
                <h5>Office Address</h5>
                <div className='flex-column'>
                    Plot No.7,<br/>Oxygen Business Park SEZ,<br/>Tower, 3, Noida-Greater Noida Expy,<br/> Sector 144, Noida,<br/> Uttar Pradesh 201304
                </div>
              </div>

              <div class="col-3">
                <h5>Abouts Us</h5>
                <div className='flex-column text-justify'>
                This is the company's flagship brand and India's largest online job website. Its business model depends on job listings and employer branding.
                </div>
              </div>

              <div class="col-2">
                <h5>Contact Us</h5>
                <ul class="nav flex-column">
                  <li class="nav-item mb-2"><a href="#" class="nav-link p-0">Tel : +91-9876543210</a></li>
                  <li class="nav-item mb-2"><a href="#" class="nav-link p-0">Mail : support@udyog.com</a></li>
                  <li class="nav-item mb-2"><a href="#" class="nav-link p-0">https://udyog.com</a></li>
                </ul>
              </div>

              <div class="col-3 offset-1">
                <form>
                  <h5>Subscribe to our newsletter</h5>
                  <p>Get Daily job post notification on your email.</p>
                  <div class="d-flex w-100 gap-2">
                    <input id="newsletter1" type="text" class="form-control" placeholder="Email address" />
                    <button class="btn btn-primary" type="button">Subscribe</button>
                  </div>
                </form>
              </div>
            </div>
            <div class="d-flex justify-content-between pt-4 mt-4">
                Working Hours 9:00 AM to 6 PM, Monday to Friday, Except Holidays
            </div>
            <div class="d-flex justify-content-between py-4 my-4 border-top-black">
              <p>© 2022 Company, Inc. All rights reserved.</p>
              <ul class="list-unstyled d-flex">
                <li class="ms-3"><a class="link-dark" href="#"><i class="fa-brands fa-facebook footersocial"></i></a></li>
                <li class="ms-3"><a class="link-dark" href="#"><i class="fa-brands fa-square-twitter footersocial"></i></a></li>
                <li class="ms-3"><a class="link-dark" href="#"><i class="fa-brands fa-linkedin footersocial"></i></a></li>
              </ul>
            </div>
          </footer>
        </div>
      </div>
    </>

  )
}