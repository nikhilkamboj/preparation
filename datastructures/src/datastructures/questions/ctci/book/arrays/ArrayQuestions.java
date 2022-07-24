package datastructures.questions.ctci.book.arrays;

import java.util.*;

public class ArrayQuestions {

    /***
     * 2 approaches here
     * 1. checking through total number of ascii chars i.e. 128
     * 2. through a hashTable
     * 3. through bit manipulation, >>>>>>> TO BE DONE <<<<<<<<<<<
     */
    public boolean checkIfStringHasUniqueChars(String string) {
        if (string.length() > 128) {
            return false;
        }
        boolean[] charArray = new boolean[string.length()];

        for (int i = 0; i < string.length(); i++) {
            int charDecimalVal = string.charAt(i);
            if (charArray[charDecimalVal]) {
                return false;
            }
            charArray[charDecimalVal] = true;
        }
        return true;
    }

    /**
     * 1. Permutation means a string has SAME characters but in different order.
     * 2. Best way is to store count in int array with 128 length, and making sure index is based on char decimal val.
     */
    public boolean checkIfStringPermutationOfOther(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] characterFrequency = new int[128];

        for (char c: s1.toCharArray()) {
            characterFrequency[c]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            char currentChar = s2.charAt(i);
            characterFrequency[currentChar]--;
            if (characterFrequency[currentChar] < 0) {
                return false;
            }
        }
        return true;
    }

    // example url " Mr John Smith         ", trueLength = 15
    public String urifyString(char[] url, int trueLength) {
        int index = trueLength-1;
        int startIndex = url.length - 1;
        while (index >= 0) {
            if (url[index] == ' ') {
                url[startIndex--] = '0';
                url[startIndex--] = '2';
                url[startIndex--] = '%';
            } else {
                url[startIndex] = url[index];
                startIndex--;
            }
            index--;
        }
        return Arrays.toString(url);
     }


    /***
     * one way is using
     * >>>>> HASH TABLE
     * >>>>> using int array to store each character count and then iterating over to check if there are atleast
     * 2 chars with 1 count.
     * >>>>>> out of 2, 1st handles all scenario better even the capital characters ones as well.<<<<<<<<<<
     */
     public boolean checkIfPermutationIsPalindrome(String string) {
         int[] characterCountTable = getCharacterCount(string);
         return checkCharacterCountForPalindrome(characterCountTable);
     }

     private int[] getCharacterCount(String string) {
         char[] chars = string.toCharArray();
         int aValue = 'a';
         int zValue = 'z';
         int[] countArray = new int[zValue - aValue + 1];

         for (char c: chars) {
             countArray[c - 'a']++;
         }

         return countArray;
     }

     // logic is no more than one character should have odd count
     private boolean checkCharacterCountForPalindrome(int[] charCountTable) {
         boolean isCountOddMoreThanOnce = false;
         for (int count: charCountTable) {
             if (count%2 == 1) {
                 if (isCountOddMoreThanOnce) {
                     return false;
                 }
                 isCountOddMoreThanOnce = true;
             }
         }
         return true;
     }



     public boolean checkIfStringsMatchAtOnePass(String s1, String s2) {
         if (s1.length() == s2.length()) {
             return isOnePassReplacement(s1, s2);
         } else if (s1.length() + 1 == s2.length()) {
             return isOneInsertAway(s1, s2);
         } else if (s1.length() - 1 == s2.length()) {
             return isOneInsertAway(s2, s1);
         }
         return false;
     }

     private boolean isOnePassReplacement(String s1, String s2) {
         boolean isDifferenceMoreThan1 = false;
         for (int i = 0; i < s1.length(); i++) {
             if (s1.charAt(i) != s2.charAt(i)) {
                 if (isDifferenceMoreThan1) {
                     return false;
                 }
                 isDifferenceMoreThan1 = true;
             }
         }
         return true;
     }

     private boolean isOneInsertAway(String s1, String s2) {
         int index1 = 0;
         int index2 = 0;
         boolean isDifferenceMoreThanOne = false;
         // here s1 is always shorter string.
         while (index1 < s1.length() && index2 < s2.length()) {
             if (s1.charAt(index1) != s2.charAt(index2)) {
                 if (isDifferenceMoreThanOne) {
                     return false;
                 }
                 index2++;
                 isDifferenceMoreThanOne = true;
             } else {
                 index1++;
                 index2++;
             }
         }
         return true;
     }

     public String compressString(String string) {
         char[] chars = string.toCharArray();
         int count = 1;
         char prev = chars[0];
         StringBuilder compressedString = new StringBuilder("");
         for (int i = 1; i < chars.length; i++) {
             if (chars[i] == prev) {
                 count++;
             } else {
                 compressedString.append(prev).append(count);
                 prev = chars[i];
                 count = 1;
             }
         }
         compressedString.append(prev).append(count);
         return string.length() > compressedString.length() ? compressedString.toString() : string;
     }

     public void invertMatrix90(int[][] matrix) {
         int height = matrix.length;
         for (int n = 0; n < height/2; n++) {
             int firstLayer = n;
             int bottomLayer = height - 1 - firstLayer;
             for (int i = firstLayer; i < bottomLayer; i++) {
                 int offset = bottomLayer - i;
                 int top = matrix[firstLayer][i];
                 // left --> top
                 matrix[firstLayer][i] = matrix[offset-i][firstLayer];
                 // bottom --> left
                 matrix[offset-i][firstLayer] = matrix[bottomLayer][offset-i];
                 // right --> bottom
                 matrix[bottomLayer][offset-i] = matrix[i][bottomLayer];
                 // top --> right
                 matrix[i][bottomLayer] = top;
             }
         }
     }

     public void zeroMatrix(int[][] matrix) {
         int height = matrix.length;
         Set<Integer> zeroIndexSet = new HashSet<>();
         // getting all zero indices
         for (int n = 0; n < height; n++) {
             int rowLength = matrix[n].length;
             for (int i = 0; i < rowLength; i++) {
                 if (matrix[n][i] == 0) {
                     zeroIndexSet.add(n);
                     zeroIndexSet.add(i);
                 }
             }
         }

         // setting each element 0, which have indices in set
         for (int n = 0; n < height; n++) {
             int rowLength = matrix[n].length;
             for (int i = 0; i < rowLength; i++) {
                 if (zeroIndexSet.contains(n) || zeroIndexSet.contains(i)) {
                     matrix[n][i] = 0;
                 }
             }
         }

     }

    /**
     * concatenate and just use JAVA's String is SubString method, but this was intentional to use
     * own implementation, will be updated soon to better implementation
     */
     public boolean isRotation(String s1, String s2) {
         if (s1.length() != s2.length()) {
             return false;
         }
         s2 = s2.concat(s2);
         char[] s2Chars = s2.toCharArray();

         for (int i = 0; i < s2Chars.length; i++) {
             if (s2Chars[i] == s1.charAt(0)) {
                 if (isRotation(s2Chars, i, s1)){
                     return true;
                 }
             }
         }
         return false;
     }

     private boolean isRotation(char[] s2, int index, String s1) {
         if (s1.length() + index > s2.length) {
             return false;
         }
         for (int i = index; i < s1.length() + index ; i++) {
             if (s2[i] != s1.charAt(i)) {
                 return false;
             }
         }
         return true;
     }

    public static void main(String[] args) {
        ArrayQuestions aq = new ArrayQuestions();
        System.out.println(aq.compressString("aabcccccaaa"));
    }

}
