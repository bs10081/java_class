package Class;


class MyClass {
    int age;
    String name;
    MyClass(int a){
        age = a;
    }
    MyClass(String str){
        name = str;
    }
    MyClass(int a, String str){
        age = a;
        name = str;
    }
    void printProfile() {
        if (name != null) {
            System.out.println(name);
        }
        if (age != 0) {
            System.out.println(age);
        }
    }
}
public class multifunction {
    public static void main(String[] args) {
        MyClass Age = new MyClass(21);
        MyClass Name = new MyClass("RegChien");
        MyClass info = new MyClass(21, "RegChien");
        Age.printProfile();
        System.out.println("---");
        Name.printProfile();
        System.out.println("---");
        info.printProfile();
    }
}
