import { useEffect, useState } from "react";
import { Cell, Pie, PieChart, ResponsiveContainer, Tooltip } from "recharts";

function ECosts() {
  const [data, setData] = useState([]);
  useEffect(() => {
    // Fetch data from your API
    fetch("/api/ecommerce/costs/categories")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const COLORS = ["#8884d8", "#82ca9d", "#FFBB28", "#FF8042", "#AF19FF"];
  const SUBCOLORS = [
    "#8884d8",
    "#8884d8",
    "#8884d8",
    "#82ca9d",
    "#FFBB28",
    "#FFBB28",
    "#FF8042",
    "#FF8042",
    "#AF19FF",
    "#AF19FF",
  ];

  const CustomTooltip = ({ active, payload, label }) => {
    if (active) {
      return (
        <div
          className="custom-tooltip"
          style={{
            backgroundColor: "#ffff",
            padding: "5px",
            border: "1px solid #cccc",
          }}
        >
          <label>{`${payload[0].name} : ${payload[0].value}$`}</label>
        </div>
      );
    }
  };

  const RADIAN = Math.PI / 180;

  let renderLabel = function (entry) {
    return entry.name;
  };
  const renderCustomizedLabel = ({
    cx,
    cy,
    midAngle,
    innerRadius,
    outerRadius,
    percent,
    index,
    entry,
  }) => {
    const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
    const x = cx + radius * Math.cos(-midAngle * RADIAN);
    const y = cy + radius * Math.sin(-midAngle * RADIAN);

    return (
      <text
        x={x}
        y={y}
        fill="white"
        textAnchor={x > cx ? "start" : "end"}
        dominantBaseline="central"
      >
        {`${(percent * 100).toFixed(0)}%`}
      </text>
    );
  };

  return (
    <div className="col-xxl-4">
      <div className="panel chart-panel-1">
        <div className="panel-header">
          <h5>Marketing Costs</h5>
          <div className="btn-box"></div>
        </div>
        <div className="panel-body">
          <div id="marketingCosts" className="chart-dark">
            <ResponsiveContainer width="100%" maxHeight={410} minHeight={425}>
              <PieChart width={730} height={300}>
                <Pie
                  data={data.categories}
                  color="#000000"
                  dataKey="totalCost"
                  nameKey="name"
                  label={renderCustomizedLabel}
                  cx="50%"
                  cy="50%"
                  outerRadius={90}
                  fill="#8884d8"
                >
                  {(data.categories || []).map((entry, index) => (
                    <Cell
                      key={`cell-${index}`}
                      fill={COLORS[index % COLORS.length]}
                    />
                  ))}
                </Pie>

                <Pie
                  data={data.subcategories}
                  color="#000000"
                  dataKey="totalCost"
                  nameKey="name"
                  cx="50%"
                  cy="50%"
                  innerRadius={110}
                  outerRadius={150}
                  fill="#8884d8"
                >
                  {(data.subcategories || []).map((entry, index) => (
                    <Cell
                      key={`cell-${index}`}
                      fill={SUBCOLORS[index % SUBCOLORS.length]}
                    />
                  ))}
                </Pie>

                <Tooltip content={<CustomTooltip />} />
                {/* <Legend /> */}
              </PieChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ECosts;
