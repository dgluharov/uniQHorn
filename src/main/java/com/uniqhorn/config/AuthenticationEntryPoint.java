package com.uniqhorn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");

		Map<String, Object> data = new HashMap<>();
		data.put("timestamp", new Date());
		data.put("status", HttpStatus.UNAUTHORIZED.value());
		data.put("message", "Access Denied: wrong username or password");
		data.put("path", request.getRequestURL().toString());

		ObjectMapper mapper = new ObjectMapper();
		String responseMsg = mapper.writeValueAsString(data);
		response.getWriter().write(responseMsg);
	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("Uniqhorn");
		super.afterPropertiesSet();
	}
}
