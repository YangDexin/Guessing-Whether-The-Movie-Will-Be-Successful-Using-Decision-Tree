package DecisionTree;
/*
** Entropy of a set is defined as:
** H(S) = - p0 log2 p0  -  p1 log2 p1 - ... (if there are other classes)
*/
public class DTreeEntropy {

    public static double sigma(int x, int total)
    {
        if (x == 0)
        {
            return 0;
        }
        double x_p = getP(x,total);
        return -(x_p*logYBase2(x_p));
    }

    //Log[2] y(input),the input is x_pi.
    public static double logYBase2(double y)
    {
        return (Math.log(y) / Math.log(2));
    }


    public static double getP(int x, int total)
    {
        return x * Double.parseDouble("1.0") / total;
    }
}
