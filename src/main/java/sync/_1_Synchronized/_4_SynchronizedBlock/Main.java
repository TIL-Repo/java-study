package sync._1_Synchronized._4_SynchronizedBlock;

public class Main {

	public static void main(String[] args) {
		A a = new A();
		A b = new A();
		Thread thread1 = new Thread(() -> {
			a.syncRun("thread1");
		});
		Thread thread2 = new Thread(() -> {
			b.syncRun("thread2");
		});
		thread1.start();
		thread2.start();
	}
}

class A {
	public void run(String name) {
		System.out.println(name + " lock");
		System.out.println(name + " unlock");
	}

	public void syncRun(String name) {
		synchronized (this) {
			System.out.println(name + " lock");
			System.out.println(name + " unlock");
		}
	}
}