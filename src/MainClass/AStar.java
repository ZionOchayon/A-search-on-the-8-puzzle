package MainClass;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class AStar {
	
	// the goal board
	private static Board goal = new Board("012345678");
	// size of the board is SIZE*SIZE
	private static final int SIZE = 3;
	private static Set<Board> closeSet = null;
	private static PriorityQueue<PartialSolution> frontier = null;
	
	public static PartialSolution search(Board board, char type , String heuristics) {	
		
		// count the number of 
		int expandedNodes = 0 ;
		Comperators comperators = new Comperators();
		PartialSolution topSolution = new PartialSolution(board);
		
		//initial frontier by heuristics
		initialFrontier(heuristics,comperators);
		
		// initial close set if needed
		if(type == 'g') {
			closeSet = new TreeSet<Board>(comperators.new ComperatorCloseSet()); 
			closeSet.add(topSolution.getLastBoard());
		}
		
		// start search
		frontier.add(topSolution);
		while (!frontier.isEmpty()) {
			
			topSolution = frontier.poll();
			if(goal.equals(topSolution.getLastBoard())) {
				break;
			}
			// find the empty spot on the last board in topSolution index[0] = row, index[1] = col
			int[] index = topSolution.getLastBoard().findEmptySpot();
			
			// check if the empty spot can move down 
			if (index[0] < SIZE-1) {
				Board newState = exchange(topSolution.getLastBoard(),index[0],index[1],index[0]+1,index[1]);
				expandedNodes += typeOfSearch(topSolution,type,newState);
			}
			
			// check if the empty spot can move up 
			if (index[0] > 0) {
				Board newState = exchange(topSolution.getLastBoard(),index[0],index[1],index[0]-1,index[1]);
				expandedNodes += typeOfSearch(topSolution,type,newState);
			}
			
			// check if the empty spot can move right 
			if (index[1] < SIZE-1) {
				Board newState = exchange(topSolution.getLastBoard(),index[0],index[1],index[0],index[1]+1);
				expandedNodes += typeOfSearch(topSolution,type,newState);
			}
			// check if the empty spot can move left 
			if (index[1] > 0) {
				Board newState = exchange(topSolution.getLastBoard(),index[0],index[1],index[0],index[1]-1);
				expandedNodes += typeOfSearch(topSolution,type,newState);
			}	
		}
		System.out.println("The number of expanded nodes = " + expandedNodes);
		return topSolution;
	}
	
	//Manhattan distance
	public static int h_Manhattan(Board board) {
		int sumManhattan = 0;
		int expectedRow = 0, expectedCol = 0;
		for(int row=0;row<SIZE;row++) {
			for(int col=0;col<SIZE;col++) {
				if(board.getBoard()[row][col] != 0 && board.getBoard()[row][col] != (SIZE*row + col)) {
					expectedRow = board.getBoard()[row][col]/SIZE;
					expectedCol = board.getBoard()[row][row]%SIZE;
					sumManhattan +=  Math.abs(expectedRow - row) + Math.abs(expectedCol - col);
				}
			}
		}
		return sumManhattan;
	}
	
	//misplaced tiles
	public static int h_Misplaced(Board board) {
		int sumMisplaced = 0;
		for(int row=0;row<SIZE;row++) {
			for(int col=0;col<SIZE;col++) {
				if(board.getBoard()[row][col] != 0 && board.getBoard()[row][col] != (SIZE*row + col)) {
					sumMisplaced++;
				}
			}
		}
		return sumMisplaced;
	}
	
	// Ucs
	public static int ucs(PartialSolution partialSolution ) {
		return partialSolution.getPartialSolution().size();
	}
	
	// Search on tree or graph
	private static int typeOfSearch(PartialSolution topSolution,char type,Board newState) {
		if(type == 'g') {
			boolean check = false;
			for(Board b : closeSet) {
				if(b.equals(newState)) {
					check = true;
				}
			}
			if(!check) { 
				closeSet.add(newState);
				frontier.add(new PartialSolution(newState,topSolution));
				return 1;
			}
			return 0;
		}else{
			frontier.add(new PartialSolution(newState,topSolution));
			return 1;
		}
	}
	
	// Initial frontier by heuristic
	private static void initialFrontier(String heuristics,Comperators comperators) {
		switch (heuristics.toLowerCase()) {
		 case "manhattan":
			 frontier = new PriorityQueue<PartialSolution>(comperators.new ComperatorManhattan());
			 break; 
		 case "misplaced":
			 frontier = new PriorityQueue<PartialSolution>(comperators.new ComperatorMisplaced());
			 break; 
		 case "ucs":
			 frontier = new PriorityQueue<PartialSolution>(comperators.new ComperatorUcs());
			 break; 
		}
	}
	
	// exchange between tow values in the board
	private static Board exchange(Board board,int row1,int col1,int row2, int col2) {
		Board newState = new Board(board);
		int temp = newState.getBoard()[row1][col1];
		newState.getBoard()[row1][col1] = newState.getBoard()[row2][col2];
		newState.getBoard()[row2][col2] = temp;
		return newState;
	}
}
