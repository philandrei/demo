package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;

import javax.crypto.SecretKey;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

import static com.example.demo.AESUtil.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
//        SecureRandom random = new SecureRandom();
//        SecretKey secretKey = AESUtil.generateKey(256);
//        byte[] IV = generateIV();
//        random.nextBytes(IV);
//        String input = "Vertere";
//        byte[] encrypted = encryptWithPrefixIV(input, secretKey, IV);
//        String strEncrypted = Base64.getEncoder().encodeToString(encrypted);
//        System.out.println("Encrypted: " + strEncrypted);
//        String decrypted = decryptWithPrefixIV(strEncrypted, secretKey);
//        System.out.println("Decrypted: " + decrypted);


//        System.out.println(isValid("()"));
//        System.out.println(isValid("()["));

//        System.out.println(removeElement(new int[]{3, 2, 2, 3, 5}, 3));
//        System.out.println(twoSum(new int[]{2, 7, 11, 15}, 18));
//        int[] x = twoSum(new int[]{2, 7, 11, 15}, 18);

//        boolean isDuplicate = containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3);
//        System.out.println(isDuplicate);
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        for (int c = 0; c < nums.length; c++) {
            int d = c + k;
            if (d >= nums.length) {
                return false;
            }
            if (nums[c] == nums[d]) {
                return true;
            }
        }
        return false;
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int c = 0; c < nums.length; c++) {
            for (int x = c + 1; x < nums.length; x++) {
                if ((nums[c] + nums[x]) == target) {
                    return new int[]{c, x};
                }
            }
        }
        return new int[]{0, 0};
    }

    public static int removeElement(int[] nums, int val) {
        Set set = new HashSet();
        int counter = 0;
        for (int c = 0; c < nums.length; c++) {
            if (nums[c] != val) {
                if (nums[(c == 0 ? 0 : c--)] == val) {
                    nums[(c == 0 ? 0 : c--)] = nums[c];
                }
            }
        }

        for (int x : nums) {
            System.out.println("sep" + x);
        }
        return counter;
    }

    public static int removeDuplicates(int[] nums) {
        List<Integer> l = new ArrayList<>();
        int index = 0;
        for (int c = 0; c < nums.length; c++) {
            if (!l.contains(nums[c])) {
                l.add(nums[c]);
            }
        }
        for (Integer x : l) {
            nums[index] = x;
            index++;
        }
        for (int c = index; c < nums.length - 1; c++) {
            nums[c] = 0;
        }

        return index;
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void rightTriangle(int size) {
        for (int x = 0; x < 5; x++) {
            for (int c = 0; c <= x; c++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void fibonnaci(int param) {
        int z = 0;
        int x = 1;
        for (int c = 1; c < param; c++) {
            int res = z + x;
            System.out.print(res + ((c + 1) != param ? "," : ""));
            z = x;
            x = res;
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        int arrLength = strs.length - 1;
        if (strs[0].equals("") || arrLength == 0) {
            return strs[0];
        }
        String res = "";
        String str = strs[0];

        for (int x = 0; x < str.length(); x++) {
            for (int c = 1; c <= arrLength; c++) {
                if (strs[c].length() <= x || str.charAt(x) != strs[c].charAt(x)) {
                    return res;
                }
            }
            res = res + str.charAt(x);
        }
        return res;
    }

    public static int lengthOfLastWord(String s) {
        String[] arr = s.trim().split(" ");
        return arr[arr.length - 1].length();
    }

}
