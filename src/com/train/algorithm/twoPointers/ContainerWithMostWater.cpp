//
// Created by 罗斯 on 21/8/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    int maxArea(vector<int>& height) {
        int water = 0;
        int i = 0,j = height.size() - 1;
        while (i < j){
            int h = min(height[i],height[j]);
            water = max(water,(j-i) * h);
            while (height[i] <= h && i < j) i++;
            while (height[j] <= h && i < j) j--;
        }
        return water;
    }
};