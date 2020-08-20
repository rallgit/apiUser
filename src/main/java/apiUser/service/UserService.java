package apiUser.service;

import java.util.Map;
import java.util.Optional;
import apiUser.entity.User;


public interface UserService {

	Optional<User> insertUser(User user, Map<String, String> headers);
}
