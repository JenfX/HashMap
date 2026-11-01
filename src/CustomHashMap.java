public class CustomHashMap<K, V> {

    static class Node<K, V> {
        final K key;
        V value;
        final int hash;
        Node<K, V> next;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    private int size;
    private final float LOAD_THRESHOLD = 0.75f;
    private final int INITIAL_CAPACITY = 16;
    private Node<K, V>[] table;


    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.size = 0;
        this.table = new Node[INITIAL_CAPACITY];
    }
    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        this.size = 0;

        int newCapacity = 2;
        while (newCapacity <= capacity) newCapacity *= 2;
        this.table = new Node[newCapacity];
    }

    private int getHash(K key) {
        if (key == null) return 0;
        return key.hashCode();
    }

    private boolean isKeyEquals(K key, int hash, Node<K,V> node) {
        if (hash == node.hash) return ((node.key == null && key == null) || key != null && key.equals(node.key));
        return false;
    }

    public void put(K key, V value) {
        if ((float) size / table.length >= LOAD_THRESHOLD) resize();

        int hash = getHash(key);
        int index = (Math.abs(hash)) % table.length;
        Node<K, V> head = table[index];

        while (head != null) {
            if (isKeyEquals(key, hash, head)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        Node<K, V> newNode = new Node<>(key, value, hash);
        newNode.next = table[index];
        table[index] = newNode;

        size++;
    }

    public V get(K key) {
        int hash = getHash(key);
        int index = (Math.abs(hash)) % table.length;
        Node<K,V> current = table[index];

        while (current != null) {
            if (isKeyEquals(key, hash, current)) return current.value;
            current = current.next;
        }
        return null;
    }

    public boolean remove(K key) {
        int hash = getHash(key);
        int index = Math.abs(hash) % table.length;
        Node<K,V> current = table[index];
        Node<K,V> previous = null;

        while (current != null) {
            if (isKeyEquals(key, hash, current)) {
                if (previous == null) table[index] = current.next;
                else previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Node<K,V>[] newTable = new Node[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Node<K, V> current = table[i];

            while (current != null) {
                int newIndex = Math.abs(current.hash) % newCapacity;
                current.next = newTable[newIndex];
                newTable[newIndex] = current;
                current = current.next;
            }
        }
        table = newTable;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

}
