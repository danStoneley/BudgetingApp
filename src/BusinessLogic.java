import java.util.List;


public class BusinessLogic {
    private final UserDAO userDAO;

    BusinessLogic(User user) {
        userDAO = new UserDAO();
    }


    public User createUser(String email, String password){
        return userDAO.createUser(email, password);
    }

}

