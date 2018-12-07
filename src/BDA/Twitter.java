
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
	
	
	private static ArrayList<Long>  ids = new ArrayList <Long> ();
	private XMLFile xml = new XMLFile();
	/**
	 * @param    email is the email relative to the user.
	 * @return    a list of tweets.
	 */
	
	public ArrayList<String> getTweets(String email) {
		ArrayList<String>  content = new ArrayList <String> ();
		String AuthConsumerKey = xml.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = xml.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = xml.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = xml.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");

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
				if (status.getUser().getName() != null) {
					if (status.getUser().getName().equals("EIC142") ||  status.getUser().getName().contains("IUL") ) {
						content.add( "Twitter" + ";;"+ status.getCreatedAt().toString() + ";;"+ status.getUser().getName() + ";;" + status.getText());
						ids.add(status.getId());
					}
				}  
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
		return content;
     }
	
	
	
	/**
	 * Returns the ids of the timeline tweets.
	 */
	
	
	/**
	   * @return    a list with the ids of the tweets.
	 */
	public ArrayList<Long> getTweetsId(){
		return ids;
	}
	



	/**
	 * Retweets a particular tweet.
	 */
	
	/**
	 * @param    id is the id relative to the tweet.
	 @param    email is the email relative to the user.
	 */

	public void retweet(long id, String email) {
		String AuthConsumerKey = xml.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = xml.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = xml.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = xml.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");
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
	
	
	public void tweet(String text, String email) {
		String AuthConsumerKey = xml.getAttributteByEmail(email, "AuthConsumerKeyTwitter");
		String AuthConsumerSecret = xml.getAttributteByEmail(email, "AuthConsumerSecretTwitter");
		String AuthAccessToken = xml.getAttributteByEmail(email, "AuthAccessTokenTwitter");
		String AuthAccessTokenSecret = xml.getAttributteByEmail(email, "AuthAccessTokenSecretTwitter");

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