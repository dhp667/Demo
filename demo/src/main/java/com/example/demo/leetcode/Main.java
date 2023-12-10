package com.example.demo.leetcode;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Main {
    public static void main(String[] args) {
        System.out.print(Character.toLowerCase('A'));
    }


    public String mostFrequentLetters(String s) {
        // 在这⾥写代码
        Map<Character, CharObject> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isEnglish(c)) {
                c = Character.toLowerCase(c);
                CharObject charObject = map.getOrDefault(c, new CharObject(c));
                charObject.incr();
                map.put(c, charObject);
            }
        }
        if (map.isEmpty()) {
            return "";
        }
        PriorityQueue<CharObject> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.times != o2.times) {
                return o2.times - o1.times;
            } else {
                return o1.c - o2.c;
            }
        });
        queue.addAll(map.values());
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append(queue.poll());
        }
        return builder.toString();
    }

    public static boolean isEnglish(char c) {
        return c >= 'A' && c <= 'z';
    }


}

class CharObject {
    char c;
    int times;

    public CharObject(char c) {
        this.c = c;
    }

    public void incr() {
        this.times++;
    }
}


