package journal.controller;


import journal.entity.UserTemplate;
import journal.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserTemplate> showAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("view/id/{id}")
    public Optional<UserTemplate> showUserById(@PathVariable ObjectId id){
        return userService.getUserById(id);
    }

    @GetMapping("view/username/{username}")
    public Optional<UserTemplate> showUserByUsername(@PathVariable String username){
        return Optional.ofNullable(userService.getUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity postNewUser(@RequestBody UserTemplate ut){
        UserTemplate user = userService.getUserByUsername(ut.getUsername());
        if(user==null) {
            userService.createUser(ut);
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("delete/id/{id}")
    public void deleteUserById(@PathVariable ObjectId id){
        userService.deleteUserById(id);
    }

    @PutMapping("update/id/{id}")
    public void updateUserById(@PathVariable ObjectId id, @RequestBody UserTemplate ut){
        UserTemplate oldUserTemplate = userService.getUserById(id).orElse(null);
        if(oldUserTemplate!=null){
            oldUserTemplate.setUsername(ut.getUsername());
            oldUserTemplate.setPassword(ut.getPassword());
        }
        userService.createUser(oldUserTemplate);
    }

    @PutMapping("update/username/{username}")
    public ResponseEntity updateUserByUsername(@PathVariable String username, @RequestBody UserTemplate ut){
        UserTemplate oldUserTemplate = userService.getUserByUsername(username);
        if(oldUserTemplate != null) {
            if (oldUserTemplate != null) {
                oldUserTemplate.setUsername(ut.getUsername());
                oldUserTemplate.setPassword(ut.getPassword());
            }
            userService.createUser(oldUserTemplate);
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.notFound().build();
        }


    }




}
