package sbu.common;
public class Comment{
	private final Profile sender;
	private final Profile reciever;
	private final String text;
	private final String timeString;
	public Comment(Profile sender,Profile reciever, String text){
		this.sender = sender;
		this.reciever = reciever;
		this.text = text;
		timeString = Time.getTime();
	}
	@Override
  	public String toString(){
  		return sender.getName() + " to "+ reciever.getName()+ " : "+text+"\n"+ timeString;
	}
}