package com.example.geekmoney.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.geekmoney.config.property.GeekmoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Autowired
	private GeekmoneyApiProperty geekmoneyApiProperty;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse resp = ((HttpServletResponse) response);

		resp.setHeader("Access-Control-Allow-Origin", geekmoneyApiProperty.getOriginPermitida());
		resp.setHeader("Access-Control-Allow-Credentials", "true"); // para enviar o cookie
		
		// req.getMetohod retona tipo de requisição
		if ("OPTIONS".equals(req.getMethod()) && geekmoneyApiProperty.getOriginPermitida().equals(req.getHeader("Origin"))) {
			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS"); //metodos permitidos
			resp.setHeader("Access-Control-Allow-Headers", "Authorization, Content-type, Accept"); // headers permitidos
			resp.setHeader("Access-Control-Max-Age", "3600"); // quanto tempo para aplicaçao fazer proxima requisiçao
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
