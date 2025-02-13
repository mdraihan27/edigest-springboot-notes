package journal.services;

import journal.entity.JournalTemplate;
import journal.entity.UserTemplate;
import journal.repo.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserTemplate> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserTemplate> getUserById(ObjectId id){
        return userRepository.findById(id);
    }

    public void createUser(UserTemplate userTemplate){
        userRepository.save(userTemplate);
    }

    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }

    public UserTemplate getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
