import java.util.*;

abstract class Fruit {
    public String getColor() {
    	return "super class color";
    }
}

class Banana extends Fruit {
    
    public String getColor() {
        return "yellow";
    }
}

class Apple extends Fruit {
    
    public String getColor() {
        return "green";
    }
}

public class Inheritance {

    public static void main(String[] args) {
        List<Fruit> list = new ArrayList<Fruit>();
        list.add(new Banana());
        list.add(new Apple());

        for (Fruit fruit : list) {
            System.out.println(fruit.getColor());
        }
    }
}