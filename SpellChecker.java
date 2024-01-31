
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
		if(word1.length() == 0){
			return word2.length();
		}
		if(word2.length() == 0){
			return word1.length();
		}
		if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1),tail(word2));
		}
		int min = (int)(Math.min(levenshtein(tail(word1), word2),  levenshtein(word1, tail(word2))));
		return 1 + (int)(Math.min(min, levenshtein(tail(word1), tail(word2))));
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
