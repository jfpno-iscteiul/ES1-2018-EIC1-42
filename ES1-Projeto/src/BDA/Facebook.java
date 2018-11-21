package BDA;



import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
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

}