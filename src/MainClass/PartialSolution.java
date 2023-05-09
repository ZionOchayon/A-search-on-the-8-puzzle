package MainClass;

import java.util.ArrayList;
import java.util.List;

public class PartialSolution{

	// A partial solution is a legal series of successive boards
	private List<Board> partialSolution = new ArrayList<Board>();
	
	// Constructor
	public PartialSolution(Board board) {	
		this.partialSolution.add(board);
	}
	
	// Deep copy constructor
	public PartialSolution(Board board, PartialSolution parent) {
		for(Board i : parent.getPartialSolution()) {
			this.partialSolution.add(i);
		}
		this.partialSolution.add(board);
	}
	
	// Get the last board 
	public Board getLastBoard() {
		return partialSolution.get(partialSolution.size()-1);
	}
	
	public List<Board> getPartialSolution() {
		return partialSolution;
	}

	// Print path
	public void print() {
		for (Board b: partialSolution) {
			b.print();
		}
	}
}
