package edu.bigdata.training.testservice.config;

import edu.bigdata.training.testservice.model.PersonEntity;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class IgniteConf {
    @Bean
    public Ignite ignite() {
        return new IgniteSpringBean();
    }

    @Bean
    public CacheConfiguration<UUID, PersonEntity> ignitePersonCacheConfiguration() {
        CacheConfiguration<UUID, PersonEntity> personCacheCfg = new CacheConfiguration<>();
        personCacheCfg.setName("Person");
        personCacheCfg.setCacheMode(CacheMode.REPLICATED);
        personCacheCfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        personCacheCfg.setIndexedTypes(UUID.class, PersonEntity.class);
        return personCacheCfg;
    }
}
