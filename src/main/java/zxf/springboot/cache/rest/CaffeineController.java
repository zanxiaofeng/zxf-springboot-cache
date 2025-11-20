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
    public List<String> caches() {
        List<String> result = new ArrayList<>();
        result.addAll(addressesCacheManager.getCacheNames());
        result.addAll(employmentsCacheManager.getCacheNames());
        return result;
    }

    @GetMapping("/caffeine/addresses")
    public Set<Object> addressesCache() {
        CaffeineCache addressesCache = (CaffeineCache) addressesCacheManager.getCache("addresses");
        Cache cache = addressesCache.getNativeCache();
        return cache.asMap().keySet();
    }

    @GetMapping("/caffeine/employments")
    public Set<Object> employmentsCache() {
        CaffeineCache employmentsCache = (CaffeineCache) employmentsCacheManager.getCache("employments");
        Cache cache = employmentsCache.getNativeCache();
        return cache.asMap().keySet();
    }
}
