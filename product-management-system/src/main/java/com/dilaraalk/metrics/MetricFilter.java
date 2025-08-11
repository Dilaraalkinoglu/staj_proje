package com.dilaraalk.metrics;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MetricFilter implements Filter{

	private MetricService metricService;
	
	
	//metricservice'in constructor ile enjekte edilme yöntemi
	public MetricFilter(MetricService metricService) {
		this.metricService = metricService;
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String req = httpRequest.getMethod() + " " +
		httpRequest.getRequestURI();
		
		
		//burası önemliymiş!:istek uygulamanın devamına
		//yani controller'a gönderiliyor
		//eger burası olmazsa istek hiç işlenmiyor
		chain.doFilter(request, response);
		
		int status = ((HttpServletResponse) response).getStatus();
		metricService.increaseCount(req, status);
 	
		
	}

}
