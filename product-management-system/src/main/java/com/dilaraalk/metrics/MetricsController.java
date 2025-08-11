package com.dilaraalk.metrics;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
	
	private final MetricService metricService;
	
	public MetricsController(MetricService metricService) {
		this.metricService = metricService;
	}
	
	@GetMapping("/requests")
	public Map<String, Map<Integer, Integer>> getRequestMetrics(){
		return metricService.getRequestStatusMetric();
	}
	
	
	

}
