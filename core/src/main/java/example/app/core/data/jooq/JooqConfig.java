package example.app.core.data.jooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultCloseableDSLContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JooqConfig {

    @Bean
    public DSLContext dslContext(
            DataSource dataSource
    ) {
        return new DefaultCloseableDSLContext(
                new DataSourceConnectionProvider(dataSource),
                SQLDialect.H2,
                new Settings()
                        .withRenderSchema(false)
        );
    }
}
