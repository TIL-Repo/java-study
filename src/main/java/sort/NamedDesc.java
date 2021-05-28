package sort;

import java.util.Comparator;

public class NamedDesc implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.name.compareTo(p2.name); // 오름차순
//        return p1.name.compareTo(p2.name) * -1; // 내림차순
//        return p2.name.compareTo(p1.name); // 내림차순
    }
}
