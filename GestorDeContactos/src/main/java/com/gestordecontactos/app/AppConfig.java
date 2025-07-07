package com.gestordecontactos.app;

import com.gestordecontactos.infra.DatabaseConfig;
import com.gestordecontactos.service.HelloService;
import com.gestordecontactos.utils.ConfigProperties;
import com.google.inject.AbstractModule;
import javax.sql.DataSource;

/**
 * Configuración básica de Guice.
 */
public class AppConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigProperties.class).asEagerSingleton();
        bind(DataSource.class).toProvider(DatabaseConfig.class).asEagerSingleton();
        bind(HelloService.class);
    }
}
