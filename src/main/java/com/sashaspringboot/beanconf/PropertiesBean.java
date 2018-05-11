/*
package com.sashaspringboot.beanconf;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.PathResource;

import java.util.Properties;

@Configuration
public class PropertiesBean {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesMy() {
        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();

        yaml.setResources(
                new PathResource("cfg/app.yml")
        );

        Properties properties = yaml.getObject();
        pspc.setProperties(properties);
        pspc.setIgnoreUnresolvablePlaceholders(true);

        return pspc;
    }


}*/
