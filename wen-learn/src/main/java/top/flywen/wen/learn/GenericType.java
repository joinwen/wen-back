package top.flywen.wen.learn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericType {
    public static void main(String[] args) {

    }
}
class LearnAny {
    private List<Object> learned;
    public void learn(Object object) {
        if(this.learned == null)
            this.learned = new ArrayList<>();
        this.learned.add(object);
    }
}
class Some<T> {
    private T name;
    public Some(T name) {
        this.name = name;
    }
    public void add(T name) {
        System.out.println(name);
    }
}
class LearnOne {
    private LinkedList<?> learned;
    public void learn(String object) {
        if(this.learned == null)
            this.learned = new LinkedList<String>();
        Some<?> some = new Some<String>("abc");
    }
}
class Math {
    private String name;
    private Integer level;

    public Math(String name, Integer level) {
        this.name = name;
        this.level = level;
    }
}
class LearnMath {
    private List<Math> learned;
    public void learn(Math math) {
        if(this.learned == null)
            this.learned = new ArrayList<>();
        this.learned.add(math);
    }
}
