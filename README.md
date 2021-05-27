# Java Study

## 공부 진척

- [x] [Stream](#Stream)
- [x] [Interface](#Interface)
- [ ] Comparable, Comparator 차이점
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
  - src.test.java.stream

Reference : https://stackify.com/streams-guide-java-8/

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
    
