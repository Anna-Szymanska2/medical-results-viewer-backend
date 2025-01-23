package pw.telm.telmbackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private Integer login;
    private String role;
    private String token;
    private Integer id;
    private String email;

    public UserDto() {
    }

    UserDto(Builder builder) {
        this.login = builder.login;
        this.role = builder.role;

    }
    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {
        private Integer login;
        private String role;

        public Builder login(Integer login) {
            this.login = login;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }


        public UserDto build() {
            return new UserDto(this);
        }
    }

}
