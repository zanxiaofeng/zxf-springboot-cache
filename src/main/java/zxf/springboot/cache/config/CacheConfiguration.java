package zxf.springboot.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {
    @Primary
    @Bean(name = "addressesCacheManager")
    public CacheManager addressesCacheManager() {
        var caffeine = Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES);
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean(name = "employmentsCacheManager")
    public CacheManager employmentsCacheManager() {
        var caffeine = Caffeine.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES);
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
