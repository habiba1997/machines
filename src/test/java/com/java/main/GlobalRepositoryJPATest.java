package com.java.main;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.main.profile.SpringProfiles;

import lombok.extern.slf4j.Slf4j;

/**
 * This class tests all repository automatically to track JPA mapping issues.
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestApplication.class)
//In short, @Profile defines a profile like a Debug profile and a Production profile etc...
// However @ActiveProfiles comes into picture in case of an ApplicationContext
// and defines which profiles should be active if respective ApplicationContext is being used.
@ActiveProfiles(profiles = { SpringProfiles.HSQL, SpringProfiles.TEST })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class GlobalRepositoryJPATest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Environment environment;

	/**
	 * Call the method findAll on each DB repositories that implement this method. This test is very usefull to detect JPA mapping issues with DB.
	 * 
	 * If this unit test fails, there are 2 possibilities:
	 * 
	 * either the JPA mapping is not correct
	 * 
	 * either creation test scripts SQL are out of date or do not reflect the real SQL Server.
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void testFindAll() {
		Map<String, CrudRepository> map = findAllCrudRepositories();
		log.info("{} repositories", map.size());

		Set<String> beansToIgnore = new java.util.HashSet<>();

		Set<String> beansInErrors = new java.util.HashSet<>();
		for (Entry<String, CrudRepository> repository : map.entrySet()) {
			if (beansToIgnore.contains(repository.getKey())) {
				log.info("findAll on {} : ignored", repository.getKey());
			} else {
				try {
					log.info("findAll on {}", repository.getKey());
					repository.getValue().findAll();
				} catch (DataAccessException exception) {
					log.error("cannot call findAll on " + repository.getKey(), exception);
					beansInErrors.add(repository.getKey());
				}
			}
		}
		if (!beansInErrors.isEmpty()) {
			Assert.fail(beansInErrors.size() + " repositories in errors on " + map.size() + " repositories: " + String.join(",", beansInErrors));
		}
	}

	/**
	 * Return all CrudRepositories beans declared in spring context.
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, CrudRepository> findAllCrudRepositories() throws BeansException {

		Map<String, CrudRepository> beansOfType = applicationContext.getBeansOfType(CrudRepository.class);
		// Sort by beanName
		return beansOfType
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

}
