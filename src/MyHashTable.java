public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + " " + value + "]";
        }
    }

    private HashNode<K, V>[] table;
    private int bucketCount = 11;
    private int currentSize;

    public MyHashTable() {
        this.table = (HashNode<K, V>[]) new HashNode[bucketCount];
        this.currentSize = 0;
    }

    public MyHashTable(int bucketCount) {
        this.bucketCount = bucketCount;
        this.table = (HashNode<K, V>[]) new HashNode[bucketCount];
        this.currentSize = 0;
    }

    private int computeHash(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % bucketCount);
    }

    public void add(K key, V value) {
        int index = computeHash(key);
        HashNode<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        head = new HashNode<>(key, value);
        head.next = table[index];
        table[index] = head;
        currentSize++;
    }

    public V retrieve(K key) {
        int index = computeHash(key);
        HashNode<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V delete(K key) {
        int index = computeHash(key);
        HashNode<K, V> head = table[index];
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    table[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                currentSize--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < bucketCount; i++) {
            HashNode<K, V> head = table[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K findKey(V value) {
        for (int i = 0; i < bucketCount; i++) {
            HashNode<K, V> head = table[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    public void displayBucketSizes() {
        for (int i = 0; i < bucketCount; i++) {
            int count = 0;
            HashNode<K, V> head = table[i];
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
