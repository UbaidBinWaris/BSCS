import com.example.*;

public class Main {

    public static void main(String[] args) {

        Employee e1 = new SalariedEmployee("Ubaid",20000.00);
        Employee e2 = new SalariedEmployee("Moeez",45000.00);


//        System.out.println("My name is :");
//        System.out.println(Me.getName());
//
//        System.out.println("My name is :"  Me.getName());
//
//        System.out.println("My Salary is :");
//        System.out.println(Me.earning());

        System.out.println(e1.toString());
        System.out.println(e2.toString());

    }

}
