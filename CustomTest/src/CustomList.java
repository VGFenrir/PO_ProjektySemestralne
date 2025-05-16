import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomList<T> extends AbstractList<T> {
    Node head;
    Node tail;
    private class Node{
        T value;
        Node next;

        public Node(T value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    public CustomList(){
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        int size = 0;
        Node current = this.head;
        while(true){
            size ++;
            if(current.next == null) break;
            current = current.next;
        }
        return size;
    }

    public void addLast(T value){
        Node lastNode = new Node(value, null);
        if(this.head == null){
            this.head = lastNode;
        }
        else{
            this.tail.next = lastNode;
        }
        this.tail = lastNode;
    }
    public T getLast(){
        return this.tail.value;
    }

    public void addFirst(T value){
        Node firstNode = new Node(value,this.head);
        if(this.head == null){
            this.tail = firstNode;
        }
        this.head = firstNode;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        Node current = head;
        while(true){
            if(current == null) break;
            result.append(current.value).append(" ");
            if(current.next == null) break;
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }

    public boolean add(T value){
        addLast(value);
        return true;
    }

    @Override
    public T get(int index){
        if (head == null) throw new NoSuchElementException();
        int size = 0;
        Node current = this.head;
        int counter = 0;
        while(true){
            if(counter == index) return current.value;
            if(current.next == null) throw new NoSuchElementException();
            current = current.next;
            counter ++;
        }
    }

//    public Iterator<T> iterator(){
//        return new Iterator<T>() {
//            private Node current = head;
//
//            public boolean hasNext() {
//                return current != null;
//            }
//
//            public T next() {
//                T result = current.value;
//                current = current.next;
//                return result;
//            }
//        }
//    }
    public static <T>CustomList<T> filterByClass(CustomList<T> list, Class<?> cl){
        CustomList<T> list1 = new CustomList<>();
        for(T c: list){
            if(c.getClass().equals(cl)){
                list1.add(c);
            }
//            if(cl.isInstance(c)){
//                list1.add(c);
//            }
        }
        return list1;
    }
}

