package BDA;

import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.GraphResponse;
import com.restfb.types.Post;

/**
 * Remove information about facebook using the restfb library
 */

public class Facebook {
	@SuppressWarnings("finally")
	public static ArrayList<String> getFBNotifications(String email) {
		ArrayList<String> posts = new ArrayList<String>();
		try {
			String tokenAccess = XMLFile.getAttributteByEmail(email, "TokenAccessFacebook");
			@SuppressWarnings("deprecation")
			FacebookClient fbClient = new DefaultFacebookClient(tokenAccess);

			Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
			//System.out.println("\nPosts:");
			int count = 0;
			int countTotal = 0;
			List<Post> listOfPosts = result.getData();
			for (Post post : listOfPosts) {
				posts.add("Facebook" + ";;" + post.getCreatedTime().toString() + ";;" + post.getId() + ";;"
						+ post.getMessage());
				// if (post.getMessage() != null && post.getMessage().contains("happy")) {
//					System.out.println("------ Post "+ count + " ------");
//					System.out.println("Id: "+"fb.com/" + post.getId());
//					System.out.println("Message: " + post.getMessage());
//					System.out.println("Created: " + post.getCreatedTime());
				count++;
				countTotal++;
			}
			// }
			System.out.println("-------------\nNumber of Results: " + count + "/" + countTotal);
		} finally {
			return posts;
		}
	}

	/**
	 * Post a facebook post
	 */
	/*
	 * Needs special access
	 * 
	 * @SuppressWarnings("deprecation") public static void publish(String email,
	 * String text) { String id = "100029513953648"; String pageAccessToken =
	 * XMLFile.getAttributteByEmail(email, "TokenAccessFacebook"); FacebookClient
	 * fbClient=null; try { fbClient = new DefaultFacebookClient(pageAccessToken); }
	 * catch (FacebookException ex) { //So that you can see what went wrong
	 * ex.printStackTrace(System.err); //in case you did anything incorrectly }
	 * 
	 * fbClient.publish(id + "/feed", FacebookType.class, Parameter.with("message",
	 * text)); }
	 */
	
	public static void postOnGroup (String text,String email) {
		String groupID = "1254500671355958";
		String tokenAccess = XMLFile.getAttributteByEmail(email, "TokenAccessFacebook");
		FacebookClient fbClient = new DefaultFacebookClient(tokenAccess);
		GraphResponse response = fbClient.publish(groupID + "/feed", GraphResponse.class, Parameter.with("message",text));
	}

}