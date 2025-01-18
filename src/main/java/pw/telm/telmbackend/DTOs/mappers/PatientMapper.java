package pw.telm.telmbackend.DTOs.mappers;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import pw.telm.telmbackend.model.Patient;

/**
 * Mapper class to convert between domain entities and DTOs for Patient objects.
 */
@Component

public class PatientMapper {
    /**
     * Converts a Patient entity to a PatientDto.
     *
     * @param patient the Patient entity to convert
     * @return a PatientDto object containing the data from the Patient entity
     */
    public static PatientDto toPatientDto (Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getIdPatient());
        patientDto.setName(patient.getName());

        return patientDto;
    }
}
