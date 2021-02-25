package com.w2m.superhero;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.w2m.superhero.resource.SuperheroResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JaxRsConfig extends ResourceConfig {

	private String PACKAGE = "com.w2m.superhero.resource";

	@Autowired
	public JaxRsConfig(@Value("${spring.jersey.applicationPath}") String applicationPath) {
		register(ApiListingResource.class);
		register(SwaggerSerializers.class);
		register(UncaughtExceptionMapper.class);
		register(SuperheroResource.class);

		BeanConfig swaggerConfigBean = new BeanConfig();
		swaggerConfigBean.setConfigId("Swagger");
		swaggerConfigBean.setTitle("Superhero project");
		swaggerConfigBean.setContact("W2M");
		swaggerConfigBean.setBasePath(applicationPath);
		swaggerConfigBean.setSchemes(new String[] { "http", "https" });
		swaggerConfigBean.setResourcePackage(PACKAGE);
		swaggerConfigBean.setScan(true);
	}

}