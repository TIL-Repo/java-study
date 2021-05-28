package sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    public void sort_intArray() throws Exception {
        //given
        Integer[] numbers = {-5, 1, 5, 9, 7, -8, 3};
        Integer[] expectedAsc = {-8, -5, 1, 3, 5, 7, 9};
        Integer[] expectedDesc = {9, 7, 5, 3, 1, -5, -8};
        //when, then
        Arrays.sort(numbers);
        Assertions.assertThat(numbers).isEqualTo(expectedAsc);

        Arrays.sort(numbers, Collections.reverseOrder());
        Assertions.assertThat(numbers).isEqualTo(expectedDesc);

        Assertions.assertThat(Arrays.stream(numbers).sorted().toArray(Integer[]::new))
                .isEqualTo(expectedAsc);

        Assertions.assertThat(Arrays.stream(numbers).sorted(Collections.reverseOrder()).toArray(Integer[]::new))
                .isEqualTo(expectedDesc);
    }

    @Test
    public void comparable_classArray() throws Exception {
        //given
        Person person1 = new Person("홍길동4", 75);
        Person person2 = new Person("홍길동2", 42);
        Person person3 = new Person("홍길동3", 65);
        Person person4 = new Person("홍길동1", 23);
        Person person5 = new Person("홍길동7", 17);
        Person[] persons = {person1, person2, person3, person4, person5};
        Person[] expectedAsc = {person5, person4, person2, person3, person1};
        Person[] expectedDesc = {person1, person3, person2, person4, person5};
        //when
        Arrays.sort(persons); // Comparable 구현 필요
        //then
//        Assertions.assertThat(persons).isEqualTo(expectedAsc);
        Assertions.assertThat(persons).isEqualTo(expectedDesc);
    }

    @Test
    public void comparable_classCollection() throws Exception {
        //given
        Person person1 = new Person("홍길동4", 75);
        Person person2 = new Person("홍길동2", 42);
        Person person3 = new Person("홍길동3", 65);
        Person person4 = new Person("홍길동1", 23);
        Person person5 = new Person("홍길동7", 17);
        Person2 person11 = new Person2("홍길동4", 75);
        Person2 person22 = new Person2("홍길동2", 42);
        Person2 person33 = new Person2("홍길동3", 65);
        Person2 person44 = new Person2("홍길동1", 23);
        Person2 person55 = new Person2("홍길동7", 17);
        List<Person> peoples = Arrays.asList(
                person1, person2, person3, person4, person5
        );
        List<Person2> peoples2 = Arrays.asList(
                person11, person22, person33, person44, person55
        );
        //when
        Collections.sort(peoples); // Comparable 구현 필요
        Collections.sort(peoples2);
        //then
        Assertions.assertThat(peoples)
                .isEqualTo(Arrays.asList(
                        person1, person3, person2, person4, person5
                ));
        Assertions.assertThat(peoples2)
                .isEqualTo(Arrays.asList(
                        person44, person22, person33, person11, person55
                ));
    }

    @Test
    public void comparator_name() throws Exception {
        Person person1 = new Person("홍길동4", 75);
        Person person2 = new Person("홍길동2", 42);
        Person person3 = new Person("홍길동3", 65);
        Person person4 = new Person("홍길동1", 23);
        Person person5 = new Person("홍길동7", 17);
        List<Person> peoples = Arrays.asList(
                person1, person2, person3, person4, person5
        );
        //when, then
//        peoples.sort(new NamedDesc()); 아래와 같은 동작
        Collections.sort(peoples, new NamedDesc());
        Assertions.assertThat(peoples)
                .isEqualTo(Arrays.asList(
                        person4, person2, person3, person1, person5
                ));
        Collections.sort(peoples, (p1, p2) -> p1.name.compareTo(p2.name) * -1);
        Assertions.assertThat(peoples)
                .isEqualTo(Arrays.asList(
                        person5, person1, person3, person2, person4
                ));
    }

    @Test
    public void comparator_hobbySize() throws Exception {
        //given
        Person3 person1 = new Person3("홍길동4", 75);
        Person3 person2 = new Person3("홍길동2", 42);
        Person3 person3 = new Person3("홍길동3", 65);
        person1.addHobby(Arrays.asList("농구", "축구", "야구", "배드민턴"));
        person2.addHobby(Arrays.asList("농구", "배드민턴"));
        person3.addHobby(Arrays.asList("농구", "축구", "야구"));
        List<Person3> persons = Arrays.asList(
                person1, person2, person3
        );
        //when
        Collections.sort(persons, (p1, p2) -> p1.getHobby().size() - p2.getHobby().size());
        //then
        Assertions.assertThat(persons)
                .isEqualTo(Arrays.asList(
                        person2, person3, person1
                ));
    }

}