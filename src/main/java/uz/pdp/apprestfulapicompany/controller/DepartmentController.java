package uz.pdp.apprestfulapicompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprestfulapicompany.entity.Department;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.DepartmentDto;
import uz.pdp.apprestfulapicompany.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * ADD NEW DEPARTMENT
     * @param departmentDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody DepartmentDto departmentDto){
        Result result = departmentService.add(departmentDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL DEPARTMENTS
     * @return ResponseEntity<List<Department>>
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
       return ResponseEntity.ok(departmentService.getAll());
    }

    /**
     * GET DEPARTMENT BY ID
     * @param id
     * @return  ResponseEntity<Department>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id){
       return ResponseEntity.ok(departmentService.getById(id));
    }

    /**
     * EDIT DEPARTMENT
     * @param id
     * @param departmentDto
     * @return ResuResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){
        Result result = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * delete department by id
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = departmentService.delete(id);
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
