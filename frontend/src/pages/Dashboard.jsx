import { useEffect, useState} from "react";
import { getMetrics, getRecommendations, runSimulation} from "../services/api.js";

const Dashboard = () => {
  const [metrics, setMetrics] = useState({});
  const [recommendations, setRecommendations] = useState([]);

    useEffect(() => {
        const loadData = async () => {
            await runSimulation();

            const metricsRes = await getMetrics();
            const recRes = await getRecommendations();

            console.log("Metrics:", metricsRes.data);
            console.log("Recommendations:", recRes.data);

            setMetrics(metricsRes.data);
            setRecommendations(recRes.data.recommendations);
        };
        loadData();
    }, []);

    return (
        <div style={{ padding: "20px"}}>
            <h1>Microservices Analyzer Dashboard</h1>

            <h2>Metrics</h2>
            <p>Avg. Latency: {metrics.averageLatency ?? "Loading..."}</p>
            <p>Failure Rate: {metrics.failureRate ?? "Loading..."}</p>

            <h2>Recommendations</h2>
            <ul>
                {recommendations?.length > 0 ? (
                    recommendations.map((rec,index) => (
                        <li key={index}>{rec}</li>
                    ))
                ) : (
                    <li>Loading...</li>
                )}
            </ul>
        </div>
    );
};

export default Dashboard;