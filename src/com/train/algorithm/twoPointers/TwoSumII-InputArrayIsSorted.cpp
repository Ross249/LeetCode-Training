//
// Created by 罗斯 on 29/8/2022.
//
#include <vector>

using namespace std;
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
//        int i = 0;
//        int j = numbers.size() - 1;
//        while (i < j){
//            const int sum = numbers[i] + numbers[j];
//            if(sum == target)
//                break;
//            else if (sum < target)
//                ++i;
//            else
//                --j;
//        }
//        return {i+1,j+1};

        const int n = numbers.size();
        for(int i = 0;i < n;i++){
            int l = i + 1;
            int r = n;
            int t = target - numbers[i];
            while (l < r) {
                int m = l + (r - l) / 2;
                if (numbers[m] == t)
                    return {i + 1, m + 1};
                else if (numbers[m] < t)
                    l = m + 1;
                else
                    r = m;
            }
        }
        return {};
    }
};