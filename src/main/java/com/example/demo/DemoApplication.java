package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;

import javax.crypto.SecretKey;
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
    }

    public static int removeDuplicates(int[] nums) {
        List<Integer> l = new ArrayList<>();
        int index = 0;
        for (int c = 0; c < nums.length; c++) {
            if(!l.contains(nums[c])){
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
