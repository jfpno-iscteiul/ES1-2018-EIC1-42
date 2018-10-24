import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {

	public static void main(String[] args) {
		String tokenAccess = "EAADlXhdm78kBAOKFtFenkmD5FDNowheADeUp6HukIcunllNB8nP8IZCZBZCoj8pWhf4DMR8qXlYIqF7X2otZA2HO4eOoASIKJcMu0ire8aeQg6nPUlIRBgFfwlKt0zZBk1GNadH2e69s2PZCZACK09T9qbjjXTzZAZA0GO8DFYBtC3OUkLRwrWwQH";
		FacebookClient fbClient = new DefaultFacebookClient(tokenAccess);
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int count = 0;
		int countTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				//Apply filter - if (aPost.getMessage() != null && aPost.getMessage().contains("happy")) {
					System.out.println("------ Post "+ count + " ------");
					System.out.println("Id: "+"fb.com/" + aPost.getId());
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					count++;
				countTotal++;
			}
		}
		System.out.println("-------------\nNumber of Results: " + count + "/"+ countTotal);		
	}
}