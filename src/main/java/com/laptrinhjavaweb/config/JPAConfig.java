package com.laptrinhjavaweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // xác định class để config 
@EnableJpaRepositories(basePackages = {"com.laptrinhjavaweb.repository"})  //Bật tính năng cung cấp hàm chung của Repository
@EnableTransactionManagement //Bật tính năng Entity Transaction
public class JPAConfig {
	
	//Khởi tạo, cài đặt Entity Manager Factory và trả về entity manager
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource()); //Cung cấp driverName, user, pass...
		//Đây là chất xúc tác ở giữa để cho NewEntity và bảng New có thể giao tiếp, liên kết, matching được
		em.setPersistenceUnitName("persistence-data"); //Định nghĩa trong file /resources/.../persistence.xml
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties()); //Bật tính năng tự động tạo table từ Entity, có nhiều option
		return em;  //Trả về entity manager để thực hiện câu SQL / là Connection trong mô hình jdbc
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcbasicfree");
		dataSource.setUsername("root");
		dataSource.setPassword("093201");
		return dataSource;
	}
	
	//Có nhiều option cho các bảng
	Properties additionalProperties() {
		Properties properties = new Properties();
		//properties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); //Dựa vào class mà generate table
		//properties.setProperty("hibernate.hbm2ddl.auto", "create"); //Một số máy dùng cách trên khi tắt server thì bị mất bảng 
		properties.setProperty("hibernate.hbm2ddl.auto", "none");		//Khi đã tạo các table ổn định
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true"); //Config cho LAZY many to many
		return properties;
	}
	
	//Tự động quản lý transaction trong JPA
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
