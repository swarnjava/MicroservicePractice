package com.swarn.proxyfilter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

@Component
public class SimpleFilter extends ZuulFilter{
	
	 private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);
	
	@Override
	   public String filterType() {
	       return "pre";
	   }
	   @Override
	   public int filterOrder() {
	       return 1;
	   }
	   @Override
	   public boolean shouldFilter() {
	       return true;
	   }
	   @Override
	   public Object run() {
		   
	       RequestContext ctx = RequestContext.getCurrentContext();
	       HttpServletRequest request = ctx.getRequest();
	      
	       String jwtToken="xxxx.yyyy.zzzz";
	       ctx.addZuulRequestHeader("Authorization", "Bearer "+jwtToken);
	      
	       return null;
	   }
	   
	 
}
