package com.twitter.controller.Controller;


import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Tanya on 28/12/2019
 */
@Controller
public class TwitterController {


    @GetMapping("getForm")
    public String getForm() {
        return "TwitterForm";
    }

    @PostMapping("/saveDetails")                     // it only support port method
    public String saveDetails(@RequestParam("Location") String Location,
                              @RequestParam("Time") String time,
                              ModelMap modelMap) {

        //testing twitter
        String[] trendsName=null;
        try {
            trendsName=  test();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        // write your code to save details
        modelMap.put("Location", Location);
        modelMap.put("Time", time);
        modelMap.put("Trends",trendsName );
        return "viewDetails";           // viewDetails is view name. It will call ViewDetails.jsp
    }

    public String[] test() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3jmA1BqasLHfItBXj3KnAIGFB")
                .setOAuthConsumerSecret("imyEeVTctFZuK62QHmL1I0AUAMudg5HKJDfkx0oR7oFbFinbvA")
                .setOAuthAccessToken("265857263-pF1DRxgIcxUbxEEFtLwLODPzD3aMl6d4zOKlMnme")
                .setOAuthAccessTokenSecret("uUFoOOGeNJfOYD3atlcmPtaxxniXxQzAU4ESJLopA1lbC");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Trends trends = twitter.getPlaceTrends(1);
        int count = 0;
        String[] trendsName = new String[10];
        for (Trend trend : trends.getTrends()) {
            if (count < 10) {
                System.out.println(trend.getName());
                trendsName[count] = trend.getName();
                count++;
            }
        }
        return trendsName;
    }
}