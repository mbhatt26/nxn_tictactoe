/**
 * 
 * This class provides structure for the configuration and score.
 * @author Mauli Bhatt, 250860504
 *
 */
public class Configuration {
	
	/**
	 * Instance variables
	 */
	private String configuration;
	private int score;
	
	/**
	 * Constructor that sets up a new configuration object with a string and a score.
	 * @param config
	 * @param score
	 */
	public Configuration(String config, int score){
		this.configuration = config;
		this.score = score;
	}
	
	/**
	 * This method returns the string configuration of the object.
	 * @return String
	 */
	public String getStringConfiguration(){
		return this.configuration;
	}
	
	/**
	 * This method returns the score of the object.
	 * @return int
	 */
	public int getScore(){
		return this.score;
	}
}
