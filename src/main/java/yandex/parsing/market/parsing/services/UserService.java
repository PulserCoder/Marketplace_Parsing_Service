package yandex.parsing.market.parsing.services;


import org.springframework.stereotype.Service;
import yandex.parsing.market.parsing.models.User;
import yandex.parsing.market.parsing.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
