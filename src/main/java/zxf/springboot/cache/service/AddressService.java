package zxf.springboot.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zxf.springboot.cache.domain.Address;
import zxf.springboot.cache.repository.AddressRepository;

@Slf4j
@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Cacheable(value = "addresses", cacheManager = "addressesCacheManager")
    public Address getById(long addressId) {
        log.info("getById: {}", addressId);
        return addressRepository.getById(addressId);
    }

    @CachePut(value = "addresses", cacheManager = "addressesCacheManager", key = "#address.addressId")
    public Address update(Address address) {
        log.info("update: {}", address);
        return addressRepository.update(address);
    }

    @CacheEvict(value = "addresses", cacheManager = "addressesCacheManager", key = "#addressId")
    public void deleteById(long addressId) {
        log.info("deleteById: {}", addressId);
        addressRepository.deleteById(addressId);
    }

    @CacheEvict(value = "addresses", cacheManager = "addressesCacheManager", allEntries = true)
    public void deleteAll() {
        log.info("deleteAll");
        addressRepository.deleteByAll();
    }
}
