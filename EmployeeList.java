
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



    public class EmployeeList {

        public static void main(String[] args) throws FileNotFoundException {
            EmployeeList1 list = new EmployeeList1();
            Scanner scanner = new Scanner(new File("C:\\Users\\Kozochka\\IdeaProjects\\lab0_ASDC\\src\\employee.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(", ");
                String name = fields[0];
                String surname = fields[1];
                String position = fields[2];
                String salaryCategory = fields[3];
                int experience = Integer.parseInt(fields[4]);
                long idnp = Long.parseLong(fields[5]);
                Employee employee = new Employee(name, surname, position, salaryCategory, experience, idnp);
                list.insert(employee);
            }
            scanner.close();


            System.out.println("Изначальный список работников:");
            list.display();

            long idnpToDelete = 7890123456789L;
            if (list.search(idnpToDelete)) {
                list.delete(idnpToDelete);
                System.out.println("\n\nСписок после удаления работника с IDNP " + idnpToDelete + ":");
                list.display();
            } else {
                System.out.println("Сотрудник с IDNP " + idnpToDelete + " не найден.");
            }


            Employee employee = new Employee("Артемьева", "Мира", "Аналитик", "Высокий", 7, 1234567890123L);
            list.insert(employee);

            System.out.println("\n\nСписок после вставки нового сотрудника в начало списка:" );
            list.display();



            String FindName = "Александра";
            String FindSurname = "Васильева";
            Employee foundEmployee = list.searchByNameAndSurname(FindName, FindSurname);
            if (foundEmployee != null) {
                System.out.println("\nНайден сотрудник: " + foundEmployee.toString());
            } else {
                System.out.println("\nСотрудник c именем " + FindName + " и фамилией " + FindSurname + " не найден.");
            }


        }
    }

class Employee {
    private String name;
    private String surname;
    private String position;
    private String salaryCategory;
    private int experience;
    private long idnp;

    public Employee(String name, String surname,  String position, String salaryCategory, int experience, long idnp) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salaryCategory = salaryCategory;
        this.experience = experience;
        this.idnp = idnp;
    }

    public String getSurname() {
        return surname;
    }



   public String getName() {
        return name;
    }


    public long getIdnp() {
        return idnp;
    }



    public String toString() {
        return name + ", " + surname + ", " + position + ", " + salaryCategory + ", " + experience + ", " + idnp;
    }
}

class ENode {
    private Employee employee;
    private ENode next;
    public ENode(Employee employee) {
        this.employee = employee;
        next = null;
    }

    public Employee getEmployee() {
        return employee;
    }



    public ENode getNext() {
        return next;
    }

    public void setNext(ENode next) {
        this.next = next;
    }
}

class EmployeeList1 {
    private ENode head;

    public EmployeeList1() {
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void insert(Employee employee) {
        ENode newNode = new ENode(employee);
        newNode.setNext(head);
        head = newNode;
    }

    public boolean search(long idnp) {
        ENode current = head;
        while (current != null) {
            if (current.getEmployee().getIdnp() == idnp) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Employee searchByNameAndSurname(String name, String surname) {
        ENode current = head;
        while (current != null) {
            if (current.getEmployee().getName().equals(name) && current.getEmployee().getSurname().equals(surname)) {
                return current.getEmployee();
            }
            current = current.getNext();
        }
        return null;
    }

    public void delete(long idnp) {
        if (isEmpty()) {
            System.out.println("Список пустой.");
        } else {
            if (head.getEmployee().getIdnp() == idnp) {
                head = head.getNext();
            } else {
                ENode previous = head;
                ENode current = head.getNext();
                while (current != null) {
                    if (current.getEmployee().getIdnp() == idnp) {
                        previous.setNext(current.getNext());
                        return;
                    }
                    previous = current;
                    current = current.getNext();
                }
            }
        }
    }

    public void display() {
        ENode current = head;
        while (current != null) {
            System.out.println(current.getEmployee().toString());
            current = current.getNext();
        }
    }
}



