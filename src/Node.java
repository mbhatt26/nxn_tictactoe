/**
 * This is the node class for the configuration nodes. This class is full of getters
 * and setters as well as a constructor.
 * @author Mauli Bhatt, 250860504
 *
 */
public class Node {
	
	private Configuration data;
	private Node next;
	
	private Node prev;
	private String conf;
	private int score;
	
	public Node(Configuration confdata, Node next){
		this.data = confdata;
		this.next = next;
		
	}
	
	public Configuration getData(){
		return this.data;
	}
	
	public Node getNext(){
		return this.next;
	}

	
	public void setNext(Node nxt){
		this.next = nxt;
	}
		
	public void setData(Configuration data){
		this.data = data;
		
	}
}
