package sort;

public class Person2 implements Comparable<Person2>{

    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person2 o) {
        return this.name.compareTo(o.name);
    }
}
