package journal.services;

import journal.JournalTemplate;
import journal.repo.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public List<JournalTemplate> getAllJournal(){

        return journalRepository.findAll();

    }

    public void postJournal(JournalTemplate journalTemplate){
        journalRepository.save(journalTemplate);
    }

    public Optional<JournalTemplate> getJournalById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteJournalById(ObjectId id){
        journalRepository.deleteById(id);
    }

    public void deleteAll(){
        journalRepository.deleteAll();
    }

    public void putById(JournalTemplate newJournal, ObjectId id){
        JournalTemplate old = getJournalById(id).orElse(null);

        if(newJournal != null){
            old.setContent(newJournal.getContent()!=null && !newJournal.getContent().equals("") ? newJournal.getContent() : old.getContent());
            old.setDate(newJournal.getDate()<31 && newJournal.getDate()!=0 ? newJournal.getDate() : old.getDate());
            old.setId(newJournal.getId()!=null ? newJournal.getId() : old.getId());
        }

        postJournal(old);
    }
}
