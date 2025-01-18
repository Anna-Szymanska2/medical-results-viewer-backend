package pw.telm.telmbackend.DTOs.model;

public class PatientDto {
    private Integer id;
    private String name;

    public PatientDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PatientDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}