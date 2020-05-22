package shift.management.service;

import shift.management.entity.User;

public interface UserService {
    public User createAccount(User user) throws Exception;

    public User updateAccount(User user) throws Exception;

}
