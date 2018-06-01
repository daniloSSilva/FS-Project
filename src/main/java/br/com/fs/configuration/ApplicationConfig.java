package br.com.fs.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
	@Bean
	public ModelMapper modelMapperFactoryBean() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplateFactoryBean() {
		return new RestTemplate();
	}

}
