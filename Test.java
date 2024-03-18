package LetterCrush;

public class Test {
    public static void main(String[] args) {
//    	LetterCrush lc2 = new LetterCrush(6, 5, "BCAABBBACABCABCCCCAAACCCACCABC");
//		Line longest = lc2.longestLine();
//		LetterCrush lc3 = new LetterCrush(5, 5, "DCEABBCBACECDCDBCEBEADAECAACBA");
//		Line longest2 = lc3.longestLine();
//		lc3.remove(longest2);
//		lc3.applyGravity();
//		System.out.println(lc2);
//		System.out.println(longest);
//		System.out.println(lc3);
//		System.out.println(longest2);
    	
    	LetterCrush lc4 = new LetterCrush(7, 4, "BCBABBCCCBBBAABBBAAAACCBBCCBBC");
    	System.out.println(lc4);
    	lc4.cascade();
		String lcString = lc4.toString();
//		System.out.println(lcString);
    }
}

