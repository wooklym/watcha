package hello;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({DatabaseConfig.class})
@ComponentScan(basePackages = {"model"})
public class ServiceConfig {
	@Autowired
	@Qualifier("localEntityManager")
	EntityManagerFactory localEntityManagerFactory;
	
	@Primary
    @Bean(name="localTransactionManager")
    public PlatformTransactionManager localTransactionManager() throws Exception {
        return new JpaTransactionManager(localEntityManagerFactory);
    }
}
