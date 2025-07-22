package zxf.springboot.cache.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import zxf.springboot.cache.domain.Employment;
import zxf.springboot.cache.service.EmploymentService;

@Slf4j
@RestController
public class EmploymentController {
    @Autowired
    private EmploymentService employmentService;

    @GetMapping("/employment/{id}")
    public Employment getEmployment(@PathVariable("id") long id) {
        log.info("getEmployment: {}", id);
        return employmentService.getById(id);
    }

    @PutMapping("/employment")
    public Employment updateEmployment(Employment employment) {
        log.info("updateEmployment: {}", employment);
        return employmentService.update(employment);
    }

    @GetMapping("/employment/delete/{id}")
    public void deleteEmployment(@PathVariable("id") long id) {
        log.info("deleteEmployment: {}", id);
        employmentService.deleteById(id);
    }

    @GetMapping("/employment/deleteAll")
    public void deleteAllEmployment() {
        log.info("deleteAllEmployment");
        employmentService.deleteAll();
    }
}
