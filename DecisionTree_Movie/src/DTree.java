import DecisionTree.DTreeComparator;
import DecisionTree.DTreeEntropy;
import DecisionTree.TreeNode;
import DecisionTree.Format;
import java.util.*;
import java.io.*;

//Main driver
public class DTree {

    //Root Node
    TreeNode root;

    // a visiable data group
    private boolean[] visiable;

    //can't find node, constants ,it never change
    private static final int NO_FOUND = -1;

    //training set
    private Object[] trainingDataset;

    //Nodeindex
    private int nodeIndex;

    public static void main(String[] args) throws IOException
    {
        //Allow user to enter the value.

        String userData[] = new String[4];

        Scanner input = new Scanner(System.in);
        //All country are upper case.
        System.out.println("Enter Country Name:"+
                "\n(The list of countries:Sweden,Iran,New Zealand,Japan,South Korea," +
                "\nMexico,Netherlands,Hong Kong,Italy,Russia,Ireland,Canada,Spain," +
                "\nUK,Belgium,USA,Australia,France,Denmark,Germany,China,India,"+
                "\nArgentina,Other)");
        userData[0] = input.nextLine().toUpperCase();
        //Keep first character as upper case for Genre, Budget and Bigstar.
        System.out.println("\nEnter Genre of Movie: "+
                "\n(The list of genres: Crime,Adventure,Comedy,Animation," +
                "\nWestern,Fantasy,Sci-Fi,Musical,Thriller,Family," +
                "\nRomance,Action,Horror,Drama,War,Mystery,Biography)");
        userData[1] = Format.ConStr(input.nextLine().toLowerCase());
        System.out.println("\nEnter a rating of movie:" +
                "\n(G,PG,PG-13,R,NC-17,UNRATED,NOT SPECIFIED,NOT RATED)");
        userData[2] = input.nextLine().toUpperCase();
        System.out.println("\nIs there a big Star or not: Yes/No");
        userData[3] = Format.ConStr(input.nextLine().toLowerCase());

        //find the csv file path and put all dataset into the array.
        BufferedReader br = new BufferedReader(new FileReader("/Users/Dexin/Desktop/DecisionTree_Movie/src/movies.csv"));

        ArrayList lines = new ArrayList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            //comma will be replaced with .
            line = line.replaceAll(",", "\\.");
            //each line will be stored in an array with . as separator
            String[] fields = line.split("\\.");
            //System.out.println(" " + fields[0]);
            lines.add(fields);
        }

        String[][] strings = (String[][]) lines.toArray(new String[lines.size()][]);

        Object[] array = new Object[6280];


        for(int i = 0; i < array.length; i++){
            String[] str = new String[5];
            for(int j = 0; j < strings[0].length; j++){
                str[j] = strings[i][j];
            }
            array[i] = str;
        }

        DTree NewDTree = new DTree();

