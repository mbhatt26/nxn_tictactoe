/**
 * This code sets up the BoardGame class, and provides the operations for the
 * game.
 * 
 * @author Mauli Bhatt, 250860504
 */
public class BoardGame {

	/**
	 * Instance variables
	 */
	private char[][] gameBoard;
	private int board_size;
	private int empty_positions;
	private int max_levels;

	/**
	 * The constructor creates an empty board full of g.
	 * @param board_size
	 * @param empty_positions
	 * @param max_levels
	 */
	public BoardGame(int board_size, int empty_positions, int max_levels) {
		this.board_size = board_size;
		this.empty_positions = empty_positions;
		this.max_levels = max_levels;
		this.gameBoard = new char[this.board_size][this.board_size];
		for (int i = 0; i < this.board_size; i++) {
			for (int j = 0; j < this.board_size; j++) {
				gameBoard[i][j] = 'g';
			}
		}
	}
	
	/**
	 * Makes a new HashDictionary object.
	 * @return HashDictionary
	 */
	public HashDictionary makeDictionary() {
		HashDictionary hashDict = new HashDictionary(9949);
		return hashDict;
	}

	/**
	 * This method checks if the game configuration is repeated.
	 * @param dict
	 * @return int
	 */
	public int isRepeatedConfig(HashDictionary dict) {
		String s = "";
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				s = s + Character.toString(gameBoard[i][j]);
			}
		}
		return dict.getScore(s);
	}

	/**
	 * This method puts the configuration inside the hash dictionary.
	 * @param dict
	 * @param score
	 */
	public void putConfig(HashDictionary dict, int score) {
		String s = "";
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				s = s + Character.toString(gameBoard[i][j]);
			}
		}
		Configuration config = new Configuration(s, score);
		dict.put(config);
	}

	/**
	 * This method saves the play to the game board.
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	/**
	 * This method checks if the position is empty
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean positionIsEmpty(int row, int col) {
		if (gameBoard[row][col] == 'g')
			return true;
		else
			return false;
	}

	/**
	 * This method checks if the tile is a computer tile.
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean tileOfComputer(int row, int col) {
		if (gameBoard[row][col] == 'o')
			return true;
		else
			return false;
	}

	/**
	 * This method checks if the tile is a human tile.
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean tileOfHuman(int row, int col) {
		if (gameBoard[row][col] == 'b')
			return true;
		else
			return false;
	}

	/**
	 * This method checks if there has been a win.
	 * @param symbol
	 * @return boolean
	 */
	public boolean wins(char symbol) {
		String winner = "";
		String rowAdj = "";
		String colAdj = "";
		String diagR = "";
		String diagL = "";

		for (int i = 0; i < board_size; i++) {
			winner += symbol;
		}

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				rowAdj = rowAdj + Character.toString(gameBoard[i][j]);
				colAdj = colAdj + Character.toString(gameBoard[j][i]);
				if (i == board_size - 1 - j) { // diagonal beginning on the
												// right
					diagR = diagR + Character.toString(gameBoard[i][j]);
				}
				if (i == j) { // diagonal beginning on the left
					diagL = diagL + Character.toString(gameBoard[i][j]);
				}
			}
			if ((rowAdj.equals(winner)) || (colAdj.equals(winner))) {
				return true; // adjacent win
			}
			rowAdj = "";
			colAdj = "";
		}
		if ((diagR.equals(winner)) || (diagL.equals(winner))) {
			return true;
		} else
			return false;
	}

	/**
	 * This method checks if the game is a draw.
	 * @param symbol
	 * @param empty_positions
	 * @return boolean
	 */
	public boolean isDraw(char symbol, int empty_positions) {
		if (!wins(symbol)) {
			if (empty_positions == 0) {
				for (int i = 0; i < board_size; i++) {
					for (int j = 0; j < board_size; j++) {
						if (gameBoard[i][j] == 'g') {
							return false;
						}
					}
				}
			}
			int[] emptyRow = new int[empty_positions];
			int[] emptyCol = new int[empty_positions];
			int x = 0; // index
			for (int i = 0; i < this.board_size; i++) { // Scans the board for the empty spots
				for (int j = 0; j < this.board_size; j++) {
					if (gameBoard[i][j] == 'g') {
						if (x >= empty_positions)
							return false;
						emptyRow[x] = i;
						emptyCol[x] = j;
						x++;
					}
				}
			}
			for (int k = 0; k < empty_positions; k++) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						if ((((emptyRow[k] -1 +i)>=0) && (emptyRow[k] -1 +i) < this.board_size) 
							&& ((((emptyCol[k] -1 +j) >= 0) && (emptyCol[k] -1 +j) < this.board_size))){
							if (gameBoard[emptyRow[k] - 1 + i][emptyCol[k] - 1 + j] == symbol) { 
								return false; // Not a draw, return false
							}
						}
					}
				}
			}
			return true;
		} else
			return false;
	}

	/**
	 * This method determines the board score.
	 * @param symbol
	 * @param empty_positions
	 * @return int score
	 */
	public int evalBoard(char symbol, int empty_positions) {
		if (isDraw(symbol, empty_positions)) {
			return 2;
		}
		if (wins(symbol)) {
			if (symbol == 'b') { // human wins
				return 0;
			}
			if (symbol == 'o') {// computer wins
				return 3;
			}
		}
		return 1;
	}
}
