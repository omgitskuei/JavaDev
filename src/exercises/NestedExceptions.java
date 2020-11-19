package exercises;

public class NestedExceptions {

    public static void main(String[] args) {
        try {
            String a = null;
            try {
                System.out.println(a.length());
                
            } catch (NullPointerException e) {
                
                System.out.println("Triggered Inner");
                throw new NullPointerException();
            }
        } catch (Exception e) {
            System.out.println("Triggered Outer");
        }
    }

}
