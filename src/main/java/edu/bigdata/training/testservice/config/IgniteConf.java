package edu.bigdata.training.testservice.config;

import edu.bigdata.training.testservice.model.PersonEntity;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.TcpDiscoveryIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.UUID;

@Configuration
public class IgniteConf {
    /**
     * имя экземпляра узла
     */
    public static final UUID instanceName = UUID.randomUUID();

    /**
     * Локальные адрес узла
     */
    @Value("${testservice.node.localAddr:127.0.0.1}")
    private String localAddr;

    @Value("${testservice.node.ipFinderAddresses:dummy}")
    private String ipFinderAddresses;

    @Value("${testservice.node.clientMode:false}")
    private boolean clientMode;

    @Bean
    public TcpDiscoveryIpFinder tcpDiscoveryIpFinder() {
        TcpDiscoveryMulticastIpFinder tcpDiscoveryIpFinder = new TcpDiscoveryMulticastIpFinder();
        tcpDiscoveryIpFinder.setLocalAddress(localAddr);
        if (!"dummy".equals(ipFinderAddresses)) {
            tcpDiscoveryIpFinder.setAddresses(Collections.singletonList(ipFinderAddresses));
        }

        return tcpDiscoveryIpFinder;
    }

    @Bean
    public DiscoverySpi discoverySpi(TcpDiscoveryIpFinder tcpDiscoveryIpFinder) {
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setLocalAddress(localAddr);
        discoverySpi.setIpFinder(tcpDiscoveryIpFinder);

        return discoverySpi;
    }


    @Bean
    public IgniteConfiguration igniteConfiguration(DiscoverySpi discoverySpi) {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setIgniteInstanceName(instanceName.toString());
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setClientMode(clientMode);
        igniteConfiguration.setDiscoverySpi(discoverySpi);

        return igniteConfiguration;
    }

    @Bean
    public Ignite ignite(IgniteConfiguration configuration) {
        IgniteSpringBean igniteSpringBean = new IgniteSpringBean();
        igniteSpringBean.setConfiguration(configuration);

        return igniteSpringBean;
    }

    @Bean
    public CacheConfiguration<UUID, PersonEntity> ignitePersonCacheConfiguration() {
        CacheConfiguration<UUID, PersonEntity> personCacheCfg = new CacheConfiguration<>();
        personCacheCfg.setName("person");
        personCacheCfg.setCacheMode(CacheMode.REPLICATED);
        personCacheCfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        personCacheCfg.setIndexedTypes(UUID.class, PersonEntity.class);

        return personCacheCfg;
    }
}
