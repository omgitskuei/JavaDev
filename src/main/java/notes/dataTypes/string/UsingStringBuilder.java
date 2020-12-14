package main.java.notes.dataTypes.string;

public class UsingStringBuilder {

    public static void main(String[] args) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("hi m");
        sBuilder.append("y name is Kuei.");
        System.out.println(sBuilder.toString());

        sBuilder.delete(0, 2);
        System.out.println(sBuilder.toString());
    }

}
