package itf;

/*
* 인터페이스 상속
*/

interface InterfaceA {
    public void methodA();
}

interface InterfaceB {
    public void methodB();
    public default void defaultMethodB(){
        System.out.println("InterfaceB-defaultMethod");
    }
}

interface InterfaceC extends InterfaceA, InterfaceB {
    public void methodC();
}

class ImplementationC implements InterfaceC {

    @Override
    public void methodA() {
        System.out.println("ImplementationClassC-methodA");
    }

    @Override
    public void methodB() {
        System.out.println("ImplementationClassC-methodB");
    }

    @Override
    public void methodC() {
        System.out.println("ImplementationClassC-methodC");
    }
}

public class Example3 {
    public static void main(String[] args) {
        ImplementationC intf = new ImplementationC();

        InterfaceA interfaceA = intf;
        interfaceA.methodA();
        // interfaceA.methodB();
        // interfaceA.methodC();

        InterfaceB interfaceB = intf;
        interfaceB.methodB();
        // interfaceB.methodB();
        // interfaceB.methodC();
        interfaceB.defaultMethodB();

        InterfaceC interfaceC = intf;
        interfaceC.methodA();
        interfaceC.methodB();
        interfaceC.methodC();
        interfaceC.defaultMethodB();
    }
}
