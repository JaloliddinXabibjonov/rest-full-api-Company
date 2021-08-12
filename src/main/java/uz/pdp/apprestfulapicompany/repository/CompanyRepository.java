package uz.pdp.apprestfulapicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprestfulapicompany.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpNameAndDirectorName(String corpName, String directorName);

}
