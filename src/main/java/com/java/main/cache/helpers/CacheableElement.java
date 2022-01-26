package com.java.main.cache.helpers;

import java.io.Serializable;

public interface CacheableElement<K> extends Serializable {
	K getCacheKeyElement();
}
