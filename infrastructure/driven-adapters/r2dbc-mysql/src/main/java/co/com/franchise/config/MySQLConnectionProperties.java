package co.com.franchise.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "infrastructure.driven-adapters.my-sql")
public class MySQLConnectionProperties {

    private String host;
    private Integer port;
    private String database;
    private String username;
    private String password;
    private String driver;
}
