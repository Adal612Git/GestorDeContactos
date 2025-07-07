package com.gestordecontactos.app;

import com.gestordecontactos.infra.DatabaseConfig;
import com.gestordecontactos.dao.ContactoDAO;
import com.gestordecontactos.dao.SQLiteContactoDAO;
import com.gestordecontactos.service.HelloService;
import com.gestordecontactos.service.ContactService;
import com.gestordecontactos.utils.ConfigProperties;
import com.gestordecontactos.i18n.ResourceBundleProvider;
import com.google.inject.AbstractModule;
import java.util.ResourceBundle;
import javax.sql.DataSource;

/**
 * Configuración básica de Guice.
 */
public class AppConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigProperties.class).asEagerSingleton();
        bind(DataSource.class).toProvider(DatabaseConfig.class).asEagerSingleton();
        bind(ContactoDAO.class).to(SQLiteContactoDAO.class).asEagerSingleton();
        bind(HelloService.class);
        bind(ContactService.class).asEagerSingleton();
        bind(ResourceBundle.class).toProvider(ResourceBundleProvider.class).asEagerSingleton();
    }
}
