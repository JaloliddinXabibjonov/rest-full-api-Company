package uz.pdp.apprestfulapicompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "FISH bo`sh bo`lmasligi kerak")
    private String name;
    @NotNull(message = "Telefon raqami bo`sh bo`lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "Ko`cha nomi bo`sh bo`lmasligi kerak")
    private String street;
    @NotNull(message = "uy raqami bo`sh bo`lmasligi kerak")
    private String homeNumber;
    @NotNull(message = "Department bo`sh bo`lmasligi kerak")
    private Integer departmentId;

}
