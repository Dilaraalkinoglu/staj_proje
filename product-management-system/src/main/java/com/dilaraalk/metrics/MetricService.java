package com.dilaraalk.metrics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class MetricService {
	
	//http status kodlarının kaç kez döndüğünü tutuyoruz burada
	private Map<String, Map<Integer, Integer>> requestStatusMetric = new ConcurrentHashMap<>();
	
	
	public MetricService() {
		this.requestStatusMetric = new ConcurrentHashMap<>();
	}
	
	
	// request: hangi endpoint oldugu, status: http respoonse status kod
	public void increaseCount(String request, int status) {
		requestStatusMetric
		.computeIfAbsent(request, r -> new ConcurrentHashMap<>())
		.merge(status, 1, Integer::sum);
	}
	
	
	public Map<String, Map<Integer, Integer>> getRequestStatusMetric() {
		return requestStatusMetric;
	}

}
