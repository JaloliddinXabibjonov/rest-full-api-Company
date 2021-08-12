package uz.pdp.apprestfulapicompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.apprestfulapicompany.entity.Address;
import uz.pdp.apprestfulapicompany.entity.Department;
import uz.pdp.apprestfulapicompany.entity.Worker;
import uz.pdp.apprestfulapicompany.entity.template.Result;
import uz.pdp.apprestfulapicompany.payload.WorkerDto;
import uz.pdp.apprestfulapicompany.repository.AddressRepository;
import uz.pdp.apprestfulapicompany.repository.DepartmentRepository;
import uz.pdp.apprestfulapicompany.repository.WorkerRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * ADD NEW WORKER
     * @param workerDto
     * @return Result
     */
    public Result add(@Valid @RequestBody WorkerDto workerDto){
        //CHECK PHONE NUMBER
        boolean phoneNumber = workerRepository.existsAllByPhoneNumber(workerDto.getPhoneNumber());
        if (phoneNumber)
            return new Result("This phone number already exist",false);

        //CHECK DEPARTMENT
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Department not found",false);

        Address address=new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Result("Successfully added",true);
    }

    /**
     * GET ALL WORKERS
     * @return List<Worker>
     */
    public List<Worker> getAll(){
        return workerRepository.findAll();
    }

    /**
     * GET WORKER BY ID
     * @param id
     * @return Worker
     */
    public Worker getById(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent())
            return optionalWorker.get();
        return null;
    }

    /**
     * EDIT WORKER
     * @param id
     * @param workerDto
     * @return Result
     */
    public Result edit(Integer id, WorkerDto workerDto){
        boolean idNot = workerRepository.existsAllByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (idNot)
            return new Result("This worker already exist",false);

        //CHECK DEPARTMENT
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Department not found",false);

        Address address=new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Result("Successfully added",true);
    }

    /**
     * DELETE WORKER BY ID
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new Result("Worker not found",false);
        workerRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
