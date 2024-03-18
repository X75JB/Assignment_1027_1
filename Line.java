package LetterCrush;

public class Line {
	private int[] start, end;
	
	// Constructor - [PUBLIC LINE]
	public Line(int row, int col, boolean horizontal, int length) {
		start = new int[] {row, col};
		if (horizontal) {
			end = new int[] {row, col + length - 1};
		} else {
			end = new int[] {row + length - 1, col};
		}
	}
	
	// Constructor - [GETSTART]
	public int[] getStart() {
		int[] copy = new int[2];
		System.arraycopy(start, 0, copy, 0, 2);
		return copy;
	}
	
	// Length of line
	public int length() {
		return (isHorizontal()) ? (end[1] - start[1] + 1) : (end[0] - start[0] + 1);
	}
	
	// Horizontal check
	public boolean isHorizontal() {
		return start[0] == end[0];
	}
	
	// Checking if a position is in the line
	public boolean inLine(int row, int col) {
		boolean withinRowRange = (row >= start[0] && row <= end[0]);
        boolean withinColRange = (col >= start[1] && col <= end[1]);
        return withinRowRange && withinColRange;
	}
	
	// Representing the lines as strings
	public String toString() {
		return "Line:[" + start[0] + "," + start[1] + "]->[" + end[0] + "," + end[1] + "]";
	}
	
}