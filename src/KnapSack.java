import java.io.File;
import java.io.IOException;
import java.util.*;

public class KnapSack{
    static int nodeCount = 0;
    PriorityQueue<KnapSack.Node> sack;
    private int[][] table;
    private final int COLUMNS = 4;
    private int weight, itemCount;

    public static void main(String[] args)throws IOException {
        String file;
        if (args.length == 0) {
            System.out.println("Please enter the location of the Knapsack input file: ");
            file = new Scanner(System.in).next();
        } else file = args[0];
        try{
            new KnapSack(new Scanner(new File(file)));
        }catch (IOException e){
            System.out.println("The file cannot be found");
        }
    }

    public KnapSack(Scanner file){
        weight = file.nextInt();
        itemCount = file.nextInt();
        table = new int[itemCount][COLUMNS];
        for (int i = 0; i<itemCount; i++) {
            table[i][0] = i + 1;
            table[i][1] = file.nextInt();
            table[i][2] = file.nextInt();
            table[i][3] = (table[i][1] / table[i][2]);
        }
        Comparator<KnapSack.Node> comparator = new NodeCompare();
        sack = new PriorityQueue<>(comparator);
        //branchAndBound();
    }

    private void branchAndBound(){

        Node root = new Node(null, 0, 0, calculateBound(new ArrayList<>()));
    }

    private int calculateBound(ArrayList<Integer> excludedItems){
        int bound = 0, remainingBoundWeight = weight;
        for(int i =0; remainingBoundWeight > 0 && i<6; i++){
            if(excludedItems.contains(table[i][0])) continue;
            if(remainingBoundWeight >= table[i][2]){
                bound += table[i][1];
                remainingBoundWeight -= table[i][2];
            }else{
                bound += remainingBoundWeight * table[i][3];
                remainingBoundWeight -= table[i][2];
            }
        }

        return bound;
    }

    public int test(ArrayList<Integer> testList){
        return calculateBound(testList);
    }

    private class NodeCompare implements Comparator<KnapSack.Node>{
        public int compare(Node one, Node two){
            return one.bound - two.bound;
        }
    }

    private class Node{
        int nodeNum;
        int runningWeight, runnningProfit, bound;
        String itemsInNode;

        Node(Node Parent){
            nodeNum = nodeCount++;

        }
        Node(String items, int profit, int weight, int bound){
            nodeNum = nodeCount++;
            this.itemsInNode = items;
            this.bound = bound;
            this.runnningProfit = profit;
            this.runningWeight = weight;

        }

    }
}
