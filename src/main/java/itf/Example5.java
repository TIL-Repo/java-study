package itf;

/*
* 표준 함수형 인터페이스
* - Runnable
* - Consumer (매개변수 O 반환 X), accept
*   - Consumer<T>
*   - BiConsumer<T, U>
*   - DoubleConsumer
*   - ObjDoubleConsumer<T>
* - Supplier (매개변수 X 반환 O), get, getAsDouble, getAsInt, ㆍㆍㆍ
*   - Supplier<T>
*   - DoubleSupplier
*   - IntSupplier
*   - BooleanSupplier
* - Function (매개변수 O 반환 O, 매핑 시에 사용), apply
*   - Function<T, R>
*   - BiFunction<T, U, R>
* - Predicate (매개변수 O 반환 O(Boolean)), test
*   - Predicate<T>
*   - BiPredicate<T, U>
* - Operator (매개변수 O 반환 O, 매핑보다는 연산 결과 반환에 사용), apply
*   - BinaryOperator<T>
*   - UnaryOperator<T>
* 실습 내용 이외에도 자료형 이름과 조합하여 다양한 메소드 존재
*/

import java.util.function.*;

public class Example5 {
    public static void main(String[] args) {
        /* Runnable */
        Runnable runnable = () -> System.out.println("Thread name : " + Thread.currentThread().getName());
        Thread thread = new Thread(runnable);
        thread.start();

        /* Consumer */
        Consumer<String> consumer = System.out::println;
        consumer.accept("하주현");
        BiConsumer<String, Integer> biConsumer = (s, i) -> System.out.println(s + i);
        biConsumer.accept("하주현", 26);
        DoubleConsumer doubleConsumer = d -> System.out.println(d);
        doubleConsumer.accept(20);
        ObjDoubleConsumer<String> objDoubleConsumer = (o, d) -> System.out.println(o + d);
        objDoubleConsumer.accept("하주현", 26L);

        /* Supplier */
        Supplier<Integer> supplier = () -> 1;
        System.out.println(supplier.get());
        DoubleSupplier doubleSupplier = () -> 2L;
        System.out.println(doubleSupplier.getAsDouble());
        IntSupplier intSupplier = () -> 3;
        System.out.println(intSupplier.getAsInt());
        BooleanSupplier  booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());

        /* Function */
        Person person = new Person("하주현", "010-1234-5678", "남", 26);
        Function<Person, PersonDto> function = Person::EntityToPersonDto;
        PersonDto personDto = function.apply(person);
        System.out.println(personDto.name + " " + personDto.phoneNumber + " " + personDto.sex);

        Address address = new Address("Korea", "Busan");
        BiFunction<Person, Address, PersonAddressDto> biFunction = (p, a) -> {
            return new PersonAddressDto(
                    p.getName(),
                    p.getPhoneNumber(),
                    p.getSex(),
                    a.getCountry(),
                    a.getCity()
            );
        };
        PersonAddressDto paDto = biFunction.apply(person, address);
        System.out.println(
                paDto.getName() + " " +
                paDto.getPhoneNumber() + " " +
                paDto.getSex() + " " +
                paDto.getCountry() + " " +
                paDto.getCity());

        /* Predicate */
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("있다!"));
        BiPredicate<String, String> biPredicate = String::equals;
        System.out.println(biPredicate.test("있다", ""));
        System.out.println(biPredicate.test("있다", "있다"));

        /* Operator */
        BinaryOperator<String> binaryOperator = (s1, s2) -> s1 + s2;
        System.out.println(binaryOperator.apply("아이유", " 예쁘다!"));
        UnaryOperator<String> unaryOperator = s1 -> s1 + " 귀엽다!";
        System.out.println(unaryOperator.apply("아이유"));
    }
}
