from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import numpy as np
from sklearn.ensemble import IsolationForest

app = FastAPI()

class LogData(BaseModel):
    latency: int
    success: bool

class RequestData(BaseModel) :
    logs: List[LogData]

@app.post("/detect-anomalies")
def detect_anomalies(data: RequestData) :
    latencies = np.array([[log.latency] for log in data.logs])

    model = IsolationForest(contamination=0.1)
    model.fit(latencies)


    predictions = model.predict(latencies)

    results = []
    for i, log in enumerate(data.logs) :
        results.append({
            "latency": log.latency,
            "success": log.success,
            "anomaly": True if predictions[i] == -1 else False
        })

    return {"results": results}