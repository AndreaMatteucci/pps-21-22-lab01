package lab01.tdd;

public interface StrategyFactory {

    SelectStrategy createEvenStrategy();
    SelectStrategy createMultipleOfStrategy(int element);
    SelectStrategy createEqualsStrategy(int element);
}


