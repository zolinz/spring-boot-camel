package com.zoli.beans;

import org.apache.camel.component.infinispan.processor.idempotent.InfinispanIdempotentRepository;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class InfinispanIdempotentRepositoryFactory implements FactoryBean<InfinispanIdempotentRepository> {



    public InfinispanIdempotentRepositoryFactory() {
    }


    @Override
    public InfinispanIdempotentRepository getObject() throws Exception {

            InfinispanIdempotentRepository repo;

            GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
            global.transport().addProperty("configurationFile", "jgroups.xml").clusterName("camelapp");

            //ConfigurationBuilder cb = new ConfigurationBuilder();
            //cb.addServers("localhost:11222");
            //cb.forceReturnValues(true);


            EmbeddedCacheManager manager =  new DefaultCacheManager(global.build());
           //EmbeddedCacheManager manager =  new DefaultCacheManager(InfinispanIdempotentRepositoryFactory.class.getResourceAsStream("/infinispan.xml"));


        ConfigurationBuilder config = new ConfigurationBuilder();
               config
                .expiration().lifespan(45, TimeUnit.SECONDS)
                .clustering().cacheMode(CacheMode.REPL_SYNC);
                 manager.defineConfiguration("idempotentCache", config.build());

            //RemoteCacheManager remoteCacheManager = new RemoteCacheManager(cb.build(), true);
            repo = new InfinispanIdempotentRepository(manager, "idempotentCache");

            return repo;

    }

    public Class<?> getObjectType() {
        return InfinispanIdempotentRepository.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
