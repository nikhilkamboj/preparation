package datastructures.basic.trie;

public class Trie {
    private TrieNode root;

    // insert a word into trie
    public void insertWord(String word) {
        char[] characters = word.toCharArray();
        TrieNode current = root;
        for (Character c: characters) {
           current = current.getChildren().computeIfAbsent(c, l -> new TrieNode());
        }
        current.setWord(true);
    }


    // check if the trie has the word
    public boolean isWordValid(TrieNode current, String word, int index) {
        if (word.length() == index) {
            return current.isWord;
        }
        Character c = word.charAt(index);
        TrieNode node = current.getChildren().get(c);
        if (node == null) {
            return false;
        }
        return isWordValid(node, word, index++);
    }

    public void delete(String word) {
        TrieNode current = root;
        removeWord(current, word, 0);
    }

    // remove a word from the trie
    // conceptually, we need to find point of diversion in trie, draw an example and see for yourself
    private boolean removeWord(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isWord) {
                // word is present but not marked or already removed
                return false;
            }
            current.setWord(false);
            return current.getChildren().isEmpty();
        }
        TrieNode node = current.getChildren().get(word.charAt(index));
        if (node == null) {
            // means the word is not present in the trie
            return false;
        }

        boolean isCurrentNodeToBeDeleted = removeWord(node, word, index++) && !current.isWord;

        if (isCurrentNodeToBeDeleted) {
            current.getChildren().remove(word.charAt(index));
            // means even after removal there is already another character into the map
            return current.getChildren().isEmpty();
        }
        return false;
    }

}
