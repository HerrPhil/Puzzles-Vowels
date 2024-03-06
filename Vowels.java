public class Vowels {

    public static void main(String [] args) {
        System.out.println("Hello vowels"); // Hello vowels
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.println("java Vowels <input>");
            return;
        }
        if (args == null || args.length == 0) { // check for args
            System.out.println("String to check for vowels is mandatory");
            return;
        }
        Vowels myVowels = new Vowels();
        myVowels.check(args);
        System.out.println(String.format("%s has %d vowels in all the substrings", myVowels.getOriginal(), myVowels.getSum()));
    }

    private String original;
    private int sum;

    String getOriginal() {
        return original;
    }

    int getSum() {
        return sum;
    }

    private void check(String [] args) {
        original = args[0];

        int n = original.length();
        int occurrences[] = new int[n];

        for (int i = 0; i < n; i++) 
        { 
            if (i == 0) {
                // Number of times the 0th character occurs in all substrings
                occurrences[i] = n;
            } 
            else {
                // Number of times the ith character occurs in all substrings
                occurrences[i] = (n - i) + occurrences[i - 1] - i;
            }
        }

        for (int i = 0; i < n; i++) {
            char ch = original.toLowerCase().charAt(i);
            // Check to see if ith character is a vowel
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                sum += occurrences[i];
            }
        }
    }
}
