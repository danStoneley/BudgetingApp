public class UserProfile {
    private String firstName;
    private String lastName;
    private String location;
    private String birthdate;

    UserProfile(String firstName, String lastName, String location, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" + location + "\n" + birthdate;
    }
}
