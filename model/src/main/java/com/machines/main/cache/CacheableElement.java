package com.machines.main.cache;

import java.io.Serializable;

public interface CacheableElement<K> extends Serializable {
	K getCacheKeyElement();
}
