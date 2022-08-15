//
// Created by 罗斯 on 15/8/2022.
//
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
//        if(head == nullptr || head -> next == nullptr) return nullptr;
//
//        ListNode *slow = head;
//        ListNode *fast = head;
//        ListNode *entry = head;
//
//        while (fast -> next && fast ->next ->next){
//            slow = slow -> next;
//            fast = fast -> next -> next;
//            if(slow == fast){
//                while (slow != entry){
//                    slow = slow -> next;
//                    entry = entry -> next;
//                }
//                return entry;
//            }
//        }
//        return nullptr;

        if (head == NULL || head->next == NULL) return NULL;

        ListNode* firstp = head;
        ListNode* secondp = head;
        bool isCycle = false;

        while(firstp != NULL && secondp != NULL) {
            firstp = firstp->next;
            if (secondp->next == NULL) return NULL;
            secondp = secondp->next->next;
            if (firstp == secondp) { isCycle = true; break; }
        }

        if(!isCycle) return NULL;
        firstp = head;
        while( firstp != secondp) {
            firstp = firstp->next;
            secondp = secondp->next;
        }

        return firstp;
    }
};