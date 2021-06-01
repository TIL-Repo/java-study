package generic;

/* 멀티 타입 파라미터 & 제네릭 메소드
* public <타입파라미터, ...> 리턴타입 메소드명(매개변수, ...) {...}
*/

class Product<T, M> {
    private T kind;
    private M model;
    static Integer a;
    // static T b; // static을 제네릭 타입이 확정되기 전에 메모리에 올라가기 때문에 멤버변수에 제네릭 타입 선언을 불가능

    Product(T kind, M model) {
        this.kind = kind;
        this.model = model;
    }

    public T getKind() { return kind; }
    public M getModel() { return model; }
}

class Car {}
class Tv {}

class UtilMethod {
    public static <T> BoxGeneric<T> boxing(T t){
        BoxGeneric<T> box = new BoxGeneric<>();
        box.set(t);
        return box;
    }
}

public class Example2 {

    public static void main(String[] args) {
        /* Multi Type Parameter */
        Product<Tv, String> product1 = new Product<>(new Tv(), "스마트TV");
        Tv tvkind = product1.getKind();
        String tvModel = product1.getModel();

        Product<Car, String> product2 = new Product<>(new Car(), "디젤");
        Car carKind = product2.getKind();
        String carModel = product2.getModel();

        /* Generic Method */
        BoxGeneric<Integer> boxing1 = UtilMethod.boxing(100);
        Integer integer = boxing1.get();
        BoxGeneric<String> boxing2 = UtilMethod.<String>boxing("Hello World!");
        String s = boxing2.get();
    }
}
