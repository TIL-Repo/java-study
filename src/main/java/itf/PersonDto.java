package itf;

public class PersonDto {
    String name;
    String phoneNumber;
    String sex;

    public PersonDto(String name, String phoneNumber, String sex) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
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
}
