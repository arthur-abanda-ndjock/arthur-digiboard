import SalesChart from "../charts/SalesChart";

const SalesAnalytics = () => {
  return (
    <div className="col-xxl-12">
      <div className="panel chart-panel-1">
        <div className="panel-header">
          <h5>Sales Analytics</h5>
          <div className="btn-box"></div>
        </div>
        <div className="panel-body">
          <div id="saleAnalytics" className="chart-dark"></div>
          <SalesChart />
        </div>
      </div>
    </div>
  );
};

export default SalesAnalytics;
