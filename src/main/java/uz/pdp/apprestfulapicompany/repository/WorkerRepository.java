package uz.pdp.apprestfulapicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprestfulapicompany.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsAllByPhoneNumberAndIdNot(String phoneNumber, Integer id);
    boolean existsAllByPhoneNumber(String phoneNumber);

}
