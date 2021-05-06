package stocks.Menti.Service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceSentiment {
    
    public String getSentiment(List<String> ls){
        return ls.get(0);
    }
}
