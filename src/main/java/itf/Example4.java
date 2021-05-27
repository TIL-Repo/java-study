package itf;

/*
* 함수형 인터페이스
*/

@FunctionalInterface
interface InterfaceTest {
    public abstract int operation(int x, int y);
}

public class Example4 {
    public static void main(String[] args) {
        InterfaceTest interfaceTest;

        interfaceTest = (x, y) -> x + y;
        System.out.println(interfaceTest.operation(3, 5));

        interfaceTest = (x, y) -> x - y;
        System.out.println(interfaceTest.operation(5, 4));
    }
}
