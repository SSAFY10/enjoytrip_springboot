package com.ssafy.enjoytrip.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer  {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// addMapping("/**") is not allowed:
		//	When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header.
		//	To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
		registry.addMapping("/enjoytrip")
			.allowedOrigins("http://localhost:5173", "http://localhost:5174") // 허용할 출처
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowCredentials(true)
			.maxAge(3600);
	}

}
