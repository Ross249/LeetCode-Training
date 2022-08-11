//
// Created by 罗斯 on 11/8/2022.
//
#include <iostream>
#include <stack>
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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        stack<int> s1;
        stack<int> s2;
        while(l1){
            s1.push(l1 -> val);
            l1 = l1 -> next;
        }

        while (l2){
            s2.push(l2 -> val);
            l2 = l2 -> next;
        }

        ListNode* head = nullptr;
        int sum = 0;
        while (!s2.empty() || !s1.empty() || sum){
            sum += s1.empty() ? 0 : s1.top();
            sum += s2.empty() ? 0 : s2.top();
            if(!s1.empty()) s1.pop();
            if(!s2.empty()) s2.pop();
            ListNode* n = new ListNode(sum % 10);
            sum /= 10;
            n -> next = head;
            head = n;
        }
        return head;

//        int n1 = 0, n2 = 0, carry = 0;
//        ListNode *curr1 = l1, *curr2 = l2, *res = NULL;
//        while( curr1 ){ curr1=curr1->next; n1++; }
//        while( curr2 ){ curr2=curr2->next; n2++; }
//        curr1 = l1; curr2 = l2;
//        while( n1 > 0 && n2 > 0){
//            int sum = 0;
//            if( n1 >= n2 ){ sum += curr1->val; curr1=curr1->next; n1--;}
//            if( n2 > n1 ){ sum += curr2->val; curr2=curr2->next; n2--;}
//            res = addToFront( sum, res );
//        }
//        curr1 = res; res = NULL;
//        while( curr1 ){
//            curr1->val += carry; carry = curr1->val/10;
//            res = addToFront( curr1->val%10, res );
//            curr2 = curr1;
//            curr1 = curr1->next;
//            delete curr2;
//        }
//        if( carry ) res = addToFront( 1, res );
//        return res;
    }

//    ListNode* addToFront( int val, ListNode* head ){
//        ListNode* temp = new ListNode(val);
//        temp->next = head;
//        return temp;
//    }
};