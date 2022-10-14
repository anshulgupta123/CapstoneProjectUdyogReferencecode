import Locationfilter from "./LocationFilter";
import Salaryfilter from "./SalaryFilter";

export default function LeftSidefilters() {
    return (
        <>
        <div className="leftfiltercontainer">
            <Salaryfilter />
            <Locationfilter />
            
           
            </div>
        </>
    )
}