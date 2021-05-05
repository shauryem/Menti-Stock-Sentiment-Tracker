package stocks.Menti.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeafultController {
    
	@GetMapping("/")
	public List<String> index() {
		List<String> ls = new ArrayList<>();
        ls.add("hello");
        return ls;
        //return "Greetings from Spring Boot!";
	}
    
}
