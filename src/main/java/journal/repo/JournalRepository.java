package journal.repo;

import journal.entity.JournalTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalTemplate, ObjectId> {

}
