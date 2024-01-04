import { useEffect, useState } from 'react';
import { CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';
const WeeklySalesRecap = () => {

  const [data, setData] = useState([]);

  useEffect(() => {
    fetch('/api/ecommerce/orders/weekly-sales')
      .then(response => response.json())
      .then(result => setData(result))
      .catch(error => console.error('Error fetching data:', error));
  }, []);


  return (
    <div className="col-xxl-4">
        <div className="panel chart-panel-1">
            <div className="panel-header">
                <h5>Weekly Sales Recap</h5>
            </div>
            <div className="panel-body">
                <div id="saleAnalytics" className="chart-dark">
                  <ResponsiveContainer width="100%" maxHeight={410} minHeight={425}>
                    <LineChart data={data} width={200} height={200} >        
                        <CartesianGrid strokeDasharray="3" />
                        <XAxis dataKey="weekId" />
                        <YAxis />
                        <Tooltip />
                        <Line  type="monotone" dataKey="price" name="amount" stroke="#2e49d1" strokeWidth={3}  fill="#2e49d1" activeDot={{ r: 5 }} />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
            </div>
        </div>
    </div>
  );
}

export default WeeklySalesRecap;
