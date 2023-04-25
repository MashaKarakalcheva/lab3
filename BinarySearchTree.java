import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Employee {
    String name;
    String surname;
    String position;
    String salaryCategory;
    int experience;
    long idnp;

    public Employee(String name, String surname, String position,
                    String salaryCategory, int experience, long idnp) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salaryCategory = salaryCategory;
        this.experience = experience;
        this.idnp = idnp;
    }
}

class Node {
    Employee employee;
    Node left;
    Node right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Employee employee) {
        root = insert(root, employee);
    }

    private Node insert(Node node, Employee employee) {
        if (node == null) {
            node = new Node(employee);
            return node;
        }
        if (employee.idnp < node.employee.idnp) {
            node.left = insert(node.left, employee);
        } else if (employee.idnp > node.employee.idnp) {
            node.right = insert(node.right, employee);
        }
        return node;
    }

    public void delete(long idnp) {
        root = delete(root, idnp);
    }

    private Node delete(Node node, long idnp) {
        if (node == null) {
            return null;
        }
        if (idnp < node.employee.idnp) {
            node.left = delete(node.left, idnp);
        } else if (idnp > node.employee.idnp) {
            node.right = delete(node.right, idnp);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node temp = findMinNode(node.right);
            node.employee = temp.employee;
            node.right = delete(node.right, temp.employee.idnp);
        }
        return node;
    }

    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node search(long idnp) {
        return search(root, idnp);
    }

    private Node search(Node node, long idnp) {
        if (node == null || node.employee.idnp == idnp) {
            return node;
        }
        if (node.employee.idnp > idnp) {
            return search(node.left, idnp);
        } else {
            return search(node.right, idnp);
        }
    }

    public void InOrder() {
        InOrder(root);
    }

    private void InOrder(Node node) {
        if (node != null) {
            InOrder(node.left);
            System.out.print(node.employee.name + " ");
            System.out.print(node.employee.surname + " ");
            System.out.print(node.employee.position + " ");
            System.out.print(node.employee.salaryCategory + " ");
            System.out.print(node.employee.experience + " ");
            System.out.println(node.employee.idnp + " ");
            InOrder(node.right);
        }
    }



    private void PreOrder(Node node) {
        if (node != null) {
            System.out.print(node.employee.name + " ");
            System.out.print(node.employee.surname + " ");
            System.out.print(node.employee.position + " ");
            System.out.print(node.employee.salaryCategory + " ");
            System.out.print(node.employee.experience + " ");
            System.out.println(node.employee.idnp + " ");
            PreOrder(node.left);
            PreOrder(node.right);
        }
    }



    private void PostOrder(Node node) {
        if (node != null) {
            PostOrder(node.left);
            PostOrder(node.right);
            System.out.print(node.employee.name + " ");
            System.out.print(node.employee.surname + " ");
            System.out.print(node.employee.position + " ");
            System.out.print(node.employee.salaryCategory + " ");
            System.out.print(node.employee.experience + " ");
            System.out.println(node.employee.idnp + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Kozochka\\IdeaProjects\\lab0_ASDC\\src\\employee.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String profession = parts[2].trim();
                String salaryCategory = parts[3].trim();
                int experience = Integer.parseInt(parts[4].trim());
                long idnp = Long.parseLong(parts[5].trim());
                Employee employee = new Employee(firstName, lastName, profession, salaryCategory, experience, idnp);
                bst.insert(employee);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Удаление работника с указанным IDNP ");
        bst.delete(123456789012L);
        System.out.println("Центрированный/симметричный обход после удаления:");
        bst.InOrder();
        System.out.println("Поиск работника с указанным IDNP:");
        Node node = bst.search(123456789123L);
        if (node != null) {
            System.out.println("Работник найден:");
            System.out.print(node.employee.name + " ");
            System.out.print(node.employee.surname + " ");
            System.out.print(node.employee.position + " ");
            System.out.print(node.employee.salaryCategory + " ");
            System.out.print(node.employee.experience + " ");
            System.out.println(node.employee.idnp + " ");
        } else {
            System.out.println("Работник не найден.");
        }
    }
}