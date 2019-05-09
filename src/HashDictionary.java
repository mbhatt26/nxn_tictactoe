/**
 * This class is the class that contains the hash table.
 * @author Mauli Bhatt, 250860504
 *
 */
public class HashDictionary implements DictionaryADT {

	private int size;
	private Node[] hashDict;

	/**
	 * This is the constructor that creates a hash table of a provided size.
	 * @param size
	 */
	public HashDictionary(int size) {
		this.hashDict = new Node[size];
		this.size = size;
		// take this out next run
		for (int i = 0; i < size; i++) {
			hashDict[i] = null;
		}
		// possible size: 9949
	}

	/**
	 * This method is the hash code that determines the key for where to place the data.
	 * @param data
	 * @return
	 */
	private int hashed(String data) {
		int k = data.length();
		int X = 33;

		int key = 0;
		for (int i = 0; i < k; i++) {
			key = ((key + (int) data.charAt(i)) * X) % size;
		}
		return key;
	}

	/**
	 * This method puts the data into the hash table.
	 * @param Configuration data
	 * @exception DictionaryException
	 * @return int
	 */
	public int put(Configuration data) throws DictionaryException {
		// may have to initialize a head node

		int key = hashed(data.getStringConfiguration());
		// Node<Configuration> node = new Node<Configuration>(data);

		if (hashDict[key] == null) {
			hashDict[key] = new Node(data, null);
			return 0;
		} else {
			Node currentNode = hashDict[key];
			while (currentNode != null) {
				if (currentNode.getData().getStringConfiguration().equals(data.getStringConfiguration())) {
					throw new DictionaryException(
							"Duplicate configuration:" + currentNode.getData().getStringConfiguration());
				}
				currentNode = currentNode.getNext();
			}
			hashDict[key] = new Node(data, hashDict[key]);
			return 1;
		}
	}
	
	/**
	 * This method removes the argument string from the hash table.
	 * @param config
	 * @exception DictionaryException
	 */
	public void remove(String config) throws DictionaryException {
		int key = hashed(config);
		
		Node currentNode = hashDict[key];
		Node prevNode = null;
		
		while (currentNode != null) {
			if (currentNode.getData().getStringConfiguration().equals(config)) {
				if (prevNode == null){ // If our desired node is first in the list, set first to next
					hashDict[key] = currentNode.getNext();
				}
				else{ // Remove middle node by changing surrounding pointers.
					prevNode.setNext(currentNode.getNext());
				}
				return;	
			}
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
		throw new DictionaryException("Configuration " + config + "does not exist.");
	}

	/**
	 * This method returns the score of a given configuration from the hash table.
	 * @param config
	 * @return int
	 */
	public int getScore(String config){
		int key = hashed(config);
		Node currentNode = hashDict[key];
		
		while (currentNode != null){
			if (currentNode.getData().getStringConfiguration().equals(config)){
				return currentNode.getData().getScore();
			}
			currentNode = currentNode.getNext();
		}
		return -1;
	}

}
