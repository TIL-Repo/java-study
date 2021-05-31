package optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
Optional 실습 참조 : https://www.baeldung.com/java-optional
Optional 올바르게 사용하는법 : https://dzone.com/articles/using-optional-correctly-is-not-optional
Optional 올바르게 사용하는법2 : http://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/
*/

class OptionalTest {

    @Test
    public void creation() throws Exception {
        //given
        String name = "JooHyun Ha";
        //when
        Optional<String> empty = Optional.empty();
        Optional<String> result = Optional.of(name);
        try {
            Optional<String> result2 = Optional.of(null);
            Assertions.fail("NullPointException");
        }catch (NullPointerException e){}
        Optional<String> result3 = Optional.ofNullable(null);
        //then
        Assertions.assertThat(empty.isPresent()).isFalse();
        Assertions.assertThat(result.isPresent()).isTrue();
        Assertions.assertThat(result3.isPresent()).isFalse();
    }

    @Test
    public void isPresent_isEmpty() throws Exception {
        //given
        Optional<String> result = Optional.of("JooHyun Ha");
        Optional<String> result2 = Optional.ofNullable(null);
        //when, then
        Assertions.assertThat(result.isPresent()).isTrue();
        Assertions.assertThat(result.isEmpty()).isFalse();
        Assertions.assertThat(result2.isPresent()).isFalse();
        Assertions.assertThat(result2.isEmpty()).isTrue();
    }

    @Test
    public void ifPresent() throws Exception {
        //given
        String name = null;
        Optional<String> result = Optional.ofNullable(name);
        /* problem
        * name에 null이 들어갈 수 있다는 사실을 까먹고 null을 체크해주지 않을 경우
        * 런타임 과정에서 NullPointException 오류가 발생한다.
        */
        if(name != null){
            System.out.println(name.length());
        }
        //when, then
        result.ifPresent(System.out::println);
    }

    @Test
    public void orElse() throws Exception {
        //given
        String name = null;
        //when
        String result = Optional.ofNullable(name).orElse("JooHyun Ha");
        //then
        Assertions.assertThat(result).isEqualTo("JooHyun Ha");
    }

    @Test
    public void orElseGet() throws Exception {
        //given
        String name = null;
        //when
        String result = Optional.ofNullable(name).orElseGet(() -> "JooHyun Ha");
        //then
        Assertions.assertThat(result).isEqualTo("JooHyun Ha");
    }

    /*
    * orElse, orElseGet의 차이점을 알고 있어야 하며 중요하다.
    * 잘못 사용시에 비즈니스 로직에 큰 문제를 일으킬 수 있다.
    * orElse : null 값이 아니라도 호출
    * orElseGet : null 값이 아니면 호출 X
    * 예) 해당 아이디가 존재하지 않으면 아이디 생성이라는 로직이 있을 경우 orElse로 하게 될 경우
    *     아이디가 존재하는데도 함수가 호출되어 오류를 발생시킬 수 있다.
    */

    String getDefaultValue(){
        String text = "발생하면 안됨";
        System.out.println("발생하면 안됨");
        return text;
    }

    @Test
    public void orElse_orElseGet() throws Exception {
        //given
        String name = "JooHyun Ha";
        //when, then
        System.out.print("result : ");
        String result = Optional.ofNullable(name).orElse(getDefaultValue());
        System.out.print("result2 : ");
        String result2 = Optional.ofNullable(name).orElseGet(this::getDefaultValue);
    }

    @Test
    public void orElseThrow() throws Exception {
        //given
        String name = null;
        //when
        try {
            String result = Optional.ofNullable(name).orElseThrow();
        }catch (NoSuchElementException e){}
        //then
    }

    @Test
    public void get() throws Exception {
        //given
        Optional<String> name = Optional.of("JooHyun Ha");
        Optional<String> name2 = Optional.ofNullable(null);
        //when
        String result = name.get();
        try {
            String result2 = name2.get();
        }catch (NoSuchElementException e) {}
        //then
    }

    @Test
    public void filter() throws Exception {
        //given
        Integer year = 2021;
        Optional<Integer> yearOptional = Optional.of(year);
        //when
        Optional<Integer> is2021 = yearOptional.filter(y -> y == 2021);
        Optional<Integer> is2022 = yearOptional.filter(y -> y == 2022);
        //then
        Assertions.assertThat(is2021.isPresent()).isTrue();
        Assertions.assertThat(is2022.isPresent()).isFalse();
    }

    public boolean priceIsInRange1(Juice juice){
        if (juice != null && juice.getPrice() != null &&
                (juice.getPrice() <= 5000 && juice.getPrice() > 1000)){
            return true;
        }
        return false;
    }

    public boolean priceIsInRange2(Juice juice){
        return Optional.ofNullable(juice)
                .map(Juice::getPrice)
                .filter(p -> p > 1000)
                .filter(p -> p <= 5000)
                .isPresent();
    }

    @Test
    public void filterFromFunction() throws Exception {
        //when, then
        Assertions.assertThat(priceIsInRange1(new Juice(5000))).isTrue();
        Assertions.assertThat(priceIsInRange1(new Juice(1000))).isFalse();
        Assertions.assertThat(priceIsInRange1(new Juice(null))).isFalse();
        Assertions.assertThat(priceIsInRange1(null)).isFalse();

        Assertions.assertThat(priceIsInRange2(new Juice(5000))).isTrue();
        Assertions.assertThat(priceIsInRange2(new Juice(1000))).isFalse();
        Assertions.assertThat(priceIsInRange2(new Juice(null))).isFalse();
        Assertions.assertThat(priceIsInRange2(null)).isFalse();
    }

    @Test
    public void map() throws Exception {
        //given
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "microsoft", "apple", "", ""
        );
        Optional<List<String>> listOptional = Optional.of(companyNames);
        Optional<String> name = Optional.of("JooHyun Ha" );
        Optional<String> password = Optional.of(" password ");
        //when
        Integer result = listOptional.map(List::size).get();
        Integer result2 = name.map(String::length).orElse(0);
        boolean result3 = password.map(String::trim).filter(p -> p.equals("password" )).isPresent();
        //then
        Assertions.assertThat(result).isEqualTo(6);
        Assertions.assertThat(result2).isEqualTo(10);
        Assertions.assertThat(result3).isTrue();
    }

    @Test
    public void flatMap() throws Exception {
        //given
        String name ="JooHyun Ha";
        Optional<String> optName = Optional.of(name);
        //when
        Optional<String> result = optName.map(String::toLowerCase);
        Optional<Object> result2 = optName.flatMap(n -> Optional.of(n.toLowerCase()));
    }


}