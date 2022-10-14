export default function Locationfilter() {
    
    return (
        <div className="skillcard cardLeft">
            {/* <img src="https://cdn-icons-png.flaticon.com/512/4781/4781517.png" className="sideimage"></img> */}
            <div className="skilltitle">Location</div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Delhi/NCR" name="Location"/>
                <label class="form-check-label" for="flexCheckDefault">
                    Delhi/NCR
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Bangalore/Bengaluru" name="Location"/>
                <label class="form-check-label" for="flexCheckChecked">
                    Bangalore/Bengaluru
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="Mumbai/Pune" name="Location"/>
                <label class="form-check-label" for="flexCheckChecked">
                    Mumbai/Pune
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input"  type="radio" value="Hyderabad/Secunderabad" name="Location"/>
                <label class="form-check-label" for="flexCheckChecked">
                    Hyderabad/Secunderabad
                </label>
            </div>
        </div>
    )
}
