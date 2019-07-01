package com.f1soft.adminservice.configuration;//package com.f1soft.adminservice.configuration;
//
//import com.f1soft.Admin;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
//import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.f1soft.adminservice.repository",
//        entityManagerFactoryRef = "entityManager",
//        transactionManagerRef = "transactionManager")
//public class DataSourceConfig {
//
//    private final PersistenceUnitManager persistenceUnitManager;
//
//    public DataSourceConfig(ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
//        this.persistenceUnitManager = persistenceUnitManager.getIfAvailable();
//    }
//
//    @Bean
//    @ConfigurationProperties
//    public JpaProperties customerJpaProperties() {
//        return new JpaProperties();
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties
//    public DataSourceProperties customerDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties
//    public DataSource customerDataSource() {
//        return (DataSource) customerDataSourceProperties().initializeDataSourceBuilder()
//                .type(DataSource.class).build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean customerEntityManager(
//            JpaProperties customerJpaProperties) {
//        org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(
//                customerJpaProperties);
//        return builder
//                .dataSource(customerDataSource())
//                .packages(Admin.class)
//                .persistenceUnit("customersDs")
//                .build();
//    }
//
//    @Bean
//    @Primary
//    public JpaTransactionManager customerTransactionManager(
//            EntityManagerFactory customerEntityManager) {
//        return new JpaTransactionManager(customerEntityManager);
//    }
//
//    private org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(
//            JpaProperties customerJpaProperties) {
//        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(customerJpaProperties);
//        return new org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder(jpaVendorAdapter,
//                customerJpaProperties.getProperties(), this.persistenceUnitManager);
//    }
//
//    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
//        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setShowSql(jpaProperties.isShowSql());
//        adapter.setDatabase(jpaProperties.getDatabase());
//        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
//        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
//        return adapter;
//    }
//
//}
//
//
//
//
//
//
