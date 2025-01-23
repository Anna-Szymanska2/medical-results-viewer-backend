package pw.telm.telmbackend.DTOs.model;

public record RegisterDto(Integer login, char[] password, String email)  {
}