        //decision tree, first 4 columns will influence on the result.
        NewDTree.create(array, 4);
        System.out.println("\nWhether the movie will be successful or not:");
        //get teh final answer whether the movie will be successful or not.
        NewDTree.compare(userData,NewDTree.root);
    }

    // According to the user data to predict
    public void compare(String[] userData,TreeNode node)
    {
        int index = getNodeIndex(node.nodeName);
        if (index == NO_FOUND)
        {
            System.out.println(node.nodeName);
        }
        TreeNode[] childs = node.childNodes;
        for (int i = 0; i <childs.length; i++)
        {
            if (childs[i] != null)
            {
                if (childs[i].parentAttribute.equals(userData[index]))
                {
                    compare(userData,childs[i]);
                }
            }
        }
    }

    // start to create
    public void create(Object[] array, int index)
    {
        this.trainingDataset = array;
        dataInitialize(array, index);
        createDTree(array);
    }

    //initialization
    public void dataInitialize(Object[] dataArray, int index)
    {
        this.nodeIndex = index;

        //Data initialization
        this.visiable = new boolean[((String[])dataArray[0]).length];
        for (int i = 0; i<this.visiable.length; i++)
        {
            if (i == index)
            {
                this.visiable[i] = true;
            }
            else
            {
                this.visiable[i] = false;
            }
        }
    }

    //create a decision tree
    public void createDTree(Object[] array)
    {
        Object[] maxgain = getMaxGain(array);
        if (this.root == null)
        {
            this.root = new TreeNode();
            root.parent = null;
            root.parentAttribute = null;
            root.attributes = getAttributes(((Integer)maxgain[1]).intValue());
            root.nodeName = getNodeName(((Integer)maxgain[1]).intValue());
            root.childNodes = new TreeNode[root.attributes.length];
            insertTree(array, root);
        }
    }

    // insert the tree node in the tree
    public void insertTree(Object[] array, TreeNode parentNode)
    {
        String[] attributes = parentNode.attributes;
        for (int i = 0; i < attributes.length; i++)
        {
            Object[] pickArray = pickAndCreateArray(array, attributes[i],getNodeIndex(parentNode.nodeName));
            Object[] info = getMaxGain(pickArray);
            double gain = ((Double)info[0]).doubleValue();
            if (gain != 0)
            {
                int index = ((Integer)info[1]).intValue();
                TreeNode currentNode = new TreeNode();
                currentNode.parent = parentNode;
                currentNode.parentAttribute = attributes[i];
                currentNode.attributes = getAttributes(index);
                currentNode.nodeName = getNodeName(index);
                currentNode.childNodes = new TreeNode[currentNode.attributes.length];
                parentNode.childNodes[i] = currentNode;
                insertTree(pickArray, currentNode);
            }

            else
            {
                TreeNode leafNode = new TreeNode();
                leafNode.parent = parentNode;
                leafNode.parentAttribute = attributes[i];
                leafNode.attributes = new String[0];
                leafNode.nodeName = getLeafNodeName(pickArray);
                leafNode.childNodes = new TreeNode[0];
                parentNode.childNodes[i] = leafNode;
            }
        }
    }

    // Clipping the array
    public Object[] pickAndCreateArray(Object[] array, String attribute, int index)
    {
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < array.length; i++)
        {
            String[] strs = (String[])array[i];
            if (strs[index].equals(attribute))
            {
                list.add(strs);
            }
        }
        return list.toArray();
    }

    //get the node name
    public String getNodeName(int index)
    {
        String[] strs = new String[]{"Country","Genre","Budget","BigStar","Rating","Success"};
        for (int i = 0; i < strs.length; i++)
        {
            if (i == index)
            {
                return strs[i];
            }
        }
        return null;
    }

    //get the leaf node name.
    public String getLeafNodeName(Object[] array)
    {
        if (array != null && array.length > 0)
        {
            String[] strs = (String[])array[0];
            return strs[nodeIndex];
        }
        return null;
    }

    //get nodeindex
    public int getNodeIndex(String name)
    {
        String[] strs = new String[]{"Country","Genre","Budget","BigStar","Rating","Success"};
        for (int i = 0; i < strs.length; i++)
        {
            if (name.equals(strs[i]))
            {
                return i;
            }
        }
        return NO_FOUND;
    }

    //Entropy
    public Object[] getMaxGain(Object[] array)
    {
        Object[] result = new Object[2];
        double gain = 0;
        int index = -1;
        for (int i = 0; i<this.visiable.length; i++)
        {
            if (!this.visiable[i])
            {
                double value = gain(array, i);
                if (gain < value)
                {
                    gain = value;
                    index = i;
                }
            }
        }
        result[0] = gain;
        result[1] = index;
        if (index != -1)
        {
            this.visiable[index] = true;
        }
        return result;
    }

    //Get the property array
    public String[] getAttributes(int index)
    {
        TreeSet<String> set = new TreeSet<String>(new DTreeComparator());
        for (int i = 0; i<this.trainingDataset.length; i++)
        {
            String[] strs = (String[])this.trainingDataset[i];
            set.add(strs[index]);
        }
        String[] result = new String[set.size()];
        return set.toArray(result);

    }

    //Entropy
    public double gain(Object[] array, int index)
    {
        String[] success = getAttributes(this.nodeIndex);
        int[] counts = new int[success.length];
        for (int i = 0; i<counts.length; i++)
        {
            counts[i] = 0;
        }

        for (int i = 0; i<array.length; i++)
        {
            String[] strs = (String[])array[i];
            for (int j = 0; j<success.length; j++)
            {
                if (strs[this.nodeIndex].equals(success[j]))
                {
                    counts[j]++;
                }
            }
        }

        double entropyS = 0;
        for (int i = 0;i <counts.length; i++)
        {
            entropyS = entropyS + DTreeEntropy.sigma(counts[i], array.length);
        }

        String[] attributes = getAttributes(index);
        double sv_total = 0;
        for (int i = 0; i<attributes.length; i++)
        {
            sv_total = sv_total + entropySv(array, index, attributes[i], array.length);
        }
        return entropyS - sv_total;
    }

    //(|Sv| / |S|) * Entropy(Sv)
    public double entropySv(Object[] array, int index, String attribute, int allTotal)
    {
        String[] success = getAttributes(this.nodeIndex);
        int[] counts = new int[success.length];
        for (int i = 0; i < counts.length; i++)
        {
            counts[i] = 0;
        }

        for (int i = 0; i < array.length; i++)
        {
            String[] strs = (String[])array[i];
            if (strs[index].equals(attribute))
            {
                for (int k = 0; k<success.length; k++)
                {
                    if (strs[this.nodeIndex].equals(success[k]))
                    {
                        counts[k]++;
                    }
                }
            }
        }

        int total = 0;
        double entropySv = 0;
        for (int i = 0; i < counts.length; i++)
        {
            total = total +counts[i];
        }

        for (int i = 0; i < counts.length; i++)
        {
            entropySv = entropySv + DTreeEntropy.sigma(counts[i], total);
        }
        return DTreeEntropy.getP(total, allTotal)*entropySv;
    }
}