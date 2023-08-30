package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = "web")
@PropertySource("classpath:db.properties")
public class AppConfig {

    private final Environment e;

    @Autowired
    public AppConfig(Environment e) {
        this.e = e;
    }

    @Bean
    public DataSource getDataSourse() {
        return new DriverManagerDataSource() {
            {
                setUrl(e.getProperty("db.url"));
                setDriverClassName(Objects.requireNonNull(e.getProperty("db.driver")));
                setUsername(e.getProperty("db.username"));
                setPassword(e.getProperty("db.password"));
            }
        };
    }

    @Bean
    public Properties getProperties() {
        return new Properties() {
            {
            put("hibernate.dialect", e.getProperty("hibernate.dialect"));
            put("hibernate.show_sql", e.getProperty("hibernate.show_sql"));
            put("hibernate.hbm2ddl.auto", e.getProperty("hibernate.hbm2ddl.auto"));
            }
        };
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory() {
        return new LocalContainerEntityManagerFactoryBean() {
            {
                setDataSource(getDataSourse());
                setJpaProperties(getProperties());
                setPackagesToScan("hibernate", "web");
                JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
                setJpaVendorAdapter(adapter);
            }
        };
    }

    @Bean
    public PlatformTransactionManager getTransaction() {
        return new JpaTransactionManager() {
            {
            setEntityManagerFactory(localEntityManagerFactory().getObject());
            }
        };
    }
}
