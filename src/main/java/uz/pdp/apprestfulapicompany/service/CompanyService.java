package uz.pdp.apprestfulapicompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.apprestfulapicompany.entity.Address;
import uz.pdp.apprestfulapicompany.entity.Company;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.CompanyDto;
import uz.pdp.apprestfulapicompany.repository.AddressRepository;
import uz.pdp.apprestfulapicompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     *a method that takes information about all companies
     * @return List<Company>
     */
    public List<Company> getCompany(){
        return companyRepository.findAll();
    }

    /**
     * get company information by id
     * @param id
     * @return Company
     */
    public Company getCompanyById(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent())
            return optionalCompany.get();
        return null;
    }

    /**
     * Add new company method
     * @param companyDto
     * @return Result
     */
    public Result addCompany(CompanyDto companyDto){
        boolean name = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        if (name)
            return new Result("Bunday korporatsiya va boshqaruv nomli kompaniya mavjud",false);
        Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new Result("Muvaffaqiyatli saqlandi",true);
    }

    /**
     * edit company by id
     * @param id
     * @param companyDto
     * @return Result
     */
    public Result edit(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Bunday kompaniya mavjud emas", false);
        Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new Result("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * Company data deletion method
     * @param id
     * @return  Result
     */
    public Result delete(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Bunday kompaniya mavjud emas",false);
        companyRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
