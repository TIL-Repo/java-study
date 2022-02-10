package first_class_collection;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FirstClassCollectionTest {

    @Test
    public void 불변성(){
        //given
        Map<String, Integer> collection = new HashMap<>();
        FirstClassCollection firstClassCollection = new FirstClassCollection(new HashMap<>());
        //when
        collection.put("1", 5);
        /* addElement 같은 메소드가 없다면 불변한 상태가 된다. */
        firstClassCollection.addElement("1", 5);
    }

    @Test
    public void 캡슐화(){
        //given
        Map<String, Integer> collection = new HashMap<>();
        collection.put("blog1", 1);
        collection.put("blog1", 3);
        collection.put("blog1", 5);
        collection.put("blog1", 7);
        FirstClassCollection firstClassCollection = new FirstClassCollection(collection);
        //when
        int sumValue = collection.values().stream().reduce(0, (x, y) -> x + y).intValue();
        /* 로직이 캡슐화 되어 있어 보기 편하며 간단하다. */
        firstClassCollection.getSumByVisitors();
    }

    @Test
    public void 이름을_가지는_컬렉션(){
        //when
//        Map<String, Integer> naverBlog = new HashMap<>();
//        Map<String, Integer> daumBlog = new HashMap<>();
        NaverBlog naverBlog = new NaverBlog(new HashMap<>());
        DaumBlog daumBlog = new DaumBlog(new HashMap<>());
    }

}