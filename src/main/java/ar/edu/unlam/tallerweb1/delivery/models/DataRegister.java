package ar.edu.unlam.tallerweb1.delivery.models;

public class DataRegister {
    private String email;
    private String password;
    private String verificatedPassword;
    private long role;
    private Long age;
    private String name;

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

    public void setRole(long role) {
        this.role = role;
    }

    public long getRole() {
        return role;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataRegister{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verificatedPassword='" + verificatedPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
