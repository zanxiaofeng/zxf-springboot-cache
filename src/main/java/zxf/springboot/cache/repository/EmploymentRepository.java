package zxf.springboot.cache.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import zxf.springboot.cache.domain.Address;
import zxf.springboot.cache.domain.Employment;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class EmploymentRepository {
    private final ConcurrentHashMap<Long, Employment> employmentMap = new ConcurrentHashMap<>();

    public Employment getById(long employmentId) {
        log.info("getById: {}", employmentId);
        return employmentMap.get(employmentId);

    }

    public Employment update(Employment employment) {
        log.info("update: {}", employment);
        employmentMap.put(employment.employmentId(), employment);
        return employment;
    }

    public void deleteById(long employmentId) {
        log.info("deleteById: {}", employmentId);
        employmentMap.remove(employmentId);
    }

    public void deleteByAll() {
        log.info("deleteByAll");
        employmentMap.clear();
    }
}
