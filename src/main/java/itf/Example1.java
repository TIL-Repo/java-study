package itf;

/*
* 인터페이스 선언
* 인터페이스 구성요소
* 인터페이스 구현
* 인터페이스 사용
*/

interface Animal {
    public abstract void cry();
}

/* 인터페이스 구성 요소 4가지
* 상수 필드
* 추상 메소드
* 디폴트 메소드
* 정적 메소드
*/

interface Pet {

    /* public static final 생략 가능 */
    public static final Integer ball = 2;

    /* public abstarct 생략 가능 */
    public abstract void play();

    public default void throwBall(){
        System.out.println("볼을 던지다.");
    }

    public static void pickUpBall(){
        System.out.println("볼을 줍다.");
    }
}

/* 인터페이스는 상속과 다르게 다중 상속이 가능하다. */
class Cat implements Animal, Pet {

    @Override
    public void cry() {
        System.out.println("냐옹");
    }

    @Override
    public void play() {
        System.out.println("냐옹아 놀자");
    }
}

class Dog implements Animal, Pet {

    @Override
    public void cry() {
        System.out.println("멍멍");
    }

    @Override
    public void play() {
        System.out.println("멍멍아 놀자");
    }
}

public class Example1 {
    public static void main(String[] args) {

        Cat cat = new Cat();
        Dog dog = new Dog();

        cat.cry();
        cat.play();
        cat.throwBall();
        dog.cry();
        dog.play();
        dog.throwBall();
        Pet.pickUpBall();

        /* 익명 구현 객체를 이용한 인터페이스 구현 방법 */
        Animal animal = new Animal(){
            @Override
            public void cry() {
                System.out.println("냐옹2");
            }
        };

        animal.cry();

    }
}
