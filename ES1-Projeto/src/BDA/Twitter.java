
package BDA;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public final class Twitter {
	
	/**
	 * Get the tweets for the desired account and save them in a list.
	 */
	
	private ArrayList<String>  content = new ArrayList <String> ();
	private static ArrayList<Long>  ids = new ArrayList <Long> ();
	
	
	/**
	 * @param    email is the email relative to the user.
	 * @return    a list of tweets.
	 */
	
	public static ArrayList<String> getTweets(String email) {
		ArrayList<String>  content = new ArrayList <String> ();
		String AuthConsumerKey = XMLFile.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = XMLFile.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = XMLFile.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = XMLFile.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");

        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey(AuthConsumerKey)
        	  .setOAuthConsumerSecret(AuthConsumerSecret)
        	  .setOAuthAccessToken(AuthAccessToken)
        	  .setOAuthAccessTokenSecret(AuthAccessTokenSecret);
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	twitter4j.Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();

            for (Status status : statuses) {
				// Filters only tweets from user "user"
				if (status.getUser().getName() != null) {
					//System.out.println(status.getUser());
					if (status.getUser().getName().equals("EIC142") ||  status.getUser().getName().contains("IUL") ) {
					//	System.out.println(status.getUser().getName() + ";;" + status.getText());
						content.add( "Twitter" + ";;"+ status.getCreatedAt().toString() + ";;"+ status.getUser().getName() + ";;" + status.getText());
						ids.add(status.getId());
						//counter++;
					}
				}  
				//counterTotal++;
            }
    		//System.out.println("-------------\nNumber of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
        //System.out.println(content);
		return content;
     }
	
	
	
	/**
	 * Returns the ids of the timeline tweets.
	 */
	
	
	/**
	   * @return    a list with the ids of the tweets.
	 */
	public static ArrayList<Long> getTweetsId(){
		return ids;
	}
	



	/**
	 * Retweets a particular tweet.
	 */
	
	/**
	 * @param    id is the id relative to the tweet.
	 @param    email is the email relative to the user.
	 */

	public static void retweet(long id, String email) {
		String AuthConsumerKey = XMLFile.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = XMLFile.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = XMLFile.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = XMLFile.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");
				 // access_secret obtained by authentication user's twitter account
				Long tweetId = id; // messageId of the tweet to be retweeted
				
				 TwitterFactory factory = new TwitterFactory();
				 twitter4j.Twitter twitter = factory.getInstance();
				 twitter.setOAuthConsumer(AuthConsumerKey, AuthConsumerSecret);
				 AccessToken accessToken = new AccessToken(AuthAccessToken, AuthAccessTokenSecret);
				 twitter.setOAuthAccessToken(accessToken);
				 try {
					twitter.retweetStatus(tweetId);
				} catch (TwitterException e) {e.printStackTrace();}
	}
	
	
	/**
	 * Tweets a particular content.
	 */
	
	

	/**
	 * @param    text is the text which the user wants to tweet.
	 @param    email is the email relative to the user.
	 */
	
	
	public static void tweet(String text, String email) {
		String AuthConsumerKey = XMLFile.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = XMLFile.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = XMLFile.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = XMLFile.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");

	       ConfigurationBuilder cb = new ConfigurationBuilder();
	       cb.setDebugEnabled(true)
	           .setOAuthConsumerKey(AuthConsumerKey)
	           .setOAuthConsumerSecret(AuthConsumerSecret)
	           .setOAuthAccessToken(AuthAccessToken)
	           .setOAuthAccessTokenSecret(AuthAccessTokenSecret);

	       try 
	       {
	          TwitterFactory factory = new TwitterFactory(cb.build());
	          twitter4j.Twitter twitter = factory.getInstance();
	         // System.out.println(twitter.getScreenName());
	          @SuppressWarnings("unused")
			Status status = twitter.updateStatus(text);
	         // System.out.println("Successfully updated the status to [" + status.getText() + "].");
	           }catch (TwitterException te) {
	              te.printStackTrace();
	           }
	}
}