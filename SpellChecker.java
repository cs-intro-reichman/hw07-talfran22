
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
			return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		String w1 = word1.toLowerCase();
		String w2 = word2.toLowerCase();
		if(w1.length() == 0){
			return w2.length();
		}
		if(w2.length() == 0){
			return w1.length();
		}
		if(w1.charAt(0) == w2.charAt(0)){
			return levenshtein(tail(w1),tail(w2));
		}
		int min = (int)(Math.min(levenshtein(tail(w1), w2),  levenshtein(w1, tail(w2))));
		return 1 + (int)(Math.min(min, levenshtein(tail(w1), tail(w2))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		int i = 0;
		In in = new In(fileName);

		while (!in.isEmpty()) {
			dictionary[i] = in.readLine();
			i++;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = word.length();
		String mini = word;
		for(int i = 0 ; i < dictionary.length ; i++){
			int levi = levenshtein(word, dictionary[i]);
			if(levi <= threshold){
				if(levi < min){
					min = levi;
					mini = dictionary[i];
				}
			}
		}
		return mini;
	}

}
