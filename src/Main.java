import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> binarySearchTree = new BST<>();

        binarySearchTree.put(9, "Value 9");
        binarySearchTree.put(7, "Value 7");
        binarySearchTree.put(17, "Value 17");
        binarySearchTree.put(6, "Value 6");
        binarySearchTree.put(1, "Value 1");

        System.out.println("Value with key 3: " + binarySearchTree.get(3));
        binarySearchTree.delete(3);
        System.out.println("Value with key 3 after removal: " + binarySearchTree.get(3));

        System.out.println("Keys in ascending order:");
        for (BST<Integer, String>.Node node : binarySearchTree) {
            System.out.println("key is " + node.getKey() + " and value is " + node.getValue());
        }

        MyHashTable<MyTestingClass, String> hashtable = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1000);
            MyTestingClass object = new MyTestingClass(id);
            hashtable.add(object, "Value " + i);
        }

        hashtable.displayBucketSizes();
    }
}
