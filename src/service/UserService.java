package service;

import model.User;
import exception.ValidationException;
import util.RegexUtil;

public class UserService {
    public void validateUser(User user) throws ValidationException {
        if (!RegexUtil.isValidEmail(user.getEmail())) {
            throw new ValidationException("Invalid email format.");
        }
    }
}

