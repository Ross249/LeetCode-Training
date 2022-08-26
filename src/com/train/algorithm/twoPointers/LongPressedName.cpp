//
// Created by 罗斯 on 26/8/2022.
//
#include <string>

using namespace std;
class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int i = 0,j = 0;
        while (i < name.size()&&j < typed.size()){
            if(name[i] == typed[j]){
                ++i;
                ++j;
            }else if(j > 0 && typed[j] == typed[j - 1]){
                ++j;
            }else{
                return false;
            }
        }
        while (j < typed.size() && typed[j] == typed[j - 1]) ++j;
        return j == typed.size() && i == name.size();
    }
};