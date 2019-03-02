public class SinglyLinkedList<E> {

    private static class Node<E> {
        private E ele;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            ele = e;
            next = n;
        }

        public E getElement() {
            return ele;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if(size <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public E first() {
        return head.getElement();
    }

    public E last() {
        return tail.getElement();
    }

    public void addFirst(E element) {
        if(!isEmpty()) {
            Node<E> temp = new Node<E>(element, head);
            head = temp;
            size++;
        }
        else{
            Node<E> temp = new Node<E>(element, head);
            head = temp;
            tail = temp;
            size++;
        }
    }

    public void addLast(E element) {
        if(!isEmpty()) {
            Node<E> temp = new Node<E>(element, null);
            tail.setNext(temp);
            tail = temp;
            size++;
        }
        else{
            Node<E> temp = new Node<E>(element, null);
            head = temp;
            tail = temp;
            size++;
        }
    }

    public E removeFirst() {
        if(!isEmpty()) {
            E temp = head.getElement();
            head = head.getNext();
            size--;
            return temp;
        }
        else{
            return null;
        }
    }

}