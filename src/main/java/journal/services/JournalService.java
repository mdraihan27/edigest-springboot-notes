package journal.services;

import journal.entity.JournalTemplate;
import journal.entity.UserTemplate;
import journal.repo.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public List<JournalTemplate> getAllJournal(){

        return journalRepository.findAll();

    }

    public ResponseEntity postJournalByUsername(JournalTemplate journalTemplate, String username){

        UserTemplate user = userService.getUserByUsername(username);

        if(user != null){
            JournalTemplate journal = journalRepository.save(journalTemplate);
            user.getJournalEntries().add(journal);
            userService.createUser(user);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }


    }

    public Optional<JournalTemplate> getJournalByUsernameAndId(String username, ObjectId id){

        UserTemplate user = userService.getUserByUsername(username);

        for(JournalTemplate journal : user.getJournalEntries()){
            if(journal.getId().equals(id)){
                return Optional.of(journal);

            }
        }

        return null;

    }

    public void deleteJournalByUsernameAndId(String username, ObjectId id){

        UserTemplate user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.createUser(user);
        journalRepository.deleteById(id);

    }

    public void deleteAllByUser(String username){
        UserTemplate user = userService.getUserByUsername(username);
        for(JournalTemplate journal : user.getJournalEntries()){
            journalRepository.deleteById(journal.getId());
        }
        user.getJournalEntries().removeIf(x -> x!=null);
    }

    public ResponseEntity putByUsernameAndId(String username, JournalTemplate newJournal, ObjectId id){

        JournalTemplate old = getJournalByUsernameAndId(username,id).orElse(null);

        if(newJournal != null){
            old.setContent(newJournal.getContent()!=null && !newJournal.getContent().equals("") ? newJournal.getContent() : old.getContent());
            old.setDate(newJournal.getDate()<31 && newJournal.getDate()!=0 ? newJournal.getDate() : old.getDate());
            old.setId(newJournal.getId()!=null ? newJournal.getId() : old.getId());
            journalRepository.save(old);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
