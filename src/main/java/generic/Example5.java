package generic;

/* 제네릭 타입의 상속과 구현
* public class ChildProduct<T, M> extends Product<T, M> {...}
* - 타입 파라미터는 자식 클래스에도 기술해야 한다.
* public class ChildProduct<T, M, C> extends Product<T, M> {...}
* - 추가적인 타입 파라미터를 가질 수 있다.
* public class StorageImpl<T> implements Storage<T> {...}
* - 제네릭 인터페이스 또한 구현 클래스에도 타입 파라미터를 기술해야 한다.
*/

class ChildProduct<K, V> extends Product<K, V> {

    ChildProduct(K kind, V model) {
        super(kind, model);
    }
}

class ChildProduct2<K, V, C> extends Product<K, V> {

    private C company;

    public ChildProduct2(K kind, V model, C company) {
        super(kind, model);
        this.company = company;
    }
}

interface Storage<T> {
    public void add(T item, int idx);
    public T get(int index);
}

class StorageImpl<T> implements Storage<T> {

    private T[] array;

    public StorageImpl(int capacity) {
        this.array = (T[])(new Object[capacity]);
    }

    @Override
    public void add(T item, int idx) {
        array[idx] = item;
    }

    @Override
    public T get(int index) {
        return array[index];
    }
}

public class Example5 {

    public static void main(String[] args) {
        ChildProduct2<Tv, String, String> product = new ChildProduct2<>(new Tv(), "SmartTV", "Samsung");

        Storage<Tv> storage = new StorageImpl<>(100);
        storage.add(new Tv(), 0);
        Tv tv = storage.get(0);
    }
}
