package com.java.main;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.java.main.profile.PackageConstants;

@EnableAutoConfiguration
@Configuration
// specify the package that will scanned (could be a whole module or justa folder)
@ComponentScan(basePackages = { PackageConstants.PACKAGE_MAIN })
public class SpringBootTestApplication {

}
