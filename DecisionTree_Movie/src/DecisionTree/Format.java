package DecisionTree;

public class Format {
    //Keep first character as upper case and other are lower case.
    public static String ConStr(String str) {
        StringBuffer s = new StringBuffer();

        // To identify that the next character is the starting
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {

            // check whether previous character is space or not
            // if it isn't space, make first character as upper case.
            if (ch == ' ' && str.charAt(i) != ' ')
                s.append(Character.toUpperCase(str.charAt(i)));
            else
                s.append(str.charAt(i));
                ch = str.charAt(i);
        }
        // Return the string
        return s.toString().trim();
    }
}
