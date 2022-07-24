package datastructures.basic.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Character character;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}
