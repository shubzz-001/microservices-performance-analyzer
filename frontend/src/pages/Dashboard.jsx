import { useEffect, useState} from "react";
import { getMetrics, getRecommendations, runSimulation} from "../services/api.js";
import { LineChart, Line, XAxis, YAxis, Tooltip} from "recharts";

const Dashboard = () => {
  const [metrics, setMetrics] = useState({});
  const [recommendations, setRecommendations] = useState([]);
  const cardStyle = {
      padding: "20px",
      borderRadius: "10px",
      background: "#1e293b",
      color: "white",
      width: "200px",
      textAlign: "center"
  };

    const chartData = [
        { name: "Run1", latency: 120 },
        { name: "Run2", latency: 200 },
        { name: "Run3", latency: 150 },
    ];

    const loadData = async () => {
        await runSimulation();

        const metricsRes = await getMetrics();
        const recRes = await getRecommendations();

        console.log("Metrics:", metricsRes.data);
        console.log("Recommendations:", recRes.data);

        setMetrics(metricsRes.data);
        setRecommendations(recRes.data.recommendations);
    };

    useEffect(() => {
        loadData();
        const interval = setInterval(() => {
            loadData();
        }, 5000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
            <div style={cardStyle}>
                <h3>Avg Latency</h3>
                <p>{metrics.averageLatency?.toFixed(2)}ms</p>
            </div>

            <div style={cardStyle}>
                <h3>Failure Rate</h3>
                <p>{(metrics.failureRate * 100)?.toFixed(2)}%</p>
            </div>

            <LineChart width={500} height={300} data={chartData}>
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="latency" />
            </LineChart>
            <button onClick={loadData} style={{ marginBottom: "20px"}}>
                Run Simulation
            </button>

            <ul>
                {recommendations.map((rec, index) => (
                    <li key={index} style={{ marginBottom: "10px" }}>
                        ⚡ {rec}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Dashboard;