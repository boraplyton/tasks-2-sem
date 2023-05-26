import java.util.Iterator;


public class SimpleLinkedList<T> implements Iterable<T> {

    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private class SimpleLinkedListNode<T> {
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

    private SimpleLinkedListNode<T> head = null;
    private SimpleLinkedListNode<T> tail = null;
    private int count;

    public void addFirst(T value) {
        head = new SimpleLinkedListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        SimpleLinkedListNode<T> temp = new SimpleLinkedListNode<>(value);

        if (count == 0) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
    }

    private void checkEmpty() throws SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private SimpleLinkedListNode<T> getNode(int index) {
        int i = 0;
        for (SimpleLinkedListNode<T> curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null;
    }

    public T removeFirst() throws SimpleLinkedListException {
        checkEmpty();
        T value = head.value;
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return value;
    }

    public T removeLast() throws SimpleLinkedListException {
//        checkEmpty();
//        T value = head.value;
//        if (count == 1) {
//            head = tail = null;
//        } else {
//            SimpleLinkedListNode<T> prev = getNode(count - 2);
//            prev.next = null;
//            tail = prev;
//            count--;
//        }
//        return value;
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        checkEmpty();
        T value;
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            SimpleLinkedListNode<T> current = prev.next;
            prev.next = current.next;
            value = current.value;
            if (count == index - 1) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            prev.next = new SimpleLinkedListNode<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
            count++;
        }
    }


    public int size() {
        return count;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();
        return tail.value;
    }

    public T get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }


    public void doTaskStackVersion() throws Exception {
        if (head == null) {
            return;
        }
        SimpleLinkedListStack<SimpleLinkedListNode<T>> helpStack = new SimpleLinkedListStack<>();

        for (SimpleLinkedListNode<T> currentNode = head; currentNode != null; currentNode = currentNode.next) {
            helpStack.push(currentNode);
        }
        head = helpStack.pop();
        SimpleLinkedListNode<T> currentNode = head;

        for (int i = 0; i < size() - 2; i++) {
            currentNode.next = helpStack.pop();
            currentNode = currentNode.next;
        }
        if (size() == 1) {
            tail = head;
            return;
        }
        tail = helpStack.pop();
        currentNode.next = tail;
        tail.next = null;

    }

    public void doTaskRecursVersion() {
        reverseList(head);
        head = tail;
    }

    private SimpleLinkedListNode<T> reverseList(SimpleLinkedListNode<T> node) {
        if (node == null || node.next == null){
            return node;
        }

        SimpleLinkedListNode<T> remaining = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return remaining;
    }


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}