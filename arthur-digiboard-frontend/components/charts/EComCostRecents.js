import { useEffect, useState } from "react";

function ECostRecents() {
  const [data, setData] = useState([]);

  useEffect(() => {
    // Fetch data from your API
    fetch("/api/ecommerce/costs/recents")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  return (
    <div className="col-xxl-8">
      <div className="panel">
        <div className="panel-header">
          <h5>Recent Cost Invoice</h5>
        </div>
        <div className="panel-body">
          <table className="table table-dashed recent-order-table dataTable no-footer">
            <thead>
              <tr>
                <th>Cost ID</th>
                <th>Category Name</th>
                <th>Subcategory Name</th>
                <th>Cost</th>
                <th>Invoice date</th>
              </tr>
            </thead>
            <tbody>
              {data.map((item, index) => (
                <tr key={index}>
                  <td>{item.costId}</td>
                  <td>{item.categoryName}</td>
                  <td>{item.subcategoryName}</td>
                  <td>{item.cost} $</td>
                  <td>{item.dateRecorded}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ECostRecents;
