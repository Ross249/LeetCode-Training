//
// Created by 罗斯 on 24/12/2022.
//
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    bool isMatch(string s, string p) {
        return isMatch(s.c_str(), p.c_str());
    }
private:
    bool isMatch(const char *a, const char* b){
        if (*b == '\0') return *a == '\0';

        if (b[1] != '*' || b[1] == '\0'){
            if (*a == '\0') return false;
            if (*a == *b || *b == '.')
                return isMatch(a+1,b+1);
            else
                return false;
        }else{
            int i = -1;
            while (i == -1 || a[i]==b[0]|| b[0] == '.'){
                if (isMatch(a + i + 1, b + 2)) return true;
                if (a[++i] == '\0') break;
            }
            return false;
        }
        return false;
    }
};