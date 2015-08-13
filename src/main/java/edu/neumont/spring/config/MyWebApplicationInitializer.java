package edu.neumont.spring.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { PersistenceConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
