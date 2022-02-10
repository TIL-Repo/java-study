package first_class_collection;

import java.util.Map;

/*
* - 일급 컬렉션이란 컬렉션을 랩핑한 클래스를 말하며, 컬렉션을 제외한 다른 멤버 변수가 없는 상태를 말한다.
* - 이를 통해서 얻을 수 있는 이점
*   - 비즈니스에 종속적인 자료구조
*       - 외부가 아닌 해당 컬렉션을 관리하는 클래스를 따로 만들어서 해당 클래스는 클래스 내부적으로 비즈니스 로직을 검증하거나 처리하여 관리할 수 있다.
*   - 컬렉션의 불변성을 보장
*       - Java의 final은 불변성 보장이 아니라 재할당을 금지하는 것이기에 값을 추가하는 것을 막을 수 없다.
*       - 컬렉션을 관리하는 클래스에 메소드가 존재하지 않으면 값을 넣을 수 없기에 불변한 컬렉션이 된다.
*   - 상태와 행위를 한 곳에서 관리
*       - 해당 컬렉션에 대한 상태와 행위를 클래스 안에 정의함으로써 일급 컬렉션 사용 시 메소드 중복일 수 있다.
*       - 또한 하려는 행위가 뚜렷하고 가독성이 높아지게 된다.
*   - 이름이 있는 컬렉션
*       - 컬렉션을 사용하지 않는다면 변수의 이름으로만 구분해야 하고 개발자마다 다르게 해석할 가능성이 있다.
*       - 일급컬렉션으로 이름을 붙인다면 명확하게 구분할 수 있다.
* */
public class FirstClassCollection {
    private Map<String, Integer> collection;

    public FirstClassCollection(Map<String, Integer> collection) {
        this.collection = collection;
    }

    public void addElement(String key, int value) {
        collection.put(key, value);
    }

    public int getSumByVisitors() {
        return collection.values().stream().reduce(0, Integer::sum);
    }
}
