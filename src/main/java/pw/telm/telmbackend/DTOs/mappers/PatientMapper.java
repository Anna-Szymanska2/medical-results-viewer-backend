package pw.telm.telmbackend.DTOs.mappers;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import pw.telm.telmbackend.model.Patient;


@Component

public class PatientMapper {

    public static PatientDto toPatientDto (Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getIdPatient());
        patientDto.setName(patient.getName());

        return patientDto;
    }
}
