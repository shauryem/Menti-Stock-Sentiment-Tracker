package stocks.Menti.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
// import me.aboullaite.corenlp.sentimentanalysis.model.SentimentType;

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

@Service
public class ServiceSentiment {
    
    public static Twitter getTwitterinstance() {
		
		Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}

    private List<String> queryHelper(String stockName)throws TwitterException{
        Twitter twitter = getTwitterinstance();
        Query query = new Query(stockName);
        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        List<String> sent = statuses.stream().map(
        item -> item.getText()).collect(
                Collectors.toList());

        return sent;
    }

    private int sentimentHelper(String stockList){
        
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        String text = stockList;
        int mainSentiment = 0;
        Annotation annotation = pipeline.process(text);
        int longest = 0;
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
            String partText = sentence.toString();
            if (partText.length() > longest) {
                mainSentiment = sentiment;
                longest = partText.length();
            }
        }

        return mainSentiment;
        // System.out.println(text);
        // switch (mainSentiment){
        //     case 0:
        //         return("Very Negative");
        //     case 1:
        //         return("Negative");
        //     case 2:
        //         return("Neutral");
        //     case 3:
        //         return("Positive");
        //     case 4:
        //         return("Very Positive");
        // }
        
        
        
        
        // return "Neutral";
    }

    public String getSentiment(String stockName) throws TwitterException{
        List<String> stockList = queryHelper(stockName);
        //List<String> sentimentList = new ArrayList<>();
        int runningSum = 0;
        int avgScore = 0;
        for(String s: stockList){
            runningSum += sentimentHelper(s);
        }
        avgScore = (int)Math.rint(runningSum/stockList.size());
        
         switch (avgScore){
            case 0:
                return("Very Negative");
            case 1:
                return("Negative");
            case 2:
                return("Neutral");
            case 3:
                return("Positive");
            case 4:
                return("Very Positive");
            default:
                return("none");
        }


       
    }
}
