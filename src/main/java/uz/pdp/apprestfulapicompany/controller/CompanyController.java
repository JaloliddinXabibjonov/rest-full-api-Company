package uz.pdp.apprestfulapicompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprestfulapicompany.entity.Company;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.CompanyDto;
import uz.pdp.apprestfulapicompany.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     *a method that takes information about all companies
     * @param companyDto
     * @return List<Company>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody CompanyDto companyDto) {
        Result result = companyService.addCompany(companyDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     *a method that takes information about all companies
     * @return ResponseEntity<List<Company>>
     */
    @GetMapping
    public ResponseEntity<List<Company>> get(){
        return ResponseEntity.ok(companyService.getCompany());
    }

    /**
     * get company information by id
     * @param id
     * @return Company
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Integer id){
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    /**
     * kompaniya ma`lumotlarini tahrirlovchi metod
     * @param id
     * @param companyDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto){
        Result result = companyService.edit(id, companyDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * Company data deletion method
     * @param id
     * @return  Result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = companyService.delete(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
