package uz.pdp.apprestfulapicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprestfulapicompany.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompany_Id(String name, Integer company_id);
    boolean existsByNameAndIdNot(String name, Integer id);

}
