package com.gestordecontactos.infra;

import com.gestordecontactos.utils.ConfigProperties;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.flywaydb.core.Flyway;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * Configuración de la base de datos SQLite.
 */
public class DatabaseConfig implements Provider<DataSource> {
    private final ConfigProperties properties;

    @Inject
    public DatabaseConfig(ConfigProperties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource get() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(properties.get("db.url", "jdbc:sqlite:contacts.db"));

        Flyway.configure()
                .dataSource(ds)
                .locations("classpath:db/migration")
                .load()
                .migrate();
        return ds;
    }
}
