package BDA;
import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;


public class Facebook {

	public ArrayList<String> getFBNotifications(String email){
		String tokenAccess =  XMLFile.getAttributteByEmail(email, "TokenAccessFacebook");
		FacebookClient fbClient = new DefaultFacebookClient(tokenAccess);
		
			ArrayList<String> posts= new ArrayList<String>();
			Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
			System.out.println("\nPosts:");
			int count = 0;
			int countTotal = 0;
			List<Post> listOfPosts= result.getData();
			for (Post post : listOfPosts) {
				posts.add("Facebook" + ";;"+ post.getCreatedTime().toString() + ";;"+ post.getId() + ";;" + post.getMessage());
				//if (post.getMessage() != null && post.getMessage().contains("happy")) {
//					System.out.println("------ Post "+ count + " ------");
//					System.out.println("Id: "+"fb.com/" + post.getId());
//					System.out.println("Message: " + post.getMessage());
//					System.out.println("Created: " + post.getCreatedTime());
					count++;
				countTotal++;
			 }
			//}
			System.out.println("-------------\nNumber of Results: " + count + "/"+ countTotal);	
			return posts;
		
	}


	
	
	public static void publish(String email, String text) {
		String id = "100029513953648";
		String pageAccessToken =  XMLFile.getAttributteByEmail(email, "TokenAccessFacebook");
		FacebookClient fbClient=null;
		try {
			fbClient = new DefaultFacebookClient(pageAccessToken);
		} catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }
		
		fbClient.publish(id + "/feed", FacebookType.class, Parameter.with("message", text));
	}

}