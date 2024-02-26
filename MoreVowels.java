public class MoreVowels {

    public static void main(String [] args) {
        System.out.println("Hello vowels"); // Hello vowels
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.println("java MoreVowels <input>");
            return;
        }
        if (args == null || args.length == 0) { // check for args
            System.out.println("String to check for vowels is mandatory");
            return;
        }
        MoreVowels myVowels = new MoreVowels();
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
        for (int i = 0; i < n; i++) {
            char ch = original.toLowerCase().charAt(i);
            // Check to see if ith character is a vowel
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                sum += (i + 1) * (n - i);
            }
        }
    }
}
