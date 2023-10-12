package com.dominotrainbuilder;
//package com.exceptions;
import com.dominotrainbuilder.TrainBuilder;
import com.dominotrainbuilder.Domino;
import com.exceptions.InvalidRangeException;

import com.dominotrainbuilder.TrainBuilder;

import java.util.*;

public class App {

    private Scanner scanner = new Scanner(System.in);

    /** Returns the number of dominoes in hand */
    public int getNumberOfDominoes() {
        int numDom = 0;
        boolean isValid = false;

        // Prompt user to enter number of dominoes and loop until input is valid
        while (!isValid) {
            try {
                System.out.println("Enter the number of dominoes in your hand: ");
                numDom = scanner.nextInt();
                // Check if number of dominoes is out of range
                if (numDom < 5 || numDom > 15)
                    throw new InvalidRangeException("Invalid input. Number should be in the range 10-15, inclusive.");
                isValid = true;
            } catch (InvalidRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Must be an integer.");
                scanner.next();
            }
        }

        scanner.nextLine();

        return numDom;
    }


    /** Returns the starting domino */
    public int getStartingDomino() {
        int startingDom = 0;
        boolean isValid = false;

        // Prompt user to enter the starting domino and loop until input is valid
        while (!isValid) {
            try {
                System.out.println("Enter a single value for the starting domino in the middle of the train hub."
                        + " For example, if the starting double is the double 12, enter 12. \nNote: the double blank "
                        + "should be entered as 0.");
                startingDom = scanner.nextInt();
                // Check if the starting domino is out of range
                if (startingDom < 0 || startingDom > 12)
                    throw new InvalidRangeException("Invalid input. Number should be in the range 0-12, inclusive.");
                isValid = true;
            } catch (InvalidRangeException e) {
                System.out.println(e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Must be an integer.");
                scanner.next();
            }
        }

        scanner.nextLine();

        return startingDom;
    }

    /** Returns an array list of dominoes which represents the user's hand */
    public List<Domino> getUserHand(int numDom, int startingDom) {
        System.out.println("\nNow enter your dominoes. Type a domino in the format number,number. Order doesn't " +
                "matter. \nFor example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5. Note: a blank should be" +
                " entered as 0.");

        // Create an array list to store the user's dominoes
        List<Domino> dominoesInHand = new ArrayList<>();

        // Prompt user to enter each domino in their hand
        for (int i = 1; i <= numDom; i++) {
            boolean isValidInput = false;
//            String domino = "";
//            String[] dominoValues;
//            int value1 = -1;
//            int value2 = -1;

            // Loop until input is valid
            while (!isValidInput) {
                try{
                    System.out.println("Enter domino  " + i + " : ");
                    // Read in the domino
                    String input = scanner.nextLine();

                    String [] dominoValues = input.split(",");
                    // Check that format is correct
                    if(dominoValues.length != 2)
                        throw new InvalidRangeException("Invalid Input. Dominoes must be entered in the format " +
                                "number,number. For example, a domino with a 5 and a 4 can be entered as 5,4 or 4,5");

                    int value1 = Integer.parseInt(dominoValues[0]);
                    int value2 = Integer.parseInt(dominoValues[1]);

                    // Checking that numbers are in the correct range
                    if(value1 > 12 || value1 < 0 || value2 > 12 || value2 < 0)
                        throw new InvalidRangeException("Invalid input. Dominoes can only have values in the range 0-12," +
                                " inclusive. Note: a blank should be entered as 0.");

                    // Check that the user did not enter the starting double
                    if(value1 == startingDom && value2 == startingDom)
                        throw new InvalidRangeException("Invalid input. You cannot enter the double domino that is " +
                                "currently the starting double.");

                    // Check that this domino hasn't already been entered
                    for(Domino d : dominoesInHand){
                        if((d.getSide1() == value1 && d.getSide2() == value2) || (d.getSide1() == value2
                                && d.getSide2() == value1))
                            throw new InvalidRangeException("Invalid input. You cannot enter the same domino twice.");
                    }

                    // Add domino to the array list of dominoes
                    dominoesInHand.add(new Domino(value1, value2));
                    isValidInput = true;

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
        }

        return dominoesInHand;
    }

    /** Displays the longest train */
    public void displayLongestTrain(List<Domino> train) {
        int trainSize = train.size()-1;
        if (trainSize > 0) {
            System.out.println("Train size: " + trainSize);
            System.out.println("You can build the following train: ");

            for (int i = 0; i < train.size(); i++) {
                System.out.println(train.get(i).getSide1() + "," + train.get(i).getSide2());
            }
        }
    }


    /** Displays the train with the most points */
    public void displayHighestPointTrain(List<Domino> highestPointTrain) {
        // Implement code to display the highest point train.
    }

    /** Main method */
    public static void main(String[] args){

        App mexicanTrain = new App();
        TrainBuilder builder = new TrainBuilder();

        int numDominoes = mexicanTrain.getNumberOfDominoes();
        int startingDomino = mexicanTrain.getStartingDomino();
        List<Domino> userHand = mexicanTrain.getUserHand(numDominoes, startingDomino);
        List<Domino> longestTrain = builder.findLongestTrain(userHand, startingDomino);

        mexicanTrain.displayLongestTrain(longestTrain);

    }
}
