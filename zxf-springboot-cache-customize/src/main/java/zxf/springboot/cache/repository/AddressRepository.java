package zxf.springboot.cache.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import zxf.springboot.cache.domain.Address;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class AddressRepository {
    private final ConcurrentHashMap<Long, Address> addressMap = new ConcurrentHashMap<>();

    public Address getById(long addressId) {
        log.info("getById: {}", addressId);
        return addressMap.get(addressId);
    }

    public Address update(Address address) {
        log.info("update: {}", address);
        addressMap.put(address.addressId(), address);
        return address;
    }

    public void deleteById(long addressId) {
        log.info("deleteById: {}", addressId);
        addressMap.remove(addressId);
    }

    public void deleteByAll() {
        log.info("deleteByAll");
        addressMap.clear();
    }
}
