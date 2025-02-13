package journal.repo;

import journal.entity.UserTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserTemplate, ObjectId> {

    UserTemplate findByUsername(String username);
}
