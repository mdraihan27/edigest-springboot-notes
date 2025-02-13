package journal.controller;

import journal.entity.JournalTemplate;
import journal.entity.UserTemplate;
import journal.services.JournalService;
import journal.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;

    @GetMapping("view/{username}")
    public ResponseEntity<List<JournalTemplate>> getAllByUser(@PathVariable String username) {
        UserTemplate userTemplate = userService.getUserByUsername(username);
        List<JournalTemplate> allList = userTemplate.getJournalEntries();

        if (allList.isEmpty()) {
            return ResponseEntity.noContent().build();  // Returns HTTP 204 No Content
        }

        return ResponseEntity.ok(allList);  // Returns HTTP 200 with the list
    }

    @GetMapping("view/{username}/{id}")
    public ResponseEntity<Optional<JournalTemplate>> getEntry(@PathVariable ObjectId id, @PathVariable String username) {

        Optional<JournalTemplate> journal =  journalService.getJournalByUsernameAndId(username, id);

        if(journal != null){
            return ResponseEntity.ok(journal);
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("create/{username}")
    public ResponseEntity postJournal(@RequestBody JournalTemplate journalTemplate, @PathVariable String username) {

        return journalService.postJournalByUsername(journalTemplate, username);
    }

    @DeleteMapping("delete/{username}/{id}")
    public void deleteJournal(@PathVariable String username, @PathVariable ObjectId id) {

        journalService.deleteJournalByUsernameAndId(username, id);
    }

    @DeleteMapping("delete/{username}")
    public void deleteAll(@PathVariable String username){
        journalService.deleteAllByUser(username);
    }

    @PutMapping("put/{username}/{id}")
    public void putJournal(@PathVariable String username, @PathVariable ObjectId id, @RequestBody JournalTemplate journalTemplate) {

        journalService.putByUsernameAndId(username, journalTemplate, id);
    }


}
