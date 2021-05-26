package stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    /* Java Stream Specializations */

    @Test
    public void intStream_mapToInt() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        int result = employees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(NoSuchElementException::new);
        IntStream result2 = IntStream.of(1, 2, 3);
        IntStream result3 = IntStream.range(10, 20);
        //then
        Assertions.assertThat(result).isEqualTo(300000);
        Assertions.assertThat(result2.max().orElseThrow()).isEqualTo(3);
        Assertions.assertThat(result3.max().orElseThrow()).isEqualTo(19);
    }

    @Test
    public void reduce() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Integer result = employees.stream()
                .map(Employee::getSalary)
                .reduce(Integer::sum)
                .orElseThrow();
        Integer result2 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Integer::min)
                .orElseThrow();
        //then
        Assertions.assertThat(result).isEqualTo(600000);
        Assertions.assertThat(result2).isEqualTo(100000);
    }

    /* Advanced collect */

    @Test
    public void joining() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        String result = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        //then
        Assertions.assertThat(result)
                .isEqualTo("Jeff Bezos, Bill Gates, Mark Zuckerberg");
    }

    @Test
    public void toSet() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Set<String> result = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        //then
        Assertions.assertThat(result)
                .hasSize(3);
    }

    @Test
    public void toCollection() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Vector<String> result = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(Vector::new));
        ArrayList<String> result2 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(ArrayList::new));
        //then
        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result2).hasSize(3);
    }

    @Test
    public void summarizingType() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        IntSummaryStatistics result = employees.stream()
                .collect(Collectors.summarizingInt(Employee::getSalary));
        //then
        Assertions.assertThat(result.getCount()).isEqualTo(3);
        Assertions.assertThat(result.getSum()).isEqualTo(600000);
        Assertions.assertThat(result.getMax()).isEqualTo(300000);
        Assertions.assertThat(result.getMin()).isEqualTo(100000);
        Assertions.assertThat(result.getAverage()).isEqualTo(200000);
    }

    @Test
    public void partitioningBy() throws Exception {
        //given
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        //when
        Map<Boolean, List<Integer>> result = intList.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        //then
        Assertions.assertThat(result.get(true)).hasSize(4);
        Assertions.assertThat(result.get(false)).hasSize(1);
    }

    @Test
    public void groupingBy_mapping() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when
        Map<Character, List<Employee>> result = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0)));

        Map<Character, List<Integer>> result2 = employees.stream()
                .collect(
                        Collectors.groupingBy(e -> e.getName().charAt(0),
                                Collectors.mapping(Employee::getSalary, Collectors.toList()))
                );
        //then
        Assertions.assertThat(result.get('B').get(0).getName()).isEqualTo("Bill Gates");
        Assertions.assertThat(result.get('J').get(0).getName()).isEqualTo("Jeff Bezos");
        Assertions.assertThat(result.get('M').get(0).getName()).isEqualTo("Mark Zuckerberg");

        Assertions.assertThat(result2.get('B').get(0)).isEqualTo(200000);
        Assertions.assertThat(result2.get('J').get(0)).isEqualTo(100000);
        Assertions.assertThat(result2.get('M').get(0)).isEqualTo(300000);
    }

    /* Parallel Streams */
    /* 주의사항
    * 1. 연산 순서가 중요하다면 사용해선 안됨
    * 2. 자원이 쓰레드로부터 안전한지 체크해야 한다.
    */

    @Test
    public void parallel() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Jeff Bezos", 100000),
                new Employee(2L, "Bill Gates", 200000),
                new Employee(3L, "Mark Zuckerberg", 300000)
        );
        //when, then
        employees.stream()
                .peek(e -> e.salaryIncrement(10))
                .forEach(e -> System.out.println(e.getName() + "가 이자 획득"));

        employees.stream().parallel()
                .peek(e -> e.salaryIncrement(10))
                .forEach(e -> System.out.println(e.getName() + "가 이자 획득"));
    }

    /* Infinite Streams */

    @Test
    public void generate_iterate() throws Exception {
        //when, then
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        List<Integer> result = Stream.iterate(2, i -> i * 2)
                .limit(5)
                .collect(Collectors.toList());

        Assertions.assertThat(result).containsOnly(2, 4, 8, 16, 32);
    }

    /* Java Stream Improvements In Java 9 */

    /* 참으로 시작해서 거짓으로 끝날 때까지 */
    @Test
    public void takeWhile() throws Exception {
        //when, then
        Stream.iterate(1, i -> i + 1)
//                .limit(10)
                .takeWhile(n -> n <= 10)
                .map(x -> x * x)
                .forEach(System.out::println);

        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .takeWhile(x -> x <= 5)
                .forEach(System.out::println);

        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .filter(x -> x <= 5)
                .forEach(System.out::println);
    }

    /* element 가 거짓이 되는 시점부터 */
    @Test
    public void dropWhile() throws Exception {
        //when, then
        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .dropWhile(x -> x <= 5)
                .forEach(System.out::println);

        System.out.println(" -- ");

        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .dropWhile(x -> x <= 8)
                .forEach(System.out::println);
    }

    /* String practice */
    @Test
    public void csv() throws Exception {
        //given
        List<String> dummy = Arrays.asList(
                "축구:야구:축구:배구:볼링:족구:배드민턴:배드민턴:배드민턴:족구",
                "축구:야구:축구:배구:볼링:족구",
                "배드민턴:배드민턴:배드민턴:족구"
        );
        Map<String, Integer> hobby = new HashMap();
        //when
        dummy.stream()
                .flatMap(x -> Stream.of(x.split(":")))
                .forEach(x -> hobby.merge(x, 1, (o, n) -> ++o));
        //then
        hobby.entrySet()
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    /* Stream.of VS Arrays.stream */
    @Test
    public void streamOf_arraysStream() throws Exception {
        //given
        int[] dummy = {1,2,3,4,5,6};
        //when, then
        Stream<int[]> result = Stream.of(dummy);
        IntStream result2 = Arrays.stream(dummy);
    }
}