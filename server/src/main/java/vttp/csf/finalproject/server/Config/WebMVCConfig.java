package vttp.csf.finalproject.server.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMVCConfig {
    @Value("UI.BASE.URL")
	String UI_BASE_URL;

	//Allowing calls made as long as they have this base URL
    public class WebMvcConfig implements WebMvcConfigurer {
        @Value("UI.BASE.URL")
        String UI_BASE_URL;
        
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            System.out.println("inside addCorsMappings");
            registry
                    .addMapping("/api/v1/**")
                    .allowedOrigins(UI_BASE_URL)
                    .allowedMethods("*")
                    .allowCredentials(true)
                    .maxAge(3600);
    
        }  
    
}
}
