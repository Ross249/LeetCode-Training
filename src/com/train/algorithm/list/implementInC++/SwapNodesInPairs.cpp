//
// Created by 罗斯 on 12/8/2022.
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
    ListNode* swapPairs(ListNode* head) {
//        if(!head || !head->next) return head;
//
//        ListNode d(0);
//        d.next = head;
//        head = &d;
//
//        while (head && head->next && head->next->next){
//            auto n1 = head->next;
//            auto n2 = n1->next;
//
//            n1-> next = n2 ->next;
//            n2-> next = n1;
//
//            head->next = n2;
//            head = n1;
//        }
//        return d.next;

        if(head == NULL || head -> next == NULL){
            return head;
        }

        ListNode* temp;
        temp = head->next;

        head->next = swapPairs(head->next->next);
        temp->next = head;

        return temp;
    }
};