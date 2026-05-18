package com.artantech.unipdsspring.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MigrationCheckConfig implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MigrationCheckConfig.class);
    private final Flyway flyway;

    public MigrationCheckConfig(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Verificando se as migrations foram aplicadas...");
        var info = flyway.info();
        var pending = info.pending();
        
        if (pending.length > 0) {
            logger.warn("Existem {} migrations pendentes!", pending.length);
        } else {
            logger.info("Todas as migrations foram aplicadas com sucesso. Banco de dados pronto!");
        }
    }
}
