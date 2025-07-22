package zxf.springboot.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zxf.springboot.cache.domain.Employment;
import zxf.springboot.cache.repository.EmploymentRepository;

@Slf4j
@Service
@CacheConfig(cacheNames = "employments", cacheManager = "employmentsCacheManager")
public class EmploymentService {
    @Autowired
    EmploymentRepository employmentRepository;

    @Cacheable()
    public Employment getById(long employmentId) {
        log.info("getById: {}", employmentId);
        return employmentRepository.getById(employmentId);
    }

    @CachePut(key = "#employment.employmentId")
    public Employment update(Employment employment) {
        log.info("update: {}", employment);
        return employmentRepository.update(employment);
    }

    @CacheEvict(key = "#employmentId")
    public void deleteById(long employmentId) {
        log.info("deleteById: {}", employmentId);
        employmentRepository.deleteById(employmentId);
    }

    @CacheEvict(allEntries = true)
    public void deleteAll() {
        log.info("deleteAll");
        employmentRepository.deleteByAll();
    }
}
