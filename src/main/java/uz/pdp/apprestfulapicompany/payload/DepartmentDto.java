package uz.pdp.apprestfulapicompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Department nomi bo`sh bo`lmasligi kerak")
    private String name;
    @NotNull(message = "Kompaniya bo`sh bo`lmasligi kerak")
    private Integer companyId;
}
