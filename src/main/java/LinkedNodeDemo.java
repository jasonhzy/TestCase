public class LinkedNodeDemo {
    /**
     * 单向链表定义
     **/
    static class Node<T> {
        private T value;    //节点值
        private Node<T> next;   //后继节点

        public Node() {
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 打印链表
     **/
    private void print(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + "\t");
            node = node.next;
        }
        System.out.println();
    }


    /**
     * 反转链表
     **/
    private Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node temp = null;
        while (head.next != null) {
            temp = head.next;   //保存下一个节点
            head.next = prev;   //重置next
            prev = head;    //保存当前节点
            head = temp; //头节点前移到下一个节点
        }
        head.next = prev;//原尾节点成为头节点，并设置下一个节点
        return head;
    }

    /**
     * 反转链表
     **/
    private Node reverse0(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node temp = null;
        while (head != null) {
            temp = head.next;   //保存下一个节点
            head.next = prev;   //重置next
            prev = head;    //保存当前节点
            head = temp; //头节点前移到下一个节点
        }
        return prev;
    }


    private Node reverse1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = null;
        Node p = head.next;
        head.next = null;
        while ( p != null) {
            temp = p.next;
            p.next = head;
            head = p;
            p = temp;
        }
        return head;
    }

    public Node reverse2(Node current)
    {
        if (current == null || current.next == null) {
            return current;
        }
        Node nextNode = current.next;
        current.next = null;
        Node reverseRest = reverse(nextNode);
        nextNode.next = current;
        return reverseRest;
    }

    /**
     * 初始化链表
     **/
    private Node initLinkedList(int num) {
        Node head = new Node(0, null);
        Node cur = head;
        for (int i = 1; i < num; i++) {
            cur.next = new Node(i, null);
            cur = cur.next;
        }
        return head;
    }



    public void test() {
        Node head = initLinkedList(5);
        print(head);
        System.out.println("**************");
        //反转链表
//        Node node = reverse(head);
        Node node = reverse0(head);
//        Node node = reverse1(head);
//        Node node = reverse2(head);
        print(node);
    }

    public static void main(String[] args) {
        LinkedNodeDemo demo = new LinkedNodeDemo();
        demo.test();
    }

}