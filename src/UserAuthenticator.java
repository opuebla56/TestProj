import java.util.Map;

public class UserAuthenticator {
    private UserRepository userRepository;

    public UserAuthenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        Map<String, String> credentials = userRepository.loadUserCredentials();
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }

    public boolean createAccount(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return userRepository.saveUser(username, password);
    }
}