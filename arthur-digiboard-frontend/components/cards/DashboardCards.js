import Link from "next/link";
import { useEffect, useState } from "react";
import CountUp from "react-countup";

const DashboardCards = ({ onLinkClick }) => {
  const [orderCardData, setOrderCardData] = useState({
    latestWeekPercentageChange: 0,
    totalOrderCount: 0,
  });

  const [activeLink, setActiveLink] = useState(null);
  const setActive = (link) => {
    setActiveLink(link);
  };

  useEffect(() => {
    fetch("/api/ecommerce/dashboard-cards")
      .then((response) => response.json())
      .then((data) => setOrderCardData(data.orderCard))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const [marketingCostSum, setMarketingCostSum] = useState(0.0);
  useEffect(() => {
    fetch("/api/ecommerce/costs/sum")
      .then((response) => response.json())
      .then((data) => setMarketingCostSum(data))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const [balanceAmount, setBalanceAmount] = useState(0.0);
  useEffect(() => {
    fetch("/api/ecommerce/balance")
      .then((response) => response.json())
      .then((data) => setBalanceAmount(data))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const valueStyle = (value) => {
    return value >= 0 ? "text-success" : "text-danger";
  };

  const activeColor = "#2a5180";
  const activeSalesAnalyticsCardStyle = {
    backgroundColor: activeLink === "salesAnalytics" ? activeColor : "inherit",
    cursor: "pointer",
  };
  const activeMarketingCardStyle = {
    backgroundColor:
      activeLink === "marketingExpenses" ? activeColor : "inherit",
    cursor: "pointer",
  };
  const activeWeeklybalanceRecapCardStyle = {
    backgroundColor:
      activeLink === "weeklybalanceRecap" ? activeColor : "inherit",
    cursor: "pointer",
  };

  return (
    <div className="row mb-30">
      <div
        className="col-lg-3"
        onClick={() => {
          onLinkClick("salesAnalytics");
          setActive("salesAnalytics");
        }}
        style={activeSalesAnalyticsCardStyle}
      >
        <div className="dashboard-top-box rounded-bottom panel-bg">
          <div className="left">
            <h3>
              <CountUp end={orderCardData.totalOrderCount} />
            </h3>
            <p>Orders</p>
            <Link href="#">Excluding orders in transit</Link>
          </div>
          <div className="right">
            <span
              className={valueStyle(orderCardData.latestWeekPercentageChange)}
            >
              since previous week {orderCardData.latestWeekPercentageChange}%
            </span>
            <div className="part-icon rounded">
              <span>
                <i className="fa-light fa-bag-shopping"></i>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div
        className="col-lg-3"
        onClick={() => {
          onLinkClick("marketingExpenses");
          setActive("marketingExpenses");
        }}
        style={activeMarketingCardStyle}
      >
        <div className="dashboard-top-box rounded-bottom panel-bg">
          <div className="left">
            <h3>
              $<CountUp end={marketingCostSum} />
            </h3>
            <p>Marketing expenses</p>
            <Link href="#">View net expenses</Link>
          </div>
          <div className="right">
            <span className="text-primary"></span>
            <div className="part-icon rounded">
              <span>
                <i className="fa-light fa-dollar-sign"></i>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div
        className="col-lg-3"
        onClick={() => {
          onLinkClick("weeklybalanceRecap");
          setActive("weeklybalanceRecap");
        }}
        style={activeWeeklybalanceRecapCardStyle}
      >
        <div className="dashboard-top-box rounded-bottom panel-bg">
          <div className="left">
            <h3>
              $<CountUp end={balanceAmount} />
            </h3>
            <p>My Balance</p>
            <Link href="#">view balance</Link>
          </div>
          <div className="right">
            <span className="text-primary"></span>
            <div className="part-icon rounded">
              <span>
                <i className="fa-light fa-credit-card"></i>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashboardCards;
