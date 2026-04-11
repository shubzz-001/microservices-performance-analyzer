import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api"
});

export const getMetrics = () => API.get("/metrics");
export const getRecommendations = () => API.get("/recommendations");
export const runSimulation = () => API.get("/simulation/run");
export const analyzeAI = () => API.get("ai/analyze");
