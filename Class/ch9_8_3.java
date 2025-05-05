package Class;


class Math {
    void addition(long x, int y) {
        System.out.println(x + y);
    };
    void addition(int x, int y, int z) {
        System.out.println((x + y + z));
    }
}
public class ch9_8_3 {
    public static void main(String[] args) {
        Math A = new Math();
        A.addition(5, 10);
    }
}
