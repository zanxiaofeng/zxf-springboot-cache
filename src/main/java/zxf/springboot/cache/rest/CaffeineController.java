package zxf.springboot.cache.rest;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.ConcurrentMap;

@Repository
public class CaffeineController {
    @Autowired
    private CacheManager addressesCacheManager;
    @Autowired
    private CacheManager employmentsCacheManager;

    @GetMapping("/caffeine/addresses")
    public ConcurrentMap<Object, Object> addressesCache() {
        CaffeineCache addressesCache = (CaffeineCache) addressesCacheManager.getCache("addresses");
        Cache cache = addressesCache.getNativeCache();
        return cache.asMap();
    }

    @GetMapping("/caffeine/employments")
    public ConcurrentMap<Object, Object> employmentsCache() {
        CaffeineCache employmentsCache = (CaffeineCache) addressesCacheManager.getCache("employments");
        Cache cache = employmentsCache.getNativeCache();
        return cache.asMap();
    }
}
