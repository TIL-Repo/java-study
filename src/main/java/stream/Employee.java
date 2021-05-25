package stream;

public class Employee {
    private Long id;
    private String name;
    private int salary;

    public Employee(Long id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void salaryIncrement(int percent){
        this.salary += this.salary / 10;
    }
}
