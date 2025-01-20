package pw.telm.telmbackend.dto;

public record RegisterDto(Integer login, char[] password, char[] password2, char[] email)  {
}
