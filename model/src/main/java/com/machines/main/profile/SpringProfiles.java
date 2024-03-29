package com.machines.main.profile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SpringProfiles {

	// this profile is for the tests that don't require database at all (NOT related to GlobalRepositoryJpaTest bcause
	// this test require hsql or test profile
	public static final String NO_DATABASE = "no-database";

	public static final String DEVELOPMENT = "dev";
	public static final String CACHE = "cache";
	public static final String CACHE_TEST = "cache-service-test-profile";
	public static final String TEST = "test";

	public static final String H2 = "h2";
	public static final String HSQL = "hsql";

	public static final String LOG = "log";
	public static final String SQL_LOG = "sql-log";

	public static final String NO_TEST = "!test";
}
