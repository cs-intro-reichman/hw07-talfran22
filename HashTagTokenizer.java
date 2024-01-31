

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);

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

	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0 ; i < dictionary.length - 1 ; i++){
			if(word.equals(dictionary[i])){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
		String newhash = hashtag.toLowerCase();
        for (int i = 1; i <= N; i++) {
			if(existInDictionary(newhash.substring(0, i), dictionary)){
				System.out.println(newhash.substring(0, i));
				breakHashTag(newhash.substring(i, N), dictionary);
				return;
			}
			
        }
    }

}
