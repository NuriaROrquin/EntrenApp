package ar.edu.unlam.tallerweb1.delivery.models;

public class DatosRegister {
    private String email;
    private String password;
    private String verificatedPassword;
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificatedPassword() {
        return verificatedPassword;
    }

    public void setVerificatedPassword(String verificatedPassword) {
        this.verificatedPassword = verificatedPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "DatosRegister{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verificatedPassword='" + verificatedPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
