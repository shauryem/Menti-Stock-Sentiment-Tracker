package stocks.Menti.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stocks.Menti.Service.ServiceSentiment;
import twitter4j.TwitterException;





@RestController
@RequestMapping("/api/v1") 
public class DefaultController {
    
        @Autowired
        private ServiceSentiment serviceSentiment;

        // public DefaultController(ServiceSentiment serviceSentiment){
        //         this.serviceSentiment = serviceSentiment;
        // }



	@GetMapping("/{stockName}")
	public String searchTweets(@PathVariable("stockName") String stockName) throws TwitterException {
                return serviceSentiment.getSentiment(stockName);

	}
    
}
