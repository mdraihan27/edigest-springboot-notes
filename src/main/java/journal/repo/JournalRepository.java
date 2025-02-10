package journal.repo;

import journal.JournalTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalTemplate, ObjectId> {

}
