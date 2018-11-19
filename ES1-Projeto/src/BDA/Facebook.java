package BDA;



import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;

public class Facebook {

	public ArrayList<String> getFBNotifications(){
		String tokenAccess = "EAADlXhdm78kBAOKFtFenkmD5FDNowheADeUp6HukIcunllNB8nP8IZCZBZCoj8pWhf4DMR8qXlYIqF7X2otZA2HO4eOoASIKJcMu0ire8aeQg6nPUlIRBgFfwlKt0zZBk1GNadH2e69s2PZCZACK09T9qbjjXTzZAZA0GO8DFYBtC3OUkLRwrWwQH";
		FacebookClient fbClient = new DefaultFacebookClient(tokenAccess);
		
			ArrayList<String> posts= new ArrayList<String>();
			Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
			System.out.println("\nPosts:");
			@SuppressWarnings("unused")
			int count = 0;
			@SuppressWarnings("unused")
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
			//System.out.println("-------------\nNumber of Results: " + count + "/"+ countTotal);	
			return posts;
	}

}