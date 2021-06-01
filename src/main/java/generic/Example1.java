package generic;

/* Generic 사용 이유
* 1. 강력한 타입 체크
*   - 사전에 컴파일 과정에서 에러 방지
* 2. 불 필요한 타입 캐스팅 제거
* public class 클래스명<T> {...}
* public interface 인터페이스명<T> {...}
*/

class Box {
    private Object object;
    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}

class BoxGeneric<T> {
    private T t;
    public T get() { return t; }
    public void set(T t){ this.t = t; }
}

public class Example1 {

    public static void main(String[] args) {

        Box box = new Box();
        box.set("Hello World!");
        String boxTxt = (String)box.get(); // Type cast 필요
        box.set(3);
        Integer boxNum = (Integer) box.get();

        BoxGeneric<String> boxG = new BoxGeneric<>();
        boxG.set("Hello Generic!");
        // boxG.set(2); 내부에 구체적인 타입이 결정되었기 때문에 다른 타입은 에러 발생
        String boxGTxt = boxG.get();

        BoxGeneric<Integer> boxG2 = new BoxGeneric<>();
        boxG2.set(2);
        Integer boxGNum = boxG2.get();
    }
}
