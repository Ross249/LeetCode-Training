//
// Created by 罗斯 on 18/11/2022.
//
#include <cstdlib>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>

using namespace std;

class Solution {
public:
    vector<int> sortArray(vector<int>& nums) {
        // quick sort
        function<void(int, int)> quickSort = [&](int l, int r) {
            if (l >= r) return;
            int i = l;
            int j = r;
            int p = nums[l + rand() % (r - l + 1)];
            while (i <= j) {
                while (nums[i] < p) ++i;
                while (nums[j] > p) --j;
                if (i <= j)
                    swap(nums[i++], nums[j--]);
            }
            quickSort(l, j);
            quickSort(i, r);
        };
        quickSort(0, nums.size() - 1);
        return nums;
    }
private:
    vector<int> countingSort(vector<int>& nums) {
        auto [min_it, max_it] = minmax_element(begin(nums), end(nums));
        int l = *min_it, r = *max_it;
        vector<int> count(r - l + 1);
        for (int n : nums) ++count[n - l];
        int index = 0;
        for (int i = 0; i < count.size(); ++i)
            while (count[i]--) nums[index++] = i + l;
        return nums;
    }

    vector<int> HeapSort(vector<int>& nums) {
        priority_queue<int> q(begin(nums), end(nums));
        int i = nums.size();
        while (!q.empty()) {
            nums[--i] = q.top();
            q.pop();
        }
        return nums;

    }

    vector<int> HeapSort1(vector<int>& nums) {
        auto heapify = [&](int i, int e) {
            while (i <= e) {
                int l = 2 * i + 1;
                int r = 2 * i + 2;
                int j = i;
                if (l <= e && nums[l] > nums[j]) j = l;
                if (r <= e && nums[r] > nums[j]) j = r;
                if (j == i) break;
                swap(nums[i], nums[j]);
                swap(i, j);
            }
        };

        const int n = nums.size();

        // Init heap
        for (int i = n / 2; i >= 0; i--)
            heapify(i, n - 1);

        // Get min.
        for (int i = n - 1; i >= 1; i--) {
            swap(nums[0], nums[i]);
            heapify(0, i - 1);
        }

        return nums;
    }

    vector<int> mSort(vector<int>& nums) {
        vector<int> t(nums.size());
        function<void(int, int)> mergeSort = [&](int l, int r) {
            if (l + 1 >= r) return;
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m, r);
            int i1 = l;
            int i2 = m;
            int index = 0;
            while (i1 < m || i2 < r)
                if (i2 == r || (i1 < m && nums[i1] < nums[i2]))
                    t[index++] = nums[i1++];
                else
                    t[index++] = nums[i2++];
            copy(begin(t), begin(t) + index, begin(nums) + l);
        };

        mergeSort(0, nums.size());
        return nums;
    }

    vector<int> BST(vector<int>& nums) {
        multiset<int> s(begin(nums), end(nums));
        return {begin(s), end(s)};
    }
};