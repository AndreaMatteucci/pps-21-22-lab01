import lab01.tdd.CircularList;
import lab01.tdd.SimpleCircularList;
import lab01.tdd.StrategyFactory;
import lab01.tdd.StrategyFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;
    StrategyFactory strategyFactory;

    @BeforeEach
    void beforeEach(){
        circularList = new SimpleCircularList();
        strategyFactory = new StrategyFactoryImpl();
    }

    @Test
    void testIsEmpty(){
        assertEquals(true, circularList.isEmpty());
    }

    @Test
    void testAdd(){
        assertTrue(circularList.isEmpty());
        circularList.add(5);
        assertFalse(circularList.isEmpty());
    }

    @Test
    void testSize(){
        circularList.add(5);
        assertEquals(1, circularList.size());
    }

    @Test
    void testNext(){

        circularList.add(1);
        circularList.add(2);
        Optional<Integer> element = circularList.next();
        if(element.isPresent()){
            assertEquals(1, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void testCircularNext(){

        circularList.add(1);
        circularList.add(2);
        circularList.next();
        circularList.next();
        Optional<Integer> element = circularList.next();

        if(element.isPresent()){
            assertEquals(1, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void testPrevious(){

        circularList.add(1);
        circularList.add(2);
        circularList.next();
        Optional<Integer> element = circularList.previous();

        if(element.isPresent()){
            assertEquals(1, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void testCircularPrevious(){

        circularList.add(1);
        circularList.add(2);

        Optional<Integer> element = circularList.previous();
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void testNextAndPrevious(){

        circularList.add(1);
        circularList.add(2);
        circularList.add(3);

        circularList.next();
        circularList.next();
        Optional<Integer> element = circularList.previous();
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void testReset(){

        circularList.add(1);
        circularList.add(2);
        circularList.add(3);

        circularList.next();
        circularList.next();
        circularList.reset();
        Optional<Integer> element = circularList.next();
        if(element.isPresent()){
            assertEquals(1, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextEvenStrategy(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);
        Optional<Integer> element = circularList.next(strategyFactory.createEvenStrategy());
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextEvenStrategyWithCircular(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(5);
        circularList.next();
        circularList.next();
        Optional<Integer> element = circularList.next(strategyFactory.createEvenStrategy());
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextMultipleOf(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(6);
        circularList.add(5);
        Optional<Integer> element = circularList.next(strategyFactory.createMultipleOfStrategy(3));
        if(element.isPresent()){
            assertEquals(6, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextMultipleOfCircular(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(6);
        circularList.add(5);
        circularList.next();
        circularList.next();
        Optional<Integer> element = circularList.next(strategyFactory.createMultipleOfStrategy(3));
        if(element.isPresent()){
            assertEquals(6, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextEqualsStrategy(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);
        Optional<Integer> element = circularList.next(strategyFactory.createEqualsStrategy(2));
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }

    @Test
    void TestNextEqualsWithCircular(){
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(5);
        circularList.next();
        circularList.next();
        Optional<Integer> element = circularList.next(strategyFactory.createEqualsStrategy(2));
        if(element.isPresent()){
            assertEquals(2, element.get());
        }else{
            fail("Element isn't present");
        }
    }


}
