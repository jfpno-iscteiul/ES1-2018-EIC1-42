import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class Twitter {
	/**
	 * Get the tweets for the desired account and save them in a list.
	 */
	static ArrayList<String>  content = new ArrayList <String> ();
	public static ArrayList<String> getTweets() {

        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("PaQDlX63u15a6moYQ2VmxZEbg")
        	  .setOAuthConsumerSecret("PSptJzC05HXYaiHLDdBtJfqwIjTOq4MEXh2OKVtN9zsMpsVbox")
        	  .setOAuthAccessToken("1053330543281094656-GHcgJiGjTmt6WUS2RpMsh1kW9ijn7a")
        	  .setOAuthAccessTokenSecret("3VpBfl2BgLp3xXy7jNWlQhTzLo4GDolc0uoJMYh9mT8nC");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	twitter4j.Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();
            //System.out.println("------------------------\n Showing home timeline \n------------------------");
//    		int counter=0;
//    		int counterTotal = 0;
            for (Status status : statuses) {
				// Filters only tweets from user "user"
				if (status.getUser().getName() != null && status.getUser().getName().contains("IUL")) {
					//System.out.println(status.getUser().getName() + ";;" + status.getText());
					content.add(status.getCreatedAt().toString() + ";;"+ status.getUser().getName() + ";;" + status.getText());
					//counter++;
				}
				//counterTotal++;
            }
    		//System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
		return content;
     }	
}