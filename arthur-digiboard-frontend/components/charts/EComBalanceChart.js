import { useEffect, useState } from "react";
import {
  Area,
  Bar,
  CartesianGrid,
  ComposedChart,
  Legend,
  Rectangle,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

function BalanceChart() {
  const [data, setData] = useState([]);
  useEffect(() => {
    fetch("/api/ecommerce/balance/weekly-recap")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const gradientOffset = () => {
    const dataMax = Math.max(...data.map((i) => i.eowBalance));
    const dataMin = Math.min(...data.map((i) => i.eowBalance));
    if (dataMax <= 0) {
      return 0;
    }
    if (dataMin >= 0) {
      return 1;
    }
    return dataMax / (dataMax - dataMin);
  };

  const off = gradientOffset();

  return (
    <div className="col-xxl-12">
      <div className="panel chart-panel-1">
        <div className="panel-header">
          <h5>Balance</h5>
          <div className="btn-box"></div>
        </div>
        <div className="panel-body">
          <div id="BalanceOveriew" className="chart-dark">
            <ResponsiveContainer width="100%" aspect={3}>
              <ComposedChart
                width={500}
                height={400}
                data={data}
                margin={{
                  top: 20,
                  right: 20,
                  bottom: 20,
                  left: 20,
                }}
              >
                <CartesianGrid stroke="#334652" strokeDasharray="3" />
                <XAxis dataKey="weekId" tick="center" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar
                  dataKey="eowPrice"
                  fill="#425fdb"
                  name="Sales"
                  activeBar={<Rectangle fill="pink" stroke="blue" />}
                />
                <Bar
                  dataKey="eowCost"
                  fill="#ed515c"
                  name="Expenses"
                  activeBar={<Rectangle fill="gold" stroke="purple" />}
                />
                {/* <Line
                  type="monotone"
                  dataKey="eowBalance"
                  name="Weekly Balance"
                  stroke="#ff7300"
                /> */}

                <defs>
                  <linearGradient id="splitColor" x1="0" y1="0" x2="0" y2="1">
                    <stop offset={off} stopColor="#04c43e" stopOpacity={0.3} />
                    <stop offset={off} stopColor="#ff7300" stopOpacity={0.3} />
                  </linearGradient>
                </defs>
                <Area
                  type="monotone"
                  dataKey="eowBalance"
                  name="Weekly Balance"
                  stroke="#04c43e"
                  fill="url(#splitColor)"
                />
              </ComposedChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BalanceChart;
