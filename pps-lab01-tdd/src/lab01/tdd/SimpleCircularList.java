package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleCircularList implements CircularList{

    private final List<Integer> list = new ArrayList<>();
    private int index = 0;

    private boolean checkEmptyList (List list){
        return list.isEmpty();
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
       return list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {

        checkEmptyList(list);
        Optional<Integer> element = Optional.of(list.get(this.index));
        this.index = this.index + 1;
        if(this.index == list.size()){
            this.index = 0;
        }
        return element;

    }

    @Override
    public Optional<Integer> previous() {

        checkEmptyList(list);
        if(this.index == 0){
            this.index = list.size()-1;
        }else{
            this.index = this.index - 1;
        }
        Optional<Integer> element = Optional.of(list.get(this.index));

        return element;
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {

        int end = index;
        Optional<Integer> element;

       do{
            element = next();
            if(element.isPresent() && strategy.apply(element.get())){
                return element;
            }
        } while(index != end);
        return Optional.empty();
    }
}
