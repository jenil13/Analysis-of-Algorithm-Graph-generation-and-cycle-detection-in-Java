package graph;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class DetectCyclesClass {
    static int LimitEdgesTotal(int calculateTotalVertices) {

        return calculateTotalVertices * ((calculateTotalVertices - 1) / 2);
    }

    public static void main(String[] args) {

// calculated the time for different vertex file and plotted the graph
        Random random = new Random();
        System.out.println("large vertices around 1000");
        int junction = random.nextInt(55) + 1;
        System.out.println(junction);
        graph.GraphCyclesForDetectCycles a = new graph.GraphCyclesForDetectCycles(junction);


//        int vertices = random.nextInt(15000) + 1;
        int edgesNumber = random.nextInt(LimitEdgesTotal(junction)) + 1;
        System.out.println(edgesNumber);
        for (int i = 0; i < edgesNumber; i++) {
            // randomly select two vertices to
            // create an edge between them
            int c = random.nextInt(junction);
            int f = random.nextInt(junction);

            // add an edge between them
            a.FormingGraph(c, f);
        }
        //Test Case

// calculated the time for different vertex file and plotted the graph
        System.out.println("Test Case for random vertices and edges:");
        long k = System.nanoTime();
        if (a.checkingGraphHasCycle()) {
            System.out.println("Detect Cycles");
        } else {
            System.out.println("No cycles Detected");
        }
        long m = System.nanoTime();
        System.out.println(m - k);

//
        //Test case 1
        System.out.println("Test CASE 1 :");
        int junction1 = 4;
        graph.GraphCyclesForDetectCycles B = new graph.GraphCyclesForDetectCycles(junction1);
        B.FormingGraph(0, 1);
        B.FormingGraph(1, 2);
        B.FormingGraph(2, 3);
        if (B.checkingGraphHasCycle()) {
            System.out.println("Detect Cycles");
        } else {
            System.out.println("No cycles Detected");
        }

        System.out.println("Test CASE 2 :");
        int junction2 = 6;
        graph.GraphCyclesForDetectCycles C = new graph.GraphCyclesForDetectCycles(junction2);
        C.FormingGraph(1, 0);
        C.FormingGraph(0, 2);
        C.FormingGraph(2, 3);
        C.FormingGraph(2, 1);
        C.FormingGraph(4, 5);

        if (C.checkingGraphHasCycle()) {
            System.out.println("Detect Cycles");
        } else {
            System.out.println("No cycles Detected");
        }


        System.out.println("Test CASE 3 :");


        System.out.println("When given case consist of disconnencted graph");
        int junction3 = 7;
        graph.GraphCyclesForDetectCycles F = new graph.GraphCyclesForDetectCycles(junction3);
        F.FormingGraph(0, 1);
        F.FormingGraph(1, 2);
        F.FormingGraph(3, 4);
        F.FormingGraph(5, 6);
        if (B.checkingGraphHasCycle()) {
            System.out.println("Detect Cycles");
        } else {
            System.out.println("No cycles Detected");
        }
//
//
   }
}



class GraphCyclesForDetectCycles {
    Stack<Integer> stackdetectcycle = new Stack<>();

    List<List<Integer>> listOfGraph;
    boolean traversed[];
    int junction;

    GraphCyclesForDetectCycles(int junction) {
        listOfGraph = new ArrayList<>();
        traversed = new boolean[junction];
        this.junction = junction;
        int i = 0;
        while (i < junction) {
            listOfGraph.add(i, new ArrayList<>());
            i = i + 1;
        }
    }

    public void FormingGraph
            (int a, int b) {
        listOfGraph.get(a).add(b);
        listOfGraph.get(b).add(a);
    }

    public boolean checkingGraphHasCycle() {
        int i = 0;
        while (i < junction) {
            if (!traversed[i]) {
                if (recursivelyCheckingCycle(i, -1)) {
                    System.out.println(Arrays.toString(traversed));
                    System.out.println(stackdetectcycle.toString());
                    return true;
                }
            }
            i = i + 1;
        }

        System.out.println(Arrays.toString(traversed));
        return false;
    }

    public boolean recursivelyCheckingCycle(int index, int parent) {

        traversed[index] = true;
        stackdetectcycle.push(index);

        List<Integer> nearbyList = listOfGraph.get(index);

        for (Integer nearby : nearbyList) {
            if (!traversed[nearby]) {
                return recursivelyCheckingCycle(nearby, index);
            } else if (nearby != parent) {
                if (traversed[nearby]) {
                    stackdetectcycle.push(nearby);

                }
                return true;
            }
        }
        stackdetectcycle.pop();
        return false;
    }
}
