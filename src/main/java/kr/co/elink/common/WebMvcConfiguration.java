package kr.co.elink.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Autowired
	LoggerInterceptor loggerInterceptor;
	
	@Value("${resource.path}")
	private String resourcePath;
	
	@Value("${upload.path}")
	private String uploadPath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")    
                .allowedMethods(
                		HttpMethod.HEAD.name(),
                    	HttpMethod.GET.name(),
                    	HttpMethod.POST.name(),
                    	HttpMethod.PUT.name(),
                    	HttpMethod.DELETE.name());
    }
    
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor)
//		.addPathPatterns("/server/**")
//		.addPathPatterns("/sandbox/**")
		.addPathPatterns("/**");
	}
	
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadPath)
				.addResourceLocations(resourcePath);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSizePerFile(500 * 1024 * 1024);
		return commonsMultipartResolver;
	}		
}
