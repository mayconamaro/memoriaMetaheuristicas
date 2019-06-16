package memoria;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean endOfWord;
    private float fo;

    TrieNode() {
        this.children = new HashMap();
        fo = -1;
    }

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    float getFunctionValue(){
        return fo;
    }
    
    void setFunctionValue(float fo){
        this.fo = fo;
    }
    
    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}