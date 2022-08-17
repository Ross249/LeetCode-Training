//
// Created by 罗斯 on 17/8/2022.
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
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
//        ListNode dummy(0);
//        ListNode *tail = &dummy;
//
//        while(list1 && list2){
//            if(list1 -> val < list2 -> val){
//                tail -> next = list1;
//                list1 = list1 -> next;
//            }else{
//                tail -> next = list2;
//                list2 = list2 -> next;
//            }
//            tail = tail -> next;
//        }
//
//        if(list1) tail -> next = list1;
//        if(list2) tail -> next = list2;
//
//        return dummy.next;

        if(!list1 || !list2) return list1 ? list1 : list2;

        if(list1->val < list2->val) {
            list1->next = mergeTwoLists(list1->next, list2);
            return list1;
        } else {
            list2->next = mergeTwoLists(list1, list2->next);
            return list2;
        }
    }
};