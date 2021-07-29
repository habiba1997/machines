package com.java.main.nodatabasetest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import com.java.main.profile.PackageConstants;
import com.java.main.profile.SpringProfiles;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@ActiveProfiles(profiles = { SpringProfiles.NO_DATABASE })
@EnableAutoConfiguration(exclude = { KafkaAutoConfiguration.class, DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
@ComponentScan(basePackages = PackageConstants.PACKAGE_MAIN, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com\\.java\\.main\\.servicesImpl\\..*", "com\\.java\\.main\\.services\\..*",
		"com\\.java\\.main\\.repositories\\..*" }))
public @interface SprintBootTestWithoutDatabase {
}
