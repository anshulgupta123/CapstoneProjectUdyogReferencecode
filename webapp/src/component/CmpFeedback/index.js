import axios from 'axios';
import React, { useEffect, useState } from 'react'
import AuthService from '../../services/AuthService';
import RatingService from '../../services/RatingService';
import './style.css'

export default function CmpFeedBack() {
    const [enableRating, setenableRating] = useState(false);
    const [feedback, setFeedback] = useState("");
    const [result, setresult] = useState({
        msz: "",
        mszClass: ""
    });
    const [cmpDetails, setCmpDetails] = useState({});
    const [cmpRating, setCmpRating] = useState({});
    const [feedbackList, setFeedbackList] = useState([]);
    const [cmplogo,setCmpLogo]=useState("");

    const CMPNAME = "globallogic";

    useEffect(() => {
        getCompanyDetails();
        getCompanyRating();
        refreshFeedback();
    }, []);

    const getCompanyDetails = () => {
        axios
            .get(RatingService.getCompanyDetailsAPI()+"/"+CMPNAME)
            .then((response) => {
                setCmpDetails(response.data.data);
                setCmpLogo(response.data.data.companyLogo.data)
            })
            .catch((error) => {
                console.log(error.message);
            });
    };

    const getCompanyRating = () => {
        axios
            .get(RatingService.getRatingAPI()+"/"+CMPNAME)
            .then((response) => {
                setCmpRating(response.data);
            })
            .catch((error) => {
                console.log(error.message);
            });
    };


    const refreshFeedback = () => {
        getCompanyRating();
        axios
            .get(RatingService.getCommentAPI()+"/"+CMPNAME)
            .then((response) => {
               
                // setFeedbackList(response.data);
                let nrmlfeedbacklist = response.data;
                let sortedfeedbackList = nrmlfeedbacklist.sort((a, b) => Date.parse(new Date(a.createdOn.split("/").reverse().join("-"))) - Date.parse(new Date(b.createdOn.split("/").reverse().join("-"))));
                setFeedbackList(sortedfeedbackList);
               

            })
            .catch((error) => {
                console.log(error.message);
            });
    };

    const handleSubmitFeedback = async (event) => {
        event.preventDefault();

        var rating = getRatingValue(event.target.rating);
        const currentDate = new Date();

        let fbvalues = {
            companyName: cmpDetails.companyName,
            rating: rating,
            description: feedback,
            givenBy: AuthService.getUserEmail()
        };
        try {
            console.log(fbvalues);
            axios({
                method: "post",
                url: RatingService.getCommentAPI(),
                data: fbvalues,
                headers: { 
                    "Content-Type": "application/json",
                    "Access-Control-Allow-Origin":"*"
                },
              }).then((response) => {
                console.log(response);
                if (response.status === 200) {
                    setresult({
                        msz: "Thank you. Your feedback have been submitted !!",
                        mszClass: "feedbackThanks-msg"
                    })
                    refreshFeedback();
                    resetFeedback();
                    var i = 0;
                    while (i < 5) {
                        event.target.rating[i].checked = false;
                        i = i + 1;
                    }
                }
                else {
                    setresult({
                        msz: "Something goes wrong, Please try again after sometimes.",
                        mszClass: "feedbackErr-msg"
                    })
                }
            })
            .catch((error) => {
                console.log(error.message);
            });
        }
        catch (error) {
            console.log(error);
        }
    };

    const handleSubmitBtnOnOff = (event) => {
        setenableRating(true);
    }

    const handleChangeFeedback = (event) => {
        setFeedback(event.target.value);
        setresult({
            msz: "",
            mszClass: ""
        })
    }

    function getRatingValue(e) {
        var i = 0;
        var r = 0;
        while (i < 5) {
            if (e[i].checked == true) {
                r = 5 - i;
                break;
            }
            i = i + 1;
        }
        return r;
    }

    function resetFeedback() {
        setFeedback("");
        setenableRating(false);
    }

    return (
        <>
            <div className="container mt-4">
                <div className="row  d-flex justify-content-center">
                    <div className="card mb-3 p-4 col-md-8">
                        <div className="row g-0">
                            <div className="col-md-4">
                                {cmplogo === ""?(<img src="/images/default-company-logo.png" className="img-fluid rounded-start feedback-cmplogo" alt={cmpDetails.companyName} />):(

                                    <img src={`data:image;base64,${cmplogo}`} className="img-fluid rounded-start feedback-cmplogo" alt={cmpDetails.companyName} />
                                )}
                                
                                
                            </div>
                            <div className="col-md-8">
                                <div className="card-body">
                                    <h5 className="card-title">{cmpDetails.companyName}</h5>
                                    <p className="card-text"><a href={cmpDetails.companyUrl} target="_blank">{cmpDetails.companyUrl}</a></p>
                                    <p className="card-text float-end">
                                        <small className="text-muted">
                                            <i className={Math.round(cmpRating.averageRating) >= 1 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                            <i className={Math.round(cmpRating.averageRating) >= 2 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                            <i className={Math.round(cmpRating.averageRating) >= 3 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                            <i className={Math.round(cmpRating.averageRating) >= 4 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                            <i className={Math.round(cmpRating.averageRating)>= 5 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                        </small>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <form onSubmit={handleSubmitFeedback}>
                <div className='container mt-4'>
                    <div className="row justify-content-center">
                        <div className="col-md-8 d-flex justify-content-between">
                            <label className="form-label p-0">Please give your valuable feedback: </label>
                            <div className="star-rating">
                                <div className="star-input">
                                    <input type="radio" className="starRadio" onChange={handleSubmitBtnOnOff} name="rating" id="rating-5" />
                                    <label for="rating-5" className="fa-solid fa-star"></label>
                                    <input type="radio" className="starRadio" onChange={handleSubmitBtnOnOff} name="rating" id="rating-4" />
                                    <label for="rating-4" className="fa-solid fa-star"></label>
                                    <input type="radio" className="starRadio" onChange={handleSubmitBtnOnOff} name="rating" id="rating-3" />
                                    <label for="rating-3" className="fa-solid fa-star"></label>
                                    <input type="radio" className="starRadio" onChange={handleSubmitBtnOnOff} name="rating" id="rating-2" />
                                    <label for="rating-2" className="fa-solid fa-star"></label>
                                    <input type="radio" className="starRadio" onChange={handleSubmitBtnOnOff} name="rating" id="rating-1" />
                                    <label for="rating-1" className="fa-solid fa-star"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className="col-md-8">
                            <textarea className="form-control" rows="5" onChange={handleChangeFeedback} value={feedback}></textarea>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className="col-md-8">
                            <div className={result.mszClass + " m-2 float-start"}>{result.msz}</div>
                            <button type="submit" className="btn btn-primary m-2 float-end" disabled={enableRating ? false : true}>Submit</button>
                        </div>
                    </div>

                </div>
            </form>
            <div className="container mt-2 mb-4">
                <div className="row  d-flex justify-content-center">
                    <div className="col-md-8">
                        <div className="headings d-flex justify-content-between align-items-center mb-3">
                            <h5>Total Feedback ( {feedbackList.length} )</h5>
                        </div>
                        {feedbackList.map((fbrst) => {
                            return (

                                <>
                                    <div className="card p-3 mb-2">
                                        <div className="card-body">
                                            <h5 className="card-title"><i className="fa-solid fa-user rounded-circle border border-2 text-primary p-2 m-2"></i>{fbrst.givenBy}</h5>
                                            <h6 className="card-subtitle mb-2 text-muted px-2">
                                                <div className="icons align-items-center">
                                                    <i className={fbrst.rating >= 1 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                                    <i className={fbrst.rating >= 2 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                                    <i className={fbrst.rating >= 3 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                                    <i className={fbrst.rating >= 4 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                                    <i className={fbrst.rating >= 5 ? "fa fa-star text-warning" : "fa fa-star text-secondary"} />
                                                </div>
                                                Reviewed on {fbrst.createdOn}
                                            </h6>
                                            <p className="card-text px-2">{fbrst.description}</p>
                                        </div>
                                    </div>
                                </>
                            );
                        })}
                    </div>
                </div>
            </div>
        </>
    )
}
