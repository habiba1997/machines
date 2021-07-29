package com.java.main.profile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackageConstants {

	public static final String PACKAGE_MAIN = "com.java.main";

	public static final String PACKAGE_CONTROLLER = "com.java.main.controller";

	public static final String PACKAGE_CONFIGURATION = "com.java.main.configuration";

	public static final String PACKAGE_FUNCTIONAL_LIST = "com.java.main.repositories";

	public static final String PACKAGE_DATA_ACCESS = "com.java.main.services";

	public static final String PACKAGE_DTO_REQUEST = "com.aptar.mes.core.business.request"; // Include runtime and public api request

	public static final String PACKAGE_PUBLIC_API_CONTROLLER = "com.aptar.mes.external.api.controller";

}
