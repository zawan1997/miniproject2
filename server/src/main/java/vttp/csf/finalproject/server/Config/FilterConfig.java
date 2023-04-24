package vttp.csf.finalproject.server.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vttp.csf.finalproject.server.Filter.JwtFilter;

public class FilterConfig {
    Logger logger = LoggerFactory.getLogger(FilterConfig.class);

	@SuppressWarnings("rawtypes")
	@Bean
	public FilterRegistrationBean jwtFilter() {		

		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setFilter(new JwtFilter());
		
		
        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
       filter.addUrlPatterns("/users");
       filter.addUrlPatterns("/users/*");
       filter.addUrlPatterns("/users/update");
       filter.addUrlPatterns("/users/delete/*");

       filter.addUrlPatterns("/comments/create");
       filter.addUrlPatterns("/comments/update");
       filter.addUrlPatterns("/comments/delete/*");

       filter.addUrlPatterns("/imageComments/create");
       filter.addUrlPatterns("/imageComments/update");
       filter.addUrlPatterns("/imageComments/delete/*");

       filter.addUrlPatterns("/locations/create");
       filter.addUrlPatterns("/locations/update");
       filter.addUrlPatterns("/locations/delete/*");

		return filter;		
	}
}
