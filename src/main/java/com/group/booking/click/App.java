package com.group.booking.click;

import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.group.booking.click.business.AvailabilityProcessor;

@SpringBootApplication
@ComponentScan(value="com.group.booking")
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer /*implements CommandLineRunner*/{
	
/*	@Autowired
	AvailabilityProcessor kk;*/
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
	

	/*@Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {

        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;

    }*/


	/*@Override
	public void run(String... args) throws Exception {
		Date fromDate = new Date();
	//	kk.insertAvailability("101", 2018, 8, "aradhya");
		kk.fetchAvailabilityBasedOn("101", "09/05/2018", "09/20/2018");
	
		System.out.println("compleddd");
		
	}*/

}
