package itf;

/*
* 인터페이스 다형성
*/

interface User {
    void printType();
}

class Recipient implements User {

    @Override
    public void printType() {
        System.out.println("수취인입니다.");
    }
}
class Sender implements User {

    @Override
    public void printType() {
        System.out.println("송신자입니다.");
    }
}

class Mail {
    public void printUserType(User user){
        user.printType();
    }
}

public class Example2 {
    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.printUserType(new Recipient());
        mail.printUserType(new Sender());
    }
}
