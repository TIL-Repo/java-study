package generic;

/* 와일드카드 타입
* 이미 선언되어진 제네릭 타입을 매개변수나 리턴타입으로 사용할 때 타입 파라미터를 제한할 목적
* - 제네릭타입<?>
*   - 타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.
* - 제네릭타입<? extends 상위타입>
*   - 타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입만 올 수 있다.
* - 제네릭타입<? super 하위타입>
*   - 타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위 타입이 올 수 있다.
*/

import java.util.Arrays;

class Course<T> {
    private String name;
    private T[] students;

    public Course(String name, int capacity) {
        this.name = name;
        this.students = (T[]) new Object[capacity];
    }

    public String getName() {
        return name;
    }
    public T[] getStudents() {
        return students;
    }

    public void add(T t){
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null){
                students[i] = t;
                break;
            }
        }
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Student extends Person {
    public Student(String name) {
        super(name);
    }
}

class Worker extends Person {
    public Worker(String name) {
        super(name);
    }
}

class HighStudent extends Student {
    public HighStudent(String name) {
        super(name);
    }
}

public class Example4 {
    public static void registerCourse(Course<?> course){
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }
    public static void registerCourseStudent(Course<? extends Student> course){
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }
    public static void registerCourseWorker(Course<? super Worker> course){
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void main(String[] args) {
        registerCourse(new Course<Person>("일반인 과정", 5));
        registerCourse(new Course<Student>("학생 과정", 5));
        registerCourse(new Course<Worker>("근로자 과정", 5));
        registerCourse(new Course<HighStudent>("고등학생생 과정", 5));

//        registerCourseStudent(new Course<Person>("일반인 과정", 5));
        registerCourseStudent(new Course<Student>("학생 과정", 5));
//        registerCourseStudent(new Course<Worker>("근로자 과정", 5));
        registerCourseStudent(new Course<HighStudent>("고등학생생 과정", 5));

        registerCourseWorker(new Course<Person>("일반인 과정", 5));
//        registerCourseWorker(new Course<Student>("학생 과정", 5));
        registerCourseWorker(new Course<Worker>("근로자 과정", 5));
//        registerCourseWorker(new Course<HighStudent>("고등학생생 과정", 5));

        Course<Person> personCourse = new Course<>("일반인 과정", 5);
        personCourse.add(new Person("일반인"));
        personCourse.add(new Person("직장인"));
        personCourse.add(new Person("학생"));
        personCourse.add(new Person("고등학생"));

        Course<Worker> workerCourse = new Course<>("직장인 과정", 5);
        workerCourse.add(new Worker("직장인"));

        Course<Student> studentCourse = new Course<>("학생 과정", 5);
        studentCourse.add(new Student("학생"));
        studentCourse.add(new HighStudent("고등학생"));

        Course<HighStudent> highStudentCourse = new Course<>("고등학생 과정", 5);
//        highStudentCourse.add(new Student("학생"));
        highStudentCourse.add(new HighStudent("고등학생"));

        registerCourse(personCourse);
        registerCourse(workerCourse);
        registerCourse(studentCourse);
        registerCourse(highStudentCourse);

    }
}
