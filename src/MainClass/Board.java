package MainClass;

public class Board {
	
	private final int SIZE = 3;
	
	private int[][] board = new int[SIZE][SIZE];

	// constructor
	public Board(String s) {
		final int RADIX = 10;
		board[0][0] = Integer.parseInt(s, 0, 1, RADIX);
		board[0][1] = Integer.parseInt(s, 1, 2, RADIX);
		board[0][2] = Integer.parseInt(s, 2, 3, RADIX);
		board[1][0] = Integer.parseInt(s, 3, 4, RADIX);
		board[1][1] = Integer.parseInt(s, 4, 5, RADIX);
		board[1][2] = Integer.parseInt(s, 5, 6, RADIX);
		board[2][0] = Integer.parseInt(s, 6, 7, RADIX);
		board[2][1] = Integer.parseInt(s, 7, 8, RADIX);
		board[2][2] = Integer.parseInt(s, 8, 9, RADIX);
	}
	
	// Deep copy constructor
	public Board(Board oldBoard) {
		for(int row = 0 ; row<SIZE ; row++) {
			for(int col = 0 ; col<SIZE ; col++) {
				this.board[row][col] = oldBoard.getBoard()[row][col];
			}
		}
	}
	
	// find Empty Spot on board and return array of [row index,col index]
	public int[] findEmptySpot() {
		int[] index = new int[2];
		for(int row = 0 ; row<SIZE ; row++) {
			for(int col = 0 ; col<SIZE ; col++) {
				if(board[row][col] == 0) {
					index[0] = row;
					index[1] = col;
					return index;
				}
			}
		}
		return null;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
    // Overriding equals() to compare two board by values
    @Override
    public boolean equals(Object o) {
    	Board board = (Board) o;
    	int counter = 0;
		for(int i = 0 ; i<SIZE ; i++) {
			for(int j = 0 ; j<SIZE ; j++) {
				if(board.getBoard()[i][j] == this.board[i][j]) {
					counter++;
				}
			}
		}
		if(counter == SIZE*SIZE) {
			return true;
		} else {
			return false;
		}	
    }
	
	//print board
	public void print() {
		System.out.println(board[0][0] + " " + board[0][1] + " " + board[0][2]);
		System.out.println(board[1][0] + " " + board[1][1] + " " + board[1][2]);
		System.out.println(board[2][0] + " " + board[2][1] + " " + board[2][2]);
		System.out.println();
	}
}
