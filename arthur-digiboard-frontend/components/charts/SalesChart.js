//import { useContext } from 'react';
import {
  Bar,
  BarChart,
  CartesianGrid,
  Legend,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

import { useEffect, useState } from "react";

//import { DigiContext } from '../../context/DigiContext';
const SalesChart = () => {
  //const {isLightTheme,isRechartHeight} = useContext(DigiContext)
  const isLightTheme = true;

  const data = [
    {
      name: "Saturday",
      stock: 31,
      order: 11,
    },
    {
      name: "Sunday",
      stock: 40,
      order: 32,
    },
    {
      name: "Monday",
      stock: 28,
      order: 45,
    },
    {
      name: "Tuesday",
      stock: 51,
      order: 32,
    },
    {
      name: "Wednesday",
      stock: 42,
      order: 34,
    },
    {
      name: "Thursday",
      stock: 109,
      order: 52,
    },
    {
      name: "Friday",
      stock: 28,
      order: 45,
    },
  ];

  const [salesChartData, setSalesChartData] = useState([]);
  // Make API call to fetch data for SalesChart
  useEffect(() => {
    fetch("/api/ecommerce/orders/by-date")
      .then((response) => response.json())
      .then((data) => {
        setSalesChartData(data); // Update Order data // fill state and toggle conditional
        console.log("inside fetchAllOrders:", data);
      })
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  return (
    <ResponsiveContainer width="100%" maxHeight={410} minHeight={425}>
      <BarChart
        data={salesChartData}
        margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
      >
        <CartesianGrid
          strokeDasharray="3"
          stroke={`${
            isLightTheme ? "hsl(0deg 0% 0% / 20%)" : "rgba(255, 255, 255, 0.2)"
          }`}
        />
        <XAxis
          dataKey="orderDate"
          stroke={`${
            isLightTheme
              ? "hsl(0deg 0% 27.45% / 70%)"
              : "hsl(0deg 0% 89.41% / 70%)"
          }`}
        />
        <YAxis />
        <Tooltip />
        <Legend className="sales" />
        <Bar
          dataKey="softwarePrice"
          name="software"
          stackId="a"
          fill="#877df5"
        />
        <Bar dataKey="ebookPrice" name="ebook" stackId="a" fill="#2e49d1" />
        <Bar
          dataKey="digitalMusicPrice"
          name="music"
          stackId="a"
          fill="#ff6b63"
        />
        <Bar
          dataKey="onlineCoursePrice"
          name="online course"
          stackId="a"
          fill="#ccbe3b"
        />
        <Bar
          dataKey="streamingServicePrice"
          name="streaming"
          stackId="a"
          fill="#00D658"
        />
      </BarChart>
    </ResponsiveContainer>
  );
};

export default SalesChart;
