package journal.controller;

import journal.JournalTemplate;
import journal.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    private JournalService journalService;

    @GetMapping
    public ResponseEntity<List<JournalTemplate>> getAll() {
        List<JournalTemplate> allList = journalService.getAllJournal();

        if (allList.isEmpty()) {
            return ResponseEntity.noContent().build();  // Returns HTTP 204 No Content
        }

        return ResponseEntity.ok(allList);  // Returns HTTP 200 with the list
    }

    @GetMapping("view/{id}/")
    public ResponseEntity<Optional<JournalTemplate>> getEntry(@PathVariable ObjectId id) {
        Optional<JournalTemplate> jt = journalService.getJournalById(id);

        if(jt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jt, HttpStatus.OK);
    }

    @PostMapping
    public void postJournal(@RequestBody JournalTemplate journalTemplate) {
        journalService.postJournal(journalTemplate);
    }

    @DeleteMapping("delete/{id}/")
    public void deleteJournal(@PathVariable ObjectId id) {
       journalService.deleteJournalById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        journalService.deleteAll();
    }

    @PutMapping("put/{id}/")
    public void putJournal(@PathVariable ObjectId id, @RequestBody JournalTemplate journalTemplate) {

        journalService.putById(journalTemplate, id);
    }


}
