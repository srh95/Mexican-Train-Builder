import com.exceptions.InvalidRangeException;

import java.util.*;

public class Train {

    /**
     * Represents a connection between two vertices in the graph
     */
    static class Edge {
        final private int v1;
        final private int v2;
        final private int weight;

        /**
         * Create a new domino connection
         */
        public Edge(int value1, int value2) {
            this.v1 = value1;
            this.v2 = value2;
            this.weight = v1 + v2;
        }

        /**
         * Returns the point value of the domino
         */
        public int getWeight() {
            return (this.weight);
        }

        /**
         * Returns the value on one side of the domino
         */
        public int getValue1() {
            return (this.v1);
        }

        /**
         * Returns the value on one side of the domino
         */
        public int getValue2() {
            return (this.v2);
        }

        /**
         * Prints the edge and the weight associated with it
         */
        @Override
        public String toString() {
            return "Edge between " + this.v1 + " and " + this.v2 + " with weight " + this.weight;
        }
    }

    /**
     * Represents a vertex in the graph
     */
    static class Node {
        final private int value;
        final private int weight;

        /**
         * Create a new vertex
         */
        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        /**
         * Returns the point value associated with the vertex
         */
        private int getValue() {
            return this.value;
        }

        /**
         * Prints the vertex and the weight associated with it
         */
        @Override
        public String toString() {
            return this.value + " (" + this.weight + ")";
        }

        @Override
        public boolean equals(Object o) {
            // If the object is compared with itself then return true
            if (o == this) {
                return true;
            }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
            if (!(o instanceof Node)) {
                return false;
            }

            // typecast o to Complex so that we can compare data members
            Node n = (Node) o;

            // Compare the values and return
            return Double.compare(this.value, n.value) == 0;

        }
    }


    /**
     * Represents graph
     */
    static class Graph {
        // adjacency list storing connections between nodes
        private final List<List<Node>> adjacencyList = new ArrayList<>();
        // adjacency list storing edges that have already been visited when searching the graph
        private final List<List<Node>> edgeList = new ArrayList<>();

        /**
         * Creates a graph from the list of edges
         */
        Graph(List<Edge> edges) {
            // Stores the maximum numbered vertex
            int n = 0;

            //iterates over the edges of the graph
            for (Edge e : edges) {
                // Determines the maximum numbered vertex
                n = Integer.max(n, Integer.max(e.getValue1(), e.getValue2()));
            }

            // Reserves space for the adjacency list and edge list
            for (int i = 0; i <= n; i++) {
                this.adjacencyList.add(i, new ArrayList<>());
                this.edgeList.add(i, new ArrayList<>());
            }

            // Adds the edges to the undirected graph
            for (Edge e : edges) {
                // If the domino is not a double, add new node for each value
                if (e.getValue1() != e.getValue2()) {
                    this.adjacencyList.get(e.getValue1()).add(new Node(e.getValue2(), e.getWeight()));
                    this.adjacencyList.get(e.getValue2()).add(new Node(e.getValue1(), e.getWeight()));
                } else {
                    this.adjacencyList.get(e.getValue1()).add(new Node(e.getValue2(), e.getWeight()));
                }
            }
        }


        /**
         * Prints the adjacency list of a graph
         */
        private void printGraph() {
            int src = 0;
            int n = this.adjacencyList.size();
            System.out.print("Adjacency List for the Graph is: ");
            while (src < n) {
                // Prints the neighboring vertices with current vertex
                for (Node edge : this.adjacencyList.get(src)) {
                    System.out.printf("%d -- > %s\t", src, edge);
                }
                System.out.println();
                src++;
            }
        }

        /**
         * Prints the adjacency list of edges that have already been visited
         */
        private void printEdgeList() {
            int src = 0;
            int n = this.edgeList.size();
            System.out.print("Edge List for the Graph is: ");
            while (src < n) {
                // Prints the neighboring vertices with current vertex
                for (Node edge : this.edgeList.get(src)) {
                    System.out.printf("%d -- > %s\t", src, edge);
                }
                System.out.println();
                src++;
            }
        }

