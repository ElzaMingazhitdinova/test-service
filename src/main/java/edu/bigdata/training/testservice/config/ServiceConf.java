package edu.bigdata.training.testservice.config;

import edu.bigdata.training.testservice.controller.TestServiceController;
import edu.bigdata.training.testservice.dao.TestServiceRepository;
import edu.bigdata.training.testservice.model.PersonEntity;
import edu.bigdata.training.testservice.service.TestBusinessLogicService;
import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.UUID;

@Configuration
@Import(IgniteConf.class)
public class ServiceConf {
    @Bean
    TestServiceRepository testServiceRepository(Ignite ignite, CacheConfiguration<UUID, PersonEntity> personCacheConf){
        return new TestServiceRepository(ignite, personCacheConf);
    }

    @Bean
    TestBusinessLogicService testBusinessLogicService(TestServiceRepository testServiceRepository){
        return new TestBusinessLogicService(testServiceRepository);
    }

    @Bean
    TestServiceController testServiceController(TestBusinessLogicService testBusinessLogicService){
        return new TestServiceController(testBusinessLogicService);
    }
}
