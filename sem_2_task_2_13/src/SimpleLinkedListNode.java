public class SimpleLinkedListNode<T> {
    public T value;
    public SimpleLinkedListNode<T> next;

    public SimpleLinkedListNode(T value, SimpleLinkedListNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public SimpleLinkedListNode(T value) {
        this(value, null);
    }

}
