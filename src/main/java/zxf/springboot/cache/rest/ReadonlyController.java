package zxf.springboot.cache.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ReadonlyController {
    @Cacheable
    @GetMapping("/readonly")
    public String readonly(@RequestParam long id, @RequestParam String name) {
        log.info("readonly: {}, {}", id, name);
        return String.format("Id=%s, Name=%s", id, name);
    }
}