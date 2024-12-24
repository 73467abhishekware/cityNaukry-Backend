package com.hundia.cityNaukry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer{

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/*") // Allow all paths
	                .allowedOrigins("http://localhost:3000") // Allow requests from your frontend
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
	                .allowedHeaders("*") // Allow all headers
	                .allowCredentials(true); // If you need credentials like cookies, authorization headers
	    }
	 
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/uploads/**")
	                .addResourceLocations("file:C:/JobPortalBackend/demoJobportal/uploads/");
	    }
}
