package uz.pdp.apprestfulapicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprestfulapicompany.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
