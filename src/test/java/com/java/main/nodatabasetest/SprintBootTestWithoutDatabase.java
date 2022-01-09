package com.java.main.nodatabasetest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@ComponentScan(basePackages = PackageConstants.PACKAGE_MAIN,
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com\\.java\\.main\\.services\\..*", "com\\.java\\.main\\.repositories\\..*" }))
public @interface SprintBootTestWithoutDatabase {
}
