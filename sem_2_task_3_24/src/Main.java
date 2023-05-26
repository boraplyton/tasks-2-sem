import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        SimpleLinkedList<Integer> r = new SimpleLinkedList<>();
        r.addLast(1);
        r.addLast(2);
        r.addLast(3);
        r.addLast(4);
        r.doTaskRecursVersion();

        Iterator<Integer> iter = r.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}