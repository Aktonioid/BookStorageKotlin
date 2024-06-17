package com.bookStorage.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import java.util.Properties
import javax.sql.DataSource

@Configuration
class DatabaseConfig(var env: Environment) {

    @Bean
    fun dataSource():DataSource{

        var config: HikariConfig = HikariConfig()

        config.jdbcUrl = env.get("jdbc.url")
        config.username = env.get("jdbc.username")
        config.password = env.get("jdbc.password")
//        config.driverClassName = env.get("jdbc.driverClassName")
        config.addDataSourceProperty("cachePrepStmts", env.get("hikari.cachePrepStmts"))
        config.addDataSourceProperty("prepStmtCacheSize", env.get("hikari.prepStmtCacheSize"))
        config.addDataSourceProperty("prepStmtCacheSqlLimit", env.get("hikari.prepStmtCacheSqlLimit"))

        return HikariDataSource(config)
    }

    @Bean
    fun HibernateProperties(): Properties{

        var properties:Properties = Properties();

        properties.put("hibernate.dialect", env.get("hibernate.dialect"))
        properties.put("hibernate.show_sql", env.get("hibernate.show_sql"))
        properties.put("hibernate.format_sql", env.get("hibernate.format_sql"))
        properties.put("hibernate.highlight_sql", env.get("hibernate.highlight_sql"))
        properties.put("logging.level.org.hibernate.SQL", env.get("logging.level.org.hibernate.SQL"))
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"))

        return properties
    }

    @Bean(name = arrayOf("entityManagerFactory"))
    fun LocalSessionFactory(): LocalSessionFactoryBean {
        var localSessionFactoryBean: LocalSessionFactoryBean = LocalSessionFactoryBean()

        localSessionFactoryBean.setDataSource(dataSource())
        localSessionFactoryBean.setPackagesToScan(*arrayOf("com.bookStorage.core.models"))
        localSessionFactoryBean.hibernateProperties = HibernateProperties()

        return localSessionFactoryBean
    }

    fun HibernateTransactionManager(): HibernateTransactionManager{
        val manager: HibernateTransactionManager = HibernateTransactionManager()
        manager.sessionFactory = LocalSessionFactory().`object`

        return  manager
    }

}