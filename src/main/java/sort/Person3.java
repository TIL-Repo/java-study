package sort;

import java.util.ArrayList;
import java.util.List;

public class Person3 implements Comparable<Person3>{

    String name;
    int age;
    List<String> hobby = new ArrayList<>();

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addHobby(List<String> hobby){
        this.hobby.addAll(hobby);
    }

    public List<String> getHobby() {
        return hobby;
    }

    @Override
    public int compareTo(Person3 o) {
        return this.name.compareTo(o.name);
    }
}
