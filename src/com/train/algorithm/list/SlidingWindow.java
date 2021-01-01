package com.train.algorithm.list;

import java.util.HashMap;
import java.util.Map;

// 滑动窗口合集，通常与hashmap组合
public class SlidingWindow {
    //leetcode-1663
    public String getSmallestString(int n, int k) {
        char[] c = new char[n];
        StringBuilder str = new StringBuilder();
        for(int i = n - 1;i >= 0;i--){
            if(k - i > 26){
                c[i] = 'z';
                k -= 26;
            }else{
                if(k == 1){
                    c[i] = (char)(k + 96);
                    continue;
                }
                c[i] = (char)(k - i + 96);
                k = 1;
            }
        }
        for(int i = 0;i < n;i++){
            str.append(c[i]);
        }
        return str.toString();
    }

    //leetcode-904
    public int totalFruit(int[] tree) {
        Map<Integer,Integer> window = new HashMap<>();
        int count = 0,left = 0,right = 0,maxlen = 0;
        while(right < tree.length){
            if(!window.containsKey(tree[right])||window.get(tree[right])==0){
                count++;
            }
            window.put(tree[right],window.getOrDefault(tree[right],0) + 1);
            right++;
            if(count <= 2){
                maxlen = Math.max(maxlen,right-left);
            }
            while(count > 2){
                window.put(tree[left],window.getOrDefault(tree[left],0) - 1);
                if(window.get(tree[left]) == 0){
                    count--;
                }
                left++;
            }
        }
        return maxlen;
    }

    //leetcode-3
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap();
        int len = 0,left = 0;
        for(int i = 0;i < s.length();i++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            len = Math.max(len,i - left + 1);
        }
        return len;
    }
}
