package com.machines.main.cache;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.machines.main.profile.CacheConstants;
import com.machines.main.profile.SpringProfiles;

/**
 * This a fake cache component only used by @CacheServiceTest.
 */
@Component
@Profile(SpringProfiles.CACHE_TEST)
class CacheServiceTestCustomComponentService {
	private AtomicInteger atomic = new AtomicInteger(0);

	@Cacheable(CacheConstants.CACHE_TEST)
	public int incrementCache() {
		return atomic.incrementAndGet();
	}
}