package shift.management.service;

import shift.management.entity.User;

import java.util.List;

public interface UserService {
    public User createAccount(User user) throws Exception;

    public User updateAccount(User user) throws Exception;

    public List<User> searchByUsernameAndName(String keyword);

}
