package co.com.franchise.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class MySQLConnectionPool {

    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;

	@Bean
	public ConnectionFactory connectionFactory(MySQLConnectionProperties properties) {
        var connection = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, properties.getDriver())
                .option(HOST, properties.getHost())
                .option(PORT, properties.getPort())
                .option(DATABASE, properties.getDatabase())
                .option(USER, properties.getUsername())
                .option(PASSWORD, properties.getPassword())
                .build());

        var poolConfiguration = ConnectionPoolConfiguration.builder(connection)
                .name("api-mysql-connection-pool")
                .initialSize(INITIAL_SIZE)
                .maxSize(MAX_SIZE)
                .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                .build();

		return new ConnectionPool(poolConfiguration);
	}
}