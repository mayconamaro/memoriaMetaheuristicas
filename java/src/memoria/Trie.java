package memoria;

public class Trie {
    
    private final TrieNode root;
    public static final int NAO_ENCONTRADO = -1;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, float fo) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.setFunctionValue(fo);
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    public float containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return NAO_ENCONTRADO;
            }
            current = node;
        }
        if(current.isEndOfWord()){
            return current.getFunctionValue();
        }else
            return -1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }
}