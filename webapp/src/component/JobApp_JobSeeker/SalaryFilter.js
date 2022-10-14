import { useState } from "react";

export default function Salaryfilter({handleFilter}) {
    const[data,setData]=useState([])
    let showValue=(e)=>{
        var checkbox = document.getElementById(e.target.id);
        if (checkbox.checked == true)
        {
        
          console.log(e.target.value)
        }
    }

    return (
        <div className="skillcard cardLeft">
            {/* <img src="https://cdn-icons-png.flaticon.com/512/3135/3135706.png" className="sideimage"></img> */}
            <div className="skilltitle">Salary</div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="1-3 Lakh" id="salary1" onClick={handleFilter([1,2,3,4,5])}/>
                <label class="form-check-label" for="flexCheckDefault">
                    1-3 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="3-6 Lakh" id="salary2" onClick={showValue}/>
                <label class="form-check-label" for="flexCheckChecked">
                     3-6 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="6-8 Lakh" id="salary3" onClick={showValue}/>
                <label class="form-check-label" for="flexCheckChecked">
                6-8 Lakh
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="8-10 Lakh" id="salary4" onClick={showValue}/>
                <label class="form-check-label" for="flexCheckChecked">
                8-10 Lakh
                </label>
            </div>
        </div>
    )
}
