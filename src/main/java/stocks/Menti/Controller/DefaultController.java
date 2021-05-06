package stocks.Menti.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stocks.Menti.Service.ServiceSentiment;

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
public class DefaultController {
    
        private ServiceSentiment serviceSentiment;

        public DefaultController(ServiceSentiment serviceSentiment){
                this.serviceSentiment = serviceSentiment;
        }

        public static Twitter getTwitterinstance() {
		
		Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}

	@GetMapping("/{stockName}")
	public String searchTweets(@PathVariable("stockName") String stockName) throws TwitterException {
        
                Twitter twitter = getTwitterinstance();
                Query query = new Query(stockName);
                QueryResult result = twitter.search(query);
                List<Status> statuses = result.getTweets();
                String sent = serviceSentiment.getSentiment(statuses.stream().map(
                item -> item.getText()).collect(
                        Collectors.toList()));
                return sent;
                //return "Greetings from Spring Boot!";
	}
    
}