        /**
         * Helper method to check the sum of the integers in an array list
         */
        private int findSum(ArrayList<Integer> path) {
            int sum = 0;
            for (int i : path)
                sum += i;
            return sum;
        }

        private boolean containsDouble(int startNode){
            for (Node node : this.adjacencyList.get(startNode)) {
                if(node.getValue() == startNode)
                    return true;
            }
            return false;
        }

        /**
         * Perform a search for the longest possible train
         */
        private ArrayList<Integer> findLongestTrain(int startNode, ArrayList<Integer> currentPath,
                                                    ArrayList<Integer> longestPath) {

//            System.out.println("starting node value: " + startNode);
            try {
                // check if adjacency list for startNode contains double
                boolean containsDouble = this.containsDouble(startNode);

                for (Node node : this.adjacencyList.get(startNode)) {

                    // Checks if we have already visited the edge between the two nodes (or already used this domino)
                    boolean containsNode = false;
                    for (Node subNode : this.edgeList.get(startNode)) {
                        if (subNode.getValue() == node.getValue()) {
                            containsNode = true;
                            break;
                        }
                    }

                    // if double we do recursion but we just save up to 3 paths- we don't delete the current path when
                    // we go back to the double and iterate down another path

//                    System.out.println("child node value: " + node.getValue());
//                    System.out.println("Current path contains " + node.getValue() + " : " + currentPath.contains(node.getValue()));
//                    System.out.println("EdgeList contains " + startNode + "->" + node + " : " + containsNode);

                    // if the node is not in the current path or in the list of already visited nodes
                    if (!currentPath.contains(node.getValue()) || !containsNode) {
                        // add node to current path
                        currentPath.add(node.getValue());

//                        System.out.print("currentPath:  ");
//                        for (int i = 0; i < currentPath.size(); i++)
//                            System.out.print(currentPath.get(i) + ", ");
//                        System.out.print("\n");

//                    System.out.println("EdgeList contains the node in the startNode list: " + containsNode);
                        if (!containsNode) {
                            this.edgeList.get(startNode).add(node);
                            this.edgeList.get(node.getValue()).add(new Node(startNode, startNode + node.getValue()));
//                            this.printEdgeList();
                        }

                        // if the current path is longer than the longest path, set this as the longest path
                        if (currentPath.size() > longestPath.size()) {
                            longestPath.clear();
                            longestPath.addAll(currentPath);
                        }
                        // if the paths are the same length, choose the path worth more points
                        if (currentPath.size() == longestPath.size()) {
                            int currentSum = findSum(currentPath);
                            int longSum = findSum(longestPath);
                            if (currentSum > longSum) {
                                longestPath.clear();
                                longestPath.addAll(currentPath);
                            }
                        }

//                        System.out.print("longest Path:  ");
//                        for (int i = 0; i < longestPath.size(); i++)
//                            System.out.print(longestPath.get(i) + ", ");
//                        System.out.print("\n");

                        // check if node is a double
                        // if node is double, save the current path
                        // if we have more than 3 options coming off double, take the 3 with most points


                        // Use recursion to explore the child nodes of the current node in our path
                        findLongestTrain(node.getValue(), currentPath, longestPath);
                        // Remove last element in the current path
                        currentPath.remove(currentPath.size() - 1);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Unfortunately, you cannot build a train because you don't have a " + startNode + ".");
            }
            return longestPath;
        }

    }

    /**
     * Main method
     * Prompts user to enter dominoes and builds the longest train
     */
    public static void main(String[] args) {
        // *** Add option to select the longest train or the train w most points or both?? ***

        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        int numDom = 0;
        int startingDom = 0;

        // Prompt user to enter number of dominoes and loop until input is valid
        while (!isValid) {
            try {
                System.out.println("Enter the number of dominoes in your hand: ");
                numDom = myScanner.nextInt();
                // Check if number of dominoes is out of range
                if (numDom < 5 || numDom > 15)
                    throw new InvalidRangeException("Invalid input. Number should be in the range 10-15, inclusive.");
                isValid = true;
            } catch (InvalidRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Must be an integer.");
                myScanner.next();
            }
        }

        myScanner.nextLine();
        isValid = false;

        // Prompt user to enter the starting domino and loop until input is valid
        while (!isValid) {
            try {
                System.out.println("Enter a single value for the starting domino in the middle of the train hub."
                        + " For example, if the starting double is the double 12, enter 12. \nNote: the double blank "
                        + "should be entered as 0.");
                startingDom = myScanner.nextInt();
                // Check if the starting domino is out of range
                if (startingDom < 0 || startingDom > 12)
                    throw new InvalidRangeException("Invalid input. Number should be in the range 0-12, inclusive.");
                isValid = true;
            } catch (InvalidRangeException e) {
                System.out.println(e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Must be an integer.");
                myScanner.next();
            }
        }

        myScanner.nextLine();
        System.out.println("\nNow enter your dominoes. Type a domino in the format number,number. Order doesn't " +
                "matter. \nFor example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5. Note: a blank should be" +
                " entered as 0.");

        // Create an array list to store the user's dominoes
        List<Train.Edge> edges = new ArrayList<>();

        // Prompt user to enter each domino in their hand
        for (int i = 1; i <= numDom; i++) {
            isValid = false;
            String domino = "";
            String[] dominoValues;
            int value1 = -1;
            int value2 = -1;
            // Loop until input is valid
            while (!isValid) {
                try{
                    System.out.println("Enter domino  " + i + " : ");
                    // Read in the domino
                    domino = myScanner.nextLine();

                    dominoValues = domino.split(",");
                    value1 = Integer.parseInt(dominoValues[0]);
                    value2 = Integer.parseInt(dominoValues[1]);

                    // Check that format is correct
                    if(dominoValues.length != 2)
                        throw new InvalidRangeException("Invalid Input. Dominoes must be entered in the format " +
                                "number,number. For example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5");

                    // Checking that numbers are in the correct range
                    if(value1 > 12 || value1 < 0 || value2 > 12 || value2 < 0)
                        throw new InvalidRangeException("Invalid input. Dominoes can only have values in the range 0-12," +
                                " inclusive. Note: a blank should be entered as 0.");
                    if(value1 == startingDom && value2 == startingDom)
                        throw new InvalidRangeException("Invalid input. You cannot enter the double domino that is " +
                                "currently the starting double.");

                    // Convert blanks to point value 25
                    if(value1 == 0)
                        value1 = 25;
                    if(value2 == 0)
                        value2 = 25;
                    if(startingDom == 0)
                        startingDom = 25;

                    // Check that this domino hasn't already been entered
                    for(Train.Edge e : edges){
                        if((e.getValue1() == value1 && e.getValue2() == value2) || (e.getValue1() == value2
                                && e.getValue2() == value1))
                            throw new InvalidRangeException("Invalid input. You cannot enter the same domino twice.");
                    }

                    isValid = true;
                } catch(InvalidRangeException e){
                    System.out.println(e.getMessage());
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid Input. Dominoes must be entered in the format number,number. For " +
                            "example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5");
                }catch(NumberFormatException e){
                    System.out.println("Invalid Input. Dominoes must be entered in the format number,number. For " +
                            "example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5 ");
                }
            }

            // Add domino to the array list of dominoes
            edges.add(new Train.Edge(value1,value2));
        }

        // Create a new graph with the list of dominoes
        Train.Graph graph = new Train.Graph(edges);
        graph.printGraph();

        ArrayList<Integer> currentPath = new ArrayList<>();
        ArrayList<Integer> longestPath = new ArrayList<>();

        currentPath.add(startingDom);
//        Node start = new Node(startingDom, startingDom*2);
        ArrayList<Integer> train = graph.findLongestTrain(startingDom, currentPath, longestPath);
        int trainSize = train.size()-1;
        if (trainSize > 0) {
            System.out.println("You can build the following train: ");

            for (int i = 0; i < train.size() - 1; i++) {
                Collections.replaceAll(train, 25, 0);
                System.out.println(train.get(i) + "," + train.get(i + 1));
            }
            }
        }
    }





