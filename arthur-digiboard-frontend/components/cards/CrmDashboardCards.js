import React, { useState, useEffect } from 'react'
import CountUp from 'react-countup'

const CrmDashboardCards = () => {

 const [myValue, setMyValue] = useState({});
 



 useEffect(() => {
    try{
        fetch("/api/rnumbers")
        .then(response => response.json())
        .then(data => setMyValue(data));
    
     }catch(error){
        console.log(error)
     }
  }, []);


  
  return (
    <div className="row mb-30">
        <div className="col-lg-3 col-6 col-xs-12">
            <div className="dashboard-top-box d-block rounded border-0 panel-bg">
                <div className="d-flex justify-content-between align-items-center mb-20">
                    <div className="right">
                        <div className="part-icon text-light rounded">
                            <span><i className="fa-light fa-user-plus"></i></span>
                        </div>
                    </div>
                    <div className="left">
                        <h3 className="fw-normal">$<CountUp end={myValue.clients}/></h3>
                    </div>
                </div>
                <div className="progress-box">
                    <p className="d-flex justify-content-between mb-1">Active Client<small>+116.24%</small></p>
                    <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                        <div className="progress-bar bg-success" style={{width:'75%'}}></div>
                    </div>
                </div>
            </div>
        </div>
        <div className="col-lg-3 col-6 col-xs-12">
            <div className="dashboard-top-box d-block rounded border-0 panel-bg">
                <div className="d-flex justify-content-between align-items-center mb-20">
                    <div className="right">
                        <div className="part-icon text-light rounded">
                            <span><i className="fa-light fa-user-secret"></i></span>
                        </div>
                    </div>
                    <div className="left">
                        <h3 className="fw-normal">$<CountUp end={myValue.admin}/></h3>
                    </div>
                </div>
                <div className="progress-box">
                    <p className="d-flex justify-content-between mb-1">Active Admin <small>56.24%</small></p>
                    <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                        <div className="progress-bar bg-primary" style={{width:'75%'}}></div>
                    </div>
                </div>
            </div>
        </div>
        <div className="col-lg-3 col-6 col-xs-12">
            <div className="dashboard-top-box d-block rounded border-0 panel-bg">
                <div className="d-flex justify-content-between align-items-center mb-20">
                    <div className="right">
                        <div className="part-icon text-light rounded">
                            <span><i className="fa-light fa-money-bill"></i></span>
                        </div>
                    </div>
                    <div className="left">
                        <h3 className="fw-normal">$<CountUp end={myValue.expenses}/></h3>
                    </div>
                </div>
                <div className="progress-box">
                    <p className="d-flex justify-content-between mb-1">Total Expenses <small>+16.24%</small></p>
                    <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                        <div className="progress-bar bg-warning" style={{width:'75%'}}></div>
                    </div>
                </div>
            </div>
        </div>
        <div className="col-lg-3 col-6 col-xs-12">
            <div className="dashboard-top-box d-block rounded border-0 panel-bg">
                <div className="d-flex justify-content-between align-items-center mb-20">
                    <div className="right">
                        <div className="part-icon text-light rounded">
                            <span><i className="fa-light fa-file"></i></span>
                        </div>
                    </div>
                    <div className="left">
                        <h3 className="fw-normal"><CountUp end={1341}/></h3>
                    </div>
                </div>
                <div className="progress-box">
                    <p className="d-flex justify-content-between mb-1">Running Projects <small>+16.24%</small></p>
                    <div className="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                        <div className="progress-bar bg-danger" style={{width:'75%'}}></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default CrmDashboardCards