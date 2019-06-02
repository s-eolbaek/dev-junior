//단방향 연결리스트 (코딩인터뷰 완전분석+geeksforgeeks 참조)
class Node<T>{
    Node next = null;
    T data;

    public Node(T t){
        data = t;
    }
}

class LinkedList<T> {
    Node head;

    void appendToTail(T t) {
        Node node = new Node(t);
        if (head == null) head = node;
        else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }


    void deleteNode(T t) {
        Node n = head;
        if (n.data.equals(t)) {
            head = head.next;
        } else {
            while (n.next != null) {
                if (n.next.data.equals(t)) {
                    n.next = n.next.next;
                    break;
                }
                n = n.next;
            }
        }
    }
    //같은 data값을 가진 노드가 여러개라면?

    void printList(){
        Node n = head;
        while(n!=null){
            System.out.print(n.data+" ");
            n = n.next;
        }
    }
}
