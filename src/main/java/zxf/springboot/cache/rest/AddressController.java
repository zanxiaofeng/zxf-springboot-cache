package zxf.springboot.cache.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zxf.springboot.cache.domain.Address;
import zxf.springboot.cache.service.AddressService;

@Slf4j
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable("id") Long id) {
        log.info("getAddressById: {}", id);
        return addressService.getById(id);
    }

    @PutMapping("/address")
    public Address updateAddress(@RequestBody Address address) {
        log.info("updateAddress: {}", address);
        return addressService.update(address);
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        log.info("deleteAddress: {}", id);
        addressService.deleteById(id);
    }

    @DeleteMapping("/address/all")
    public void deleteAllAddresses() {
        log.info("deleteAllAddresses");
        addressService.deleteAll();
    }
}
