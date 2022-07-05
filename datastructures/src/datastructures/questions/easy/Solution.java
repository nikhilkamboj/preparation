package datastructures.questions.easy;

import java.util.Arrays;

public class Solution {
    public int removeElement(int[] nums, int val) {
        int j = nums.length - 1;
        int i = 0;
        int k = 0;

        while (true) {
            if (j >= 0 && nums[j] == val) {
                j--;
                k++;
            } else {
                break;
            }
        }

        while (i < j) {
            if(nums[j] == val) {
                k++;
                j--;
            }
            if(nums[i] == val) {
                nums[i] = nums[j];
                nums[j] = val;
                i++;
            } else {
                i++;
            }
        }
        return nums.length - k;
    }

    public void plusOne(int[] digits) {
        int multiplier = 1;
        int val = 0;
        for (int i = digits.length-1; i >=0; i--) {
            val = val + digits[i] * multiplier;
            multiplier = multiplier*10;
        }
        int newVal = val + 1;



        char[] value = String.valueOf(newVal).toCharArray();
        int[] finalVal = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            finalVal[i] =  value[i];
        }

    }
}
