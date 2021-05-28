package sort;

public class Person implements Comparable<Person>{

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
//        return this.age - o.age; // 오름차순
        return o.age - this.age; // 내림차순
    }
}
