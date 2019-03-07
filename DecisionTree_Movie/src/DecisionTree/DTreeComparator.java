package DecisionTree;
import java.util.Comparator;
//compares the first object with the second object.
public class DTreeComparator implements Comparator
{
    public int compare(Object o1, Object o2) throws ClassCastException{
        String str1 = (String)o1;
        String str2 = (String)o2;
        return str1.compareTo(str2);
    }
}
