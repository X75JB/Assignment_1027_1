package LetterCrush;

public class LetterCrush {
	private char[][] grid;
	private static final char EMPTY = ' ';
	
	// Constructor to initialize the grid's width, height, and initial string
	public LetterCrush(int width, int height, String initial) {
		// Initialize the grid
		grid = new char[height][width];
        int currentIndex = 0;
        // Fill the grid with characters from the initial string
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (currentIndex < initial.length()) {
                    grid[row][col] = initial.charAt(currentIndex);
                    currentIndex++;
                } else {
                    grid[row][col] = EMPTY;
                }
            }
        }
    }
	
	// Method to generate a string representation of the grid
	public String toString() {
        StringBuilder result = new StringBuilder("LetterCrush\n");
        for (int row = 0; row < grid.length; row++) {
            result.append("|");
            for (int col = 0; col < grid[row].length; col++) {
                result.append(grid[row][col]);
            }
            result.append("|" + row + "\n");					
		}
        result.append("+");
        for (int col = 0; col < grid[0].length; col++) {
        	result.append(col);
        }
        result.append("+");
        return result.toString();
	}
	
	public boolean isStable() {
	    for (int col = 0; col < grid[0].length; col++) {
	        boolean emptyFound = false;

	        for (int row = grid.length - 1; row >= 0; row--) {
	            if (grid[row][col] == EMPTY) {
	                emptyFound = true;
	            } else if (emptyFound) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	// Method used to move cells down when a non-char cell is below it, similar to real life gravity
	public void applyGravity() {
		for (int row = grid.length - 1; row > 0; row--) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] == EMPTY) {
					for (int i = row - 1; i >= 0; i--) {
						if (grid[i][col] != EMPTY) {
							grid[row][col] = grid[i][col];
							grid[i][col] = EMPTY;
							break;
						}
					}
				}
			}
		}
	}
	
	// Method used to remove a line of chars from the grid
	public boolean remove(Line theLine) {
	    // Extracting the start and end points of the line
		int[] start = theLine.getStart();
	    int[] end;
	    
	    if (theLine.isHorizontal()) {
	        end = new int[]{start[0], start[1] + theLine.length() - 1};
	    } else {
	        end = new int[]{start[0] + theLine.length() - 1, start[1]};
	    }

	    // Check if line is within grid bounds
	    if (start[0] < 0 || start[0] >= grid.length || start[1] < 0 || start[1] >= grid[0].length ||
	        end[0] < 0 || end[0] >= grid.length || end[1] < 0 || end[1] >= grid[0].length) {
	        return false;
	    }
	    
	 // Remove characters within the line
	    for (int row = start[0]; row <= end[0]; row++) {
	        for (int col = start[1]; col <= end[1]; col++) {
	            grid[row][col] = EMPTY;
	        }
	    }
	    return true;
	}
	
	// Method to generate a string representation of the grid
	public String toString(Line theLine) {
	    int[] start = theLine.getStart();
	    int endRow = theLine.isHorizontal() ? start[0] : start[0] + theLine.length() - 1;
	    int endCol = theLine.isHorizontal() ? start[1] + theLine.length() - 1 : start[1];

	    StringBuilder result = new StringBuilder("CrushLine\n");
	    for (int row = 0; row < grid.length; row++) {
	        result.append("|");
	        for (int col = 0; col < grid[row].length; col++) {
	            char currentChar = grid[row][col];
	            if ((theLine.isHorizontal() && row == start[0] && col >= start[1] && col <= endCol) ||
	                (!theLine.isHorizontal() && col == start[1] && row >= start[0] && row <= endRow)) {
	                currentChar = Character.toLowerCase(currentChar);
	            }
	            result.append(currentChar);
	        }
	        result.append("|").append(row).append("\n");
	    }
	    result.append("+");
	    for (int col = 0; col < grid[0].length; col++) {
	        result.append(col);
	    }
	    result.append("+");

	    return result.toString();
	}
	
	// Method to find the longest line of consecutive characters in the grid
	public Line longestLine() {
        Line longest = null;
        int longestLength = 0;

        // Checking for horizontal lines
        for (int row = 0; row < grid.length; row++) {
            char currentChar = grid[row][0];
            int currentLength = 1;
            for (int col = 1; col < grid[row].length; col++) {
                if (grid[row][col] == currentChar && currentChar != EMPTY) {
                    currentLength++;
                } else {
                    currentChar = grid[row][col];
                    currentLength = 1;
                }
                if (currentLength >= 3 && currentLength > longestLength) {
                    longestLength = currentLength;
                    longest = new Line(row, col - currentLength + 1, true, currentLength);
                }
            }
        }

        // Checking for vertical lines
        for (int col = 0; col < grid[0].length; col++) {
            char currentChar = grid[grid.length - 1][col];
            int currentLength = 1;
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] == currentChar && currentChar != EMPTY) {
                    currentLength++;
                } else {
                    currentChar = grid[row][col];
                    currentLength = 1;
                }
                if (currentLength >= 3 && currentLength > longestLength) {
                    longestLength = currentLength;
                    longest = new Line(row, col, false, currentLength);
                }
            }
        }

        return longest;
    }
		
	// Method to repeatedly remove and apply gravity until no lines of 3 or more are left
	public void cascade() {
        while (true) {
            Line longest = longestLine();
            if (longest != null && longest.length() >= 3) {
                remove(longest);
                applyGravity();
            } else {
                break; 
            }
        }
    }
}