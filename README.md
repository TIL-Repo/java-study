# Java Study

## 공부 진척

- [x] [Stream](#Stream)
- [x] [Interface](#Interface)
- [x] [Comparable Comparator](#Comparable-Comparator)
- [x] [Optional](#Optional)
- [ ] Generic Type
- [ ] Annotation

## 패키지 구조 & 약간 설명

### Stream

- StreamTest
  - Creation
    - Stream.of
    - Arrays.stream
    - Stream.Builder<T>
  - Operations
    - forEach
    - map && collect
    - filter
    - findFirst
    - toArray
    - flatMap
    - peek
  - Method Types and Pipelines
    - count && skip && limit
  - Lazy Evaluation
  - Comparison Based Stream Operations
    - sorted
    - min && max
    - distinct
    - allMatch && anyMatch && noneMatch
  - Stream Specialization
    - Creation
      - IntStream && mapToInt
    - Reduction Operations
      - reduce
    - Advanced collect
      - joining
      - toSet
      - toCollection
      - summarizingType
      - partitioningBy
      - groupingBy && mapping
    - Parallel Streams
    - Infinite Streams
      - generate && iterate
    - Java Streams Improvements In Java 9
      - takeWhile
      - dropWhile
    - etc
      - csv
      - stream.of Vs Arrays.stream

※ Employee : Stream 실습 더미 데이터

- 작업 디렉토리
  - src.main.java.stream
    - Employee
  - src.test.java.stream
    - StreamTest

Reference

- Stream 실습 참고 : [link](https://stackify.com/streams-guide-java-8/)

***

### Interface

- Example1.java
  - 인터페이스 선언
  - 인터페이스 구성요소
    - 상수 필드
    - 추상 메소드
    - 디폴트 메소드
    - 정적 메소드
  - 인터페이스 구현
  - 인터페이스 사용
- Example2.java
    - 인터페이스 다형성
- Example3.java
    - 인터페이스 상속
- Example4.java
    - 함수형 인터페이스
- Example5.java
  - 표준 함수형 인터페이스
    - Runnable
    - Consumer (매개변수 O, 반환 X), accept
      - Consumer<T>
      - BiConsumer<T, U>
      - DoubleConsumer
      - ObjDoubleConsumer<T>
    - Supplier (매개변수 X, 반환 O), get, getAsDouble, getAsInt, ㆍㆍㆍ
      - Supplier<T>
      - DoubleSupplier
      - IntSupplier
      - BooleanSupplier
    - Function (매개변수 O, 반환 O, 매핑 시에 사용), apply
      - Function<T, R>
      - BiFunction<T, U, R>
    - Predicate (매개변수 O, 반환 O(Boolean)), test
      - Predicate<T>
      - BiPredicate<T, U>
    - Operator (매개변수 O, 반환 O, 매핑보다는 연산 결과 반환에 사용), apply
      - BinaryOperator<T>
      - UnaryOperator<T>
              
※ Person, PersonDto, PersonAddressDto, Address : 표준 함수형 인터페이스 Function 실습 더미 데이터

- 작업 디렉토리
  - src.main.java.itf
    
***
    
### Comparable Comparator

- SortTest
  - Basic
    - sort_intArray
  - Comparable
    - comparable_classArray
    - comparable_classCollection
  - Comparator
    - comparator_name
    - comparator_hobbySize
    
※ NamedDesc, Person, Person2, Person3 : Comparable && Comparator 실습 더미 데이터

- 작업 디렉토리
  - src.test.java.sort
    - SortTest
  - src.main.java.sort
    - NamedDesc, Person, Person2, Person3

#### Comparable Comparator 의 차이점

- Comparator 는 함수형 인터페이스이지만 Comparable 은 그렇지 않다.
- Comparable 은 객체의 기본 정렬 방식을 결정하지만 Comparator 는 상황에 따라 정렬 방식을 유동적으로 변경할 수 있다.
- Comparable 의 정렬 기준은 컴파일 시점에 결정나지만 Comparator 는 런타임 중에 정렬 방식을 변경할 수 있다.
    
### Optional

- OptionalTest
  - creation
    - Optional.empty
    - Optional.of
    - Optional.ofNullable
  - isPresent_isEmpty
  - ifPresent
  - orElse
  - orElseGet
  - orElse vs orElseGet
  - orElseThrow
  - get
  - filter
  - map
  - floatMap
  
- 작업 디렉토리
  - src.test.java.optional
    - OptionalTest
  - src.main.java.optional
    - Juice
  
※ Juice : Filter 실습 더미 데이터

Reference

- Optional 실습 참고 : [link](https://www.baeldung.com/java-optional)
- Optional 올바르게 사용하는법 : [link](https://dzone.com/articles/using-optional-correctly-is-not-optional)
- Optional 올바르게 사용하는법2 : [link](http://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/)


