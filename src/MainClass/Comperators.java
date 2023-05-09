package MainClass;

import java.util.Comparator;

public class Comperators {
	
	public class ComperatorUcs implements Comparator<PartialSolution>{
		@Override
		public int compare(PartialSolution o1, PartialSolution o2) {
			int sum1 = AStar.ucs(o1);
			int sum2 = AStar.ucs(o1);
			return sum1-sum2;
		}
	}
	
	public class ComperatorMisplaced implements Comparator<PartialSolution>{
		@Override
		public int compare(PartialSolution o1, PartialSolution o2) {
			int sum1 = AStar.h_Misplaced(o1.getLastBoard()) + AStar.ucs(o1);
			int sum2 = AStar.h_Misplaced(o2.getLastBoard()) + AStar.ucs(o1);
			return sum1-sum2;
		}
	}
	
	public class ComperatorManhattan implements Comparator<PartialSolution>{
		@Override
		public int compare(PartialSolution o1, PartialSolution o2) {
			int sum1 = AStar.h_Manhattan(o1.getLastBoard()) + AStar.ucs(o1);
			int sum2 = AStar.h_Manhattan(o2.getLastBoard()) + AStar.ucs(o1);
			return sum1-sum2;
		}
	}
	
	public class ComperatorCloseSet implements Comparator<Board>{
		@Override
		public int compare(Board o1, Board o2) {
			if(o1.equals(o2)) {
				return 0;
			}
			return 1;
			
		}
	}
}
