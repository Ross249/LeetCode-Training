//
// Created by 罗斯 on 16/8/2022.
//
#include <queue>
#include <vector>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
//        ListNode dummy(0);
//        ListNode *tail = &dummy;
//
//        auto com = [](ListNode *a ,ListNode *b) {return a -> val > b ->val;};
//        priority_queue<ListNode*,vector<ListNode*>, decltype(com)> q(com);
//
//        for(ListNode* list : lists)
//            if(list) q.push(list);
//
//        while(!q.empty()){
//            tail -> next = q.top();
//            q.pop();
//            tail = tail -> next;
//            if(tail -> next) q.push(tail->next);
//        }
//        return dummy.next;
        return merge(lists, 0, lists.size() - 1);
    }
private:
    ListNode* merge(vector<ListNode*>& lists, int l, int r) {
        if (l > r) return nullptr;
        if (l == r) return lists[l];
        if (l + 1 == r) return mergeTwoLists(lists[l], lists[r]);
        int m = l + (r - l) / 2;
        auto l1 = merge(lists, l, m);
        auto l2 = merge(lists, m + 1, r);
        return mergeTwoLists(l1, l2);
    }
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy(0);
        ListNode* tail = &dummy;
        while (l1 && l2) {
            if (l1->val > l2->val) swap(l1, l2);
            tail->next = l1;
            l1 = l1->next;
            tail = tail->next;
        }
        if (l1) tail->next = l1;
        if (l2) tail->next = l2;
        return dummy.next;
    }
};