package edu.bigdata.training.testservice.config;


import edu.bigdata.training.testservice.dao.TestServiceRepository;
import edu.bigdata.training.testservice.utils.EntityUtils;
import org.apache.ignite.Ignite;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UtilsConf {
    @Bean
    EntityUtils entityUtils(TestServiceRepository testServiceRepository, Ignite ignite){
        return new EntityUtils(testServiceRepository, ignite);
    }
}
