//
// Created by 罗斯 on 19/8/2022.
//
#include <utility>
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
    ListNode* sortList(ListNode* head) {
        if(!head || !head-> next) return head;

        int len = 1;
        ListNode *cur = head;
        while(cur = cur -> next) ++len;

        ListNode dummy(0);
        dummy.next = head;
        ListNode *l;
        ListNode *r;
        ListNode *tail;

        for(int n = 1 ; n < len; n<<=1){
            cur = dummy.next;
            tail = &dummy;

            while (cur){
                l = cur;
                r = split(l,n);
                cur = split(r,n);
                auto merged = merge(l,r);
                tail -> next = merged.first;
                tail = merged.second;
            }
        }
        return dummy.next;
    }
private:
    ListNode *split(ListNode *head,int n){
        while (--n && head) head = head ->next;
        ListNode *rest = head ? head -> next : nullptr;
        if(head) head ->next = nullptr;
        return rest;
    }

    pair<ListNode*,ListNode*> merge(ListNode *l1,ListNode *l2){
        ListNode dummy(0);
        ListNode *tail = &dummy;
        while (l1 && l2){
            if(l1 -> val > l2 -> val) swap(l1,l2);
            tail -> next = l1;
            l1 = l1 ->next;
            tail = tail -> next;
        }
        tail -> next = l1 ? l1 : l2;
        while (tail -> next) tail = tail -> next;
        return {dummy.next,tail};
    }
};