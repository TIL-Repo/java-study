package itf;

public class Person {
    String name;
    String phoneNumber;
    String sex;
    Integer age;

    public Person(String name, String phoneNumber, String sex, Integer age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.age = age;
    }

    public PersonDto EntityToPersonDto(){
        return new PersonDto(this.name, this.phoneNumber, this.sex);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }
}
