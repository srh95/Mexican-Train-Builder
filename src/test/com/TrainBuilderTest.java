package test.com;
import com.dominotrainbuilder.Domino;
import com.dominotrainbuilder.TrainBuilder;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Test class for the TrainBuilder class.
 */
public class TrainBuilderTest {

    @Test
    public void testFindLongestTrainCase1() {
        // Test
        TrainBuilder trainBuilder = new TrainBuilder();
        List<Domino> userHand = new ArrayList<>();

        // Define user hand
        userHand.add(new Domino(12,8));
        userHand.add(new Domino(1,8));
        userHand.add(new Domino(3,1));
        userHand.add(new Domino(3,9));
        userHand.add(new Domino(9,10));
        userHand.add(new Domino(10,2));
        userHand.add(new Domino(10,4));
        userHand.add(new Domino(4,0));
        userHand.add(new Domino(0,3));
        userHand.add(new Domino(5,2));
        userHand.add(new Domino(11,5));
        userHand.add(new Domino(7,7));

        int startingDomino = 12;

        List<Domino> longestTrain = trainBuilder.findLongestTrain(userHand, startingDomino);

        // Define expected longest train
        List<Domino> expectedLongestTrain = new ArrayList<>();
        expectedLongestTrain.add(new Domino(12,8));
        expectedLongestTrain.add(new Domino(8,1));
        expectedLongestTrain.add(new Domino(1,3));
        expectedLongestTrain.add(new Domino(3,0));
        expectedLongestTrain.add(new Domino(0,4));
        expectedLongestTrain.add(new Domino(4,10));
        expectedLongestTrain.add(new Domino(10,2));
        expectedLongestTrain.add(new Domino(2,5));
        expectedLongestTrain.add(new Domino(5,11));

        assertEquals(expectedLongestTrain, longestTrain);
    }

    @Test
    public void testFindLongestTrainCase2() {
        TrainBuilder trainBuilder = new TrainBuilder();
        List<Domino> userHand = new ArrayList<>();

        // Define user hand
        userHand.add(new Domino(12,8));
        userHand.add(new Domino(1,8));
        userHand.add(new Domino(3,1));
        userHand.add(new Domino(3,9));
        userHand.add(new Domino(9,10));
        userHand.add(new Domino(10,2));
        userHand.add(new Domino(10,4));
        userHand.add(new Domino(4,0));
        userHand.add(new Domino(0,6));
        userHand.add(new Domino(5,2));
        userHand.add(new Domino(11,5));
        userHand.add(new Domino(7,7));

        int startingDomino = 12;

        List<Domino> longestTrain = trainBuilder.findLongestTrain(userHand, startingDomino);

        // Define expected longest train
        List<Domino> expectedLongestTrain = new ArrayList<>();
        expectedLongestTrain.add(new Domino(12,8));
        expectedLongestTrain.add(new Domino(8,1));
        expectedLongestTrain.add(new Domino(1,3));
        expectedLongestTrain.add(new Domino(3,9));
        expectedLongestTrain.add(new Domino(9,10));
        expectedLongestTrain.add(new Domino(10,4));
        expectedLongestTrain.add(new Domino(4,0));
        expectedLongestTrain.add(new Domino(0,6));

        assertEquals(expectedLongestTrain, longestTrain);
    }

    @Test
    public void testFindLongestTrainCase3() {
        TrainBuilder trainBuilder = new TrainBuilder();
        List<Domino> userHand = new ArrayList<>();

        // Define user hand
        userHand.add(new Domino(4,8));
        userHand.add(new Domino(5,1));
        userHand.add(new Domino(3,1));
        userHand.add(new Domino(3,9));
        userHand.add(new Domino(7,10));
        userHand.add(new Domino(4,3));
        userHand.add(new Domino(3,10));
        userHand.add(new Domino(4,0));
        userHand.add(new Domino(0,5));
        userHand.add(new Domino(5,2));
        userHand.add(new Domino(11,12));
        userHand.add(new Domino(7,12));

        int startingDomino = 5;

        List<Domino> longestTrain = trainBuilder.findLongestTrain(userHand, startingDomino);

        // Define expected longest train
        List<Domino> expectedLongestTrain = new ArrayList<>();
        expectedLongestTrain.add(new Domino(0,5));
        expectedLongestTrain.add(new Domino(5,1));
        expectedLongestTrain.add(new Domino(1,3));
        expectedLongestTrain.add(new Domino(3,10));
        expectedLongestTrain.add(new Domino(10,7));
        expectedLongestTrain.add(new Domino(7,12));
        expectedLongestTrain.add(new Domino(12,11));

        assertEquals(expectedLongestTrain, longestTrain);
    }

    @Test
    public void testFindLongestTrainCase4() {
        TrainBuilder trainBuilder = new TrainBuilder();
        List<Domino> userHand = new ArrayList<>();

        // Define user hand
        userHand.add(new Domino(4,8));
        userHand.add(new Domino(5,1));
        userHand.add(new Domino(3,1));
        userHand.add(new Domino(3,9));
        userHand.add(new Domino(7,10));
        userHand.add(new Domino(4,3));
        userHand.add(new Domino(3,10));
        userHand.add(new Domino(4,7));
        userHand.add(new Domino(4,5));
        userHand.add(new Domino(5,2));
        userHand.add(new Domino(11,12));
        userHand.add(new Domino(7,12));

        int startingDomino = 0;

        List<Domino> longestTrain = trainBuilder.findLongestTrain(userHand, startingDomino);

        // Define expected longest train
        List<Domino> expectedLongestTrain = new ArrayList<>();

        assertEquals(expectedLongestTrain, longestTrain);
    }


}
