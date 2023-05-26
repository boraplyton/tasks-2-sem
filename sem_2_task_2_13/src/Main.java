public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SimpleLinkedList<Double> sl = new SimpleLinkedList<>();
        sl.addLast((double) -3);
        sl.addLast((double) 62);
        sl.addLast((double) -1);
        sl.addLast((double) -1);
        sl.addLast((double) -1);
        sl.addLast((double) -1);
        sl.addLast((double) 64);
        sl.addLast((double) -1);
        sl.addLast((double) -1);




        SimpleLinkedListNode<Double> result = sl.doTask();
        if (result == null){
            System.out.println("Такаго элемента нет, все элементы отрицательные");
        } else {
            System.out.println(result.value); // Выводим значение если убрать value то будет выводиться нод
        }
    }
}