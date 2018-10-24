import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {

	public static void main(String[] args) {
		/*
		String token1Access = "EAADlXhdm78kBAPbcqL6I1iXQhv9RC6iEsDmNd10kUvZBEmVZBwWWxlkBddn8Jvc5IWCVFUuRgOwZCWnvL6gbdXxXPyrJgmJFRTAJBNhYZBld7KZBuqRBE6Shwu3gqb0M1ZCmKXlVXyPcCPtcZBPlGvXMCSfkDaSdq8ytmf9V2ZBS3qqz1avsa9ZBz2ZCSuNxpqovZA6rCfzjvGNaDWAwCRRrwtA";
		FacebookClient fbClient = new DefaultFacebookClient(token1Access);
		User me = fbClient.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me.getId());
		System.out.println("Name: " + me.getName());
		
		
		String token2Access = "EAADlXhdm78kBAOKFtFenkmD5FDNowheADeUp6HukIcunllNB8nP8IZCZBZCoj8pWhf4DMR8qXlYIqF7X2otZA2HO4eOoASIKJcMu0ire8aeQg6nPUlIRBgFfwlKt0zZBk1GNadH2e69s2PZCZACK09T9qbjjXTzZAZA0GO8DFYBtC3OUkLRwrWwQH";
		FacebookClient fbClient2 = new DefaultFacebookClient(token2Access);
		AccessToken extendedAccessToken = fbClient2.obtainExtendedAccessToken("252192282308553","90ebd92d8243dc015e44730b041b2377");
		System.out.println("ExtendedAccessToken: " + extendedAccessToken.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken.getExpires());
		
		*/
		//FINAL
		String token3Access = "EAADlXhdm78kBAOKFtFenkmD5FDNowheADeUp6HukIcunllNB8nP8IZCZBZCoj8pWhf4DMR8qXlYIqF7X2otZA2HO4eOoASIKJcMu0ire8aeQg6nPUlIRBgFfwlKt0zZBk1GNadH2e69s2PZCZACK09T9qbjjXTzZAZA0GO8DFYBtC3OUkLRwrWwQH";
		FacebookClient fbClient3 = new DefaultFacebookClient(token3Access);
		Connection<Post> result = fbClient3.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int count = 0;
		int countTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Change/delete filter
				//if (aPost.getMessage() != null && aPost.getMessage().contains("happy")) {
					System.out.println("---- Post "+ count + " ----");
					System.out.println("Id: "+"fb.com/" + aPost.getId());
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					count++;
				//}
				countTotal++;
			}
		}
		System.out.println("-------------\nNumber of Results: " + count + "/"+ countTotal);		
	}
}