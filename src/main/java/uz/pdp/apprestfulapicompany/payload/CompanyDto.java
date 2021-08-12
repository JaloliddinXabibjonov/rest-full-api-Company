package uz.pdp.apprestfulapicompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "Korporatsiya nomi bo`sh bo`lmasligi kerak")
    private String corpName;
    @NotNull(message = "Boshqaruv nomi bo`sh bo`lmasligi kerak")
    private String directorName;
    @NotNull(message = "Ko`cha nomi bo`sh bo`lmasligi kerak!")
    private String street;
    @NotNull(message = "Uy raqami bo`sh bo`lmasligi kerak!")
    private String homeNumber;
}
