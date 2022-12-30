//
// Created by 罗斯 on 30/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;
class Solution {
public:
    int makeArrayIncreasing(vector<int>& arr1, vector<int>& arr2) {
        constexpr int kInf = 1e9;
        int m = arr1.size();
        sort(begin(arr2), end(arr2));
        vector<int> b;
        for (int i = 0; i < arr2.size(); ++i) {
            if (!b.empty() && arr2[i] == b.back()) continue;
            b.push_back(arr2[i]);
        }

        int n = b.size();

        vector<int> keep(m, kInf);
        keep[0] = 0;
        vector<int> swap(n, 1);

        for (int i = 1; i < m; ++i) {
            int min_keep = kInf;
            int min_swap = kInf;
            vector<int> temp(n, kInf);
            for (int j = 0; j < n; ++j) {
                if (j > 0) min_swap = min(min_swap, swap[j - 1] + 1);
                if (arr1[i] > b[j]) min_keep = min(min_keep, swap[j]);
                if (arr1[i] > arr1[i - 1]) keep[i] = keep[i - 1];
                if (b[j] > arr1[i - 1]) temp[j] = keep[i - 1] + 1;
                temp[j] = min(min_swap, temp[j]);
                keep[i] = min(min_keep, keep[i]);
            }
            temp.swap(swap);
        }

        int s = *min_element(begin(swap), end(swap));
        int k = keep.back();
        int res = min(s, k);
        return res >= kInf ? -1 : res;
    }
};