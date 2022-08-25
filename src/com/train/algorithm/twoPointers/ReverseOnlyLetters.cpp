//
// Created by 罗斯 on 25/8/2022.
//
#include <string>
#include <ctype.h>

using namespace std;
class Solution {
public:
    string reverseOnlyLetters(string s) {
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(isalpha(s[i]) && isalpha(s[j])){
                swap(s[i++],s[j--]);
            }else{
                if (!isalpha(s[i])) ++i;
                if (!isalpha(s[j])) --j;
            }
        }
        return s;
    }
};