public class User {
    private String email;
    private String password;
    private final int id;

    public User(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
