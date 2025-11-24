package zxf.springboot.cache.rest;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CaffeineController {
    @Autowired
    @Qualifier("addressesCacheManager")
    private CacheManager addressesCacheManager;
    @Autowired
    @Qualifier("employmentsCacheManager")
    private CacheManager employmentsCacheManager;

    @GetMapping("/caffeine/caches")
    public Map<String, Collection<String>> caches() {
        Map<String, Collection<String>> result = new HashMap<>();
        result.put("addresses", addressesCacheManager.getCacheNames());
        result.put("employments", employmentsCacheManager.getCacheNames());
        return result;
    }

    @GetMapping("/caffeine/addresses")
    public Map<String, Set<Object>> addressesCache() {
        Map<String, Set<Object>> result = new HashMap<>();
        for (String cacheName : addressesCacheManager.getCacheNames()) {
            CaffeineCache addressesCache = (CaffeineCache) addressesCacheManager.getCache(cacheName);
            Cache cache = addressesCache.getNativeCache();
            result.put(cacheName, cache.asMap().keySet());
        }
        return result;
    }

    @GetMapping("/caffeine/employments")
    public Map<String, Set<Object>> employmentsCache() {
        Map<String, Set<Object>> result = new HashMap<>();
        for (String cacheName : employmentsCacheManager.getCacheNames()) {
            CaffeineCache addressesCache = (CaffeineCache) employmentsCacheManager.getCache(cacheName);
            Cache cache = addressesCache.getNativeCache();
            result.put(cacheName, cache.asMap().keySet());
        }
        return result;
    }
}
