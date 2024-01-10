import { useEffect, useState } from "react";

function RecentOrders() {

  const [data, setData] = useState([]);

  useEffect(() => {
    // Fetch data from your API
    fetch('/api/ecommerce/orders/summary/recents')
      .then(response => response.json())
      .then(result => setData(result))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (

    <div className="col-xxl-8">
        <div className="panel">
            <div className="panel-header">
                <h5>Recent Orders</h5>
            </div>
            <div className="panel-body">
                    <table className="table table-dashed recent-order-table dataTable no-footer">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer Full Name</th>
                                <th>Product type</th>
                                <th>Order date</th>
                                <th>Price</th>
                                {/* <th>Status</th> */}
                            </tr>
                        </thead>
                        <tbody>
                            {data.map((item, index) => (
                                <tr key={index}>
                                    <td>{item.orderID}</td>
                                    <td>{item.customerFullName}</td>
                                    <td>{item.productType}</td>
                                    <td>{item.orderDate}</td>
                                    <td>{item.price}</td>
                                    {/* <td>{item.orderStatus}</td> */}
                                </tr>
                            ))}
                        </tbody>
                    </table>
            </div>
        </div>
    </div>         
  );
}

export default RecentOrders;