package pw.telm.telmbackend.dto;

public record RegisterDto(Integer login, char[] password, String email)  {
}
