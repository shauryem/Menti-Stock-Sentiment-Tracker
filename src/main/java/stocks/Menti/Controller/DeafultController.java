package stocks.Menti.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;



@RestController
public class DeafultController {
    
    public static Twitter getTwitterinstance() {
		
		Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}

	@GetMapping("/")
	public List<String> searchTweets() throws TwitterException {
        
        Twitter twitter = getTwitterinstance();
        Query query = new Query("Bruno Mars");
        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        return statuses.stream().map(
            item -> item.getText()).collect(
                    Collectors.toList());
        //return "Greetings from Spring Boot!";
	}
    
}
