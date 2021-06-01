package generic;

/* 제한된 타입 파라미터
* public <T extends 상위타입> 리턴타입 메소드(매개변수, ...) {...}
* - T는 상위타입이거나 상위타입의 자식 클래스만 올 수 있다.
* - 구현할 때 T는 상위타입의 메소드만 올 수 있고 자식 클래스의 메소드는 사용이 불가능하다.
*/

class UtilLimit {
    public static <T extends Number> int compare(T t1, T t2) {
        double v1 = t1.doubleValue();
        double v2 = t2.doubleValue();
        return Double.compare(v1, v2);
    }
}

public class Example3 {
    public static void main(String[] args) {
        int result1 = UtilLimit.compare(87.4, 20);
        System.out.println("result1 = " + result1);
    }
}
