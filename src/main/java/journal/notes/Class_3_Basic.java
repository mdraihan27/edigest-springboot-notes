package journal.notes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Class_3_Basic {

    @GetMapping("abc")
    public String sayHello(){
        return "Hello";
    }
}
