//
// Created by 罗斯 on 10/8/2022.
//
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode dummy(0);
        ListNode *tail = &dummy;
        int sum = 0;
        while(l1 || l2 || sum){
            sum += (l1 ? l1->val : 0) + (l2 ? l2->val : 0);
            l1 = l1 ? l1 -> next : nullptr;
            l2 = l2 ? l2 -> next : nullptr;
            tail -> next = new ListNode(sum % 10);
            tail = tail -> next;
            sum /= 10;
        }
        return dummy.next;
    }
};