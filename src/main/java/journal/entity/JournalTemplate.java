package journal.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal_entries")

@Getter
@Setter
public class JournalTemplate {

    @Id
    private ObjectId id;
    private int date;
    private String content;



}
