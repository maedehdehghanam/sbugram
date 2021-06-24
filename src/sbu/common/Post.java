package sbu.common;
import java.io.*;
import java.util.*;
import java.time.*;
import java.util.concurrent.*;
public class Post implements Serializable , Comparable{

	private final long createdTime;
	private final String timeString;
	private List<Profile> likedPeople;
	private long numberOfLikes;
	private Object postingPicture;
	private String caption;
	private List<Comment> comments;
	private boolean deleted = false;
	private final Profile poster;
	private String title;
	
	public Post(Object postingPicture, String caption, Profile poster, String title){
		this.postingPicture = postingPicture;
		this.caption = caption;
		this.poster = poster;
		this.title =  title;
		comments = new Vector<>();
		numberOfLikes = 0;
		likedPeople = new Vector<>();
		createdTime = Time.getMilli();
    	timeString = Time.getTime();
	}

	/*@Override
	public int hashCode() {
    return createdTime.hashCode();
    }*/
    @Override
  	public String toString(){
  		return poster.getUserName() + caption + "\n" + "likes = " + numberOfLikes;
  	}
  	public void deletePost(){
  		deleted = true;
  	}
  	@Override
  	public int compareTo(Object o) {
    	if (o instanceof Post == false) 
    		return -1;
    	Post other = ((Post) o );
    	if(this.postingPicture == other.postingPicture)
    		return 1;
    	//return createdTime.compareTo( other.createdTime );
    	return 0;
    }
    public long getNumberOfLikes(){
    	return numberOfLikes;
    }
    public String getTitle(){
    	return title;
    }
    public List<Profile> getLikedPeople(){
    	return likedPeople;
    }
    public synchronized void likePost(Profile account){
    	likedPeople.add(account);
    	numberOfLikes++;
    }
    public synchronized void unlikePost(Profile account){
    	likedPeople.remove(account);
    	numberOfLikes--;
    }
    public synchronized void addComment(Comment c){
    	comments.add(c);
    }
    public synchronized void deleteComment(Comment c){
    	comments.remove(c);
    }
    public synchronized void editPost(String newCaption){
    	caption = newCaption;
    }
    public Profile getPoster(){
    	return poster;
    } 
}