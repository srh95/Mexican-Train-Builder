package com.dominotrainbuilder;
import com.dominotrainbuilder.Domino;

import java.util.ArrayList;
import java.util.List;

public class TrainBuilder {

    /**
     * Helper method to calculate the sum of the integers in an array list
     *
     * @param train: a list of dominoes
     * @return sum: the total number of points in this domino train
     */
    private int findSum(List<Domino> train) {
        int sum = 0;
        for (Domino d : train){
            // Check whether the domino has 0s on either side
            if (d.getSide1() == 0 || d.getSide2() == 0) {
                // Add 25 points for each 0 on the domino
                if (d.getSide1() == 0) {
                    sum += d.getSide2() + 25;
                } else if (d.getSide2() == 0) {
                    sum += d.getSide1() + 25;
                } else {
                    // Both sides are 0, so add 50 points
                    sum += 50;
                }
            } else {
                sum += d.getTotal();
            }
        }

        return sum;
    }


    /** Finds the longest train of dominoes */
    public List<Domino> findLongestTrain(List<Domino> userHand, int startingDomino){

        // Represents the longest possible train
        List<Domino> longestTrain = new ArrayList<>();
        // Represents the train actively being built
        List<Domino> currentPath = new ArrayList<>();

        // Create a list of all valid starting dominoes in the user's hand
        List<Domino> startingDominoes = new ArrayList<>();
        for(Domino domino : userHand) {
            if(domino.getSide1() == startingDomino) {
                startingDominoes.add(domino);
            } else if(domino.getSide2() == startingDomino){
                domino.flip();
                startingDominoes.add(domino);
            }
        }

        // Explore the train using each starting domino as the starting point to find the longest possible train
        for(Domino domino : startingDominoes){
            currentPath.clear();
            currentPath.add(domino);

            List<Domino> remainingHand = new ArrayList<>(userHand);
            remainingHand.remove(domino);

            exploreTrain(remainingHand, currentPath, longestTrain);
        }

        return longestTrain;
    }

    /** Helper method to find the longest train of dominoes */
    private void exploreTrain(List<Domino> remainingHand, List<Domino> currentPath, List<Domino> longestTrain){
        // Gets the last domino in the current path we are building
        Domino lastDomino = currentPath.get(currentPath.size() - 1);
        int lastValue = lastDomino.getSide2();


        for(Domino domino : remainingHand){

            if(domino.getSide2() == lastValue)
                domino.flip();
            if(domino.getSide1() == lastValue) {
                List<Domino> newPath = new ArrayList<>(currentPath);
                newPath.add(domino);

                List<Domino> newRemainingHand = new ArrayList<>(remainingHand);
                newRemainingHand.remove(domino);

                exploreTrain(newRemainingHand, newPath, longestTrain);

                if(newPath.size() >= longestTrain.size()){
                    int currentSum = findSum(newPath);
                    int longSum = findSum(longestTrain);
                    if (currentSum > longSum || newPath.size() > longestTrain.size()) {
                        longestTrain.clear();
                        longestTrain.addAll(newPath);
//                        System.out.println("Longest Path: ");
//                        for (int i = 0; i < longestTrain.size()-1; i++) {
//                            System.out.println(longestTrain.get(i).getSide1() + "," + longestTrain.get(i).getSide2());
//                        }
                    }
                }
            }
        }
    }

}
