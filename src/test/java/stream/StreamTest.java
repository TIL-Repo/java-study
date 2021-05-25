package stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamTest {

    @Test
    public void creation() throws Exception {
        //given
        Employee[] arrayOfEmps = {
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        };
        /* Stream.of(Arrays or elements) */
        Stream.of(arrayOfEmps);
        Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);

        /* stream() : Java8부터 추가된 Collection interface 내장 함수 */
        Arrays.asList(arrayOfEmps).stream();

        /* Stream.builder() */
        Stream.Builder<Employee> empStreamBuilder = Stream.builder();

        empStreamBuilder.accept(arrayOfEmps[0]);
        empStreamBuilder.accept(arrayOfEmps[1]);
        empStreamBuilder.accept(arrayOfEmps[2]);

        Stream<Employee> empStream = empStreamBuilder.build();
    }

    @Test
    public void forEach() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        employees.stream().forEach(
                e -> e.salaryIncrement(10)
        );
        //then
        Assertions.assertThat(employees)
                .extracting("salary", Integer.class)
                .containsOnly(110000, 220000, 330000);
    }

    @Test
    public void map_collect() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        String[] engList = {"a", "b", "c"};
        //when
        List<Long> result = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        List<String> result2 = Stream.of(engList)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //then
        Assertions.assertThat(result)
                .containsOnly(1L, 2L, 3L);
        Assertions.assertThat(result2)
                .containsOnly("A", "B", "C");
    }

    @Test
    public void filter() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        List<Long> result = employees.stream()
                .map(Employee::getId)
                .filter(e -> e < 3)
                .collect(Collectors.toList());
        List<Long> result2 = employees.stream()
                .filter(e -> e.getId() < 2)
                .filter(e -> e.getSalary() < 200000)
                .map(Employee::getId)
                .collect(Collectors.toList());
        //then
        Assertions.assertThat(result)
                .hasSize(2);
        Assertions.assertThat(result2)
                .hasSize(1);
    }

    @Test
    public void findFirst() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Employee result = employees.stream()
                .filter(e -> e.getSalary() < 200000)
                .findFirst()
                .orElse(null);
        Employee result2 = employees.stream()
                .filter(e -> e.getSalary() < 100000)
                .findFirst()
                .orElse(null);
        //then
        Assertions.assertThat(result)
                .isNotNull();
        Assertions.assertThat(result2)
                .isNull();
    }

    @Test
    public void toArray() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Employee[] result = employees.stream().toArray(Employee[]::new);
        Long[] result2 = employees.stream()
                .map(Employee::getId)
                .toArray(Long[]::new);
        //then
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(employees.toArray());
        Assertions.assertThat(result2)
                .containsOnly(1L, 2L, 3L);
    }

    @Test
    public void flatMap() throws Exception {
        //given
        List<List<String>> number = Arrays.asList(
                Arrays.asList("1", "2", "3"),
                Arrays.asList("4", "5", "6"),
                Arrays.asList("7", "8", "9")
        );
        List<List<List<String>>> number2 = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("1", "2", "3"),
                        Arrays.asList("4", "5", "6"),
                        Arrays.asList("7", "8", "9")
                ),
                Arrays.asList(
                        Arrays.asList("1", "2", "3"),
                        Arrays.asList("4", "5", "6"),
                        Arrays.asList("7", "8", "9")
                )
        );
        //when
        List<String> result = number.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<String> result2 = number2.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        //then
        Assertions.assertThat(number.size() * 3)
                .isEqualTo(result.size());
        Assertions.assertThat(number2.size() * 3 * 3)
                .isEqualTo(result2.size());

    }

    @Test
    public void peek() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when, then
        employees.stream()
                .peek(e -> System.out.println(e.getSalary()))
                .peek(e -> e.salaryIncrement(10))
                .forEach(e -> System.out.println(e.getSalary()));
    }

    @Test
    public void count_skip_limit() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        long result = employees.stream()
                .filter(e -> e.getSalary() < 200000)
                .count();
        Stream<Integer> result2 = Stream.iterate(0, i -> i + 2)
                .limit(5);
        Stream<Integer> result3 = Stream.iterate(0, i -> i + 2)
                .skip(3)
                .limit(5);
        //then
        Assertions.assertThat(result).isEqualTo(1);
        Assertions.assertThat(result2)
                .containsOnly(0, 2, 4, 6, 8);
        Assertions.assertThat(result3)
                .containsOnly(6, 8 ,10 ,12, 14);
    }

    /* 필요한 연산만 할 수 있게 지원 */
    @Test
    public void LazyEvaluation() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000),
                new Employee(4L, "Elon Musk", 400000)
        );
        //when, then
        employees.stream()
                .peek(e -> System.out.println(e.getName() + "분이 인출 시도"))
                .filter(e -> e.getSalary() > 200000)
                .forEach(e -> System.out.println(e.getName() + "만 인출 가능"));
    }

    @Test
    public void sorted() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        List<Employee> result = employees.stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList());
        //then
        Assertions.assertThat(result)
                .extracting("name")
                .usingRecursiveComparison()
                .isEqualTo(Arrays.asList("Bill Gates", "Jeff Bezos", "Mark Zuckerberg"));
    }

    @Test
    public void min_max() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Employee result = employees.stream()
                .min((e1, e2) -> (int) (e1.getId() - e2.getId()))
                .orElseThrow(NoSuchElementException::new);
        Employee result2 = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        //then
        Assertions.assertThat(result.getId()).isEqualTo(1);
        Assertions.assertThat(result2.getSalary()).isEqualTo(300000);
    }

    @Test
    public void distinct() throws Exception {
        //given
        List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3, 3);
        //when
        List<Integer> result = intList.stream().distinct().collect(Collectors.toList());
        //then
        Assertions.assertThat(result)
                .containsOnly(2, 3, 4, 5)
                .hasSize(4);
    }

    @Test
    public void allMatch_anyMatch_noneMatch() throws Exception {
        //given
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        //when
        boolean result = intList.stream().allMatch(i -> i % 2 == 0);
        boolean result2 = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean result3 = intList.stream().noneMatch(i -> i % 3 == 0);
        boolean result4 = intList.stream()
                .filter(i -> i % 3 != 0)
                .noneMatch(i -> i % 3 == 0);
        //then
        Assertions.assertThat(result).isEqualTo(false);
        Assertions.assertThat(result2).isEqualTo(true);
        Assertions.assertThat(result3).isEqualTo(false);
        Assertions.assertThat(result4).isEqualTo(true);
    }

}