package uz.pdp.apprestfulapicompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apprestfulapicompany.entity.Company;
import uz.pdp.apprestfulapicompany.entity.Department;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.DepartmentDto;
import uz.pdp.apprestfulapicompany.repository.CompanyRepository;
import uz.pdp.apprestfulapicompany.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    /**
     * ADD NEW DEPARTMENT
     * @param departmentDto
     * @return Result
     */
    public Result add(DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByNameAndCompany_Id(departmentDto.getName(), departmentDto.getCompanyId());
        if (exists)
            return new Result("This department and company already exist", false);
        Department department=new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Company not found", false);
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("Successfully added",true);
    }

    /**
     * GET ALL DEPARTMENTS
     * @return List<Department>
     */
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    /**
     * GET DEPARTMENT BY ID
     * @param id
     * @return  Department
     */
    public Department getById(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent())
            return optionalDepartment.get();
        return new Department();
    }

    /**
     * EDIT DEPARTMENT
     * @param id
     * @param departmentDto
     * @return Result
     */
    public Result edit(Integer id, DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Department not found", false);
        boolean idNot = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (idNot)
            return new Result("This department already exist", false);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Company not found", false);
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("Successfully edited", true);
    }

    /**
     * delete department by id
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Department not found", false);
        departmentRepository.deleteById(id);
        return new Result("Department successfully deleted",true);
    }
}
