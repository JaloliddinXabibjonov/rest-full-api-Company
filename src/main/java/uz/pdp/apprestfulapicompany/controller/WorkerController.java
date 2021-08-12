package uz.pdp.apprestfulapicompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprestfulapicompany.entity.Worker;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.WorkerDto;
import uz.pdp.apprestfulapicompany.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    /**
     * ADD NEW WORKER
     * @param workerDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody WorkerDto workerDto){
        Result result = workerService.add(workerDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL WORKERS
     * @return ResponseEntity<List<Worker>>
     */
    @GetMapping
    public ResponseEntity<List<Worker>> getAll(){
        return ResponseEntity.ok(workerService.getAll());
    }

    /**
     * GET WORKER BY ID
     * @param id
     * @return ResponseEntity<Worker>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Integer id){
        Worker worker = workerService.getById(id);
        return ResponseEntity.ok(worker);
    }

    /**
     * EDIT WORKER
     * @param id
     * @param workerDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto){
        Result result = workerService.edit(id, workerDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE WORKER BY ID
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = workerService.delete(id);
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
