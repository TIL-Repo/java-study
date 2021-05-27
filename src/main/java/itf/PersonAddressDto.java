package itf;

public class PersonAddressDto {

    String name;
    String phoneNumber;
    String sex;
    String country;
    String city;

    public PersonAddressDto(String name, String phoneNumber, String sex, String country, String city) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.country = country;
        this.city = city;
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

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
