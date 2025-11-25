package zxf.springboot.cache.rest;

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
    private CacheManager cacheManager;

    @GetMapping("/caffeine/caches")
    public Map<String, List<String>> caches() {
        return cacheInfo(cacheManager);
    }

    private Map<String, List<String>> cacheInfo(CacheManager cacheManager) {
        Map<String, List<String>> result = new HashMap<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            CaffeineCache cache = (CaffeineCache) cacheManager.getCache(cacheName);
            List<String> keys = cache.getNativeCache().asMap().keySet().stream().map(Object::toString).toList();
            result.put(cacheName, keys);
        }
        return result;
    }
}
