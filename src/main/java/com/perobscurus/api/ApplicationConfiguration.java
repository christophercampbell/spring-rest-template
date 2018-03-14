package com.perobscurus.api;


import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Configuration
@ComponentScan(basePackages = {"com.perobscurus.api"})
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${config.file}", ignoreResourceNotFound = true)
})
public class ApplicationConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    public ApplicationConfiguration() {
    }

    /**
     * Configure Jackson ObjectMapper for serialization/deserialization
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true)
                .modules(new GuavaModule(),
                        new Jdk8Module());

        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    /**
     * Add /webapp as root for static context
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

    /**
     * An implementation of MultipartResolver for file upload.
     *
     * @return CommonsMultipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return resolver;
    }

    /**
     * Provides property resolving placeholder values, as in key=${some.value}
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}