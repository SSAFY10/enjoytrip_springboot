package com.ssafy.enjoytrip.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer  {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:5173", "http://localhost:5174") // 허용할 출처
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowCredentials(true)
			.maxAge(3600);
	}

}
