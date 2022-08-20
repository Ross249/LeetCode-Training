//
// Created by 罗斯 on 20/8/2022.
//
class MyLinkedList {
public:
    MyLinkedList(): head_(nullptr),tail_(nullptr),size_(0){}

    ~MyLinkedList(){
        Node *node = head_;
        while (node){
            Node *cur = node;
            node = node -> next;
            delete cur;
        }
        head_ = nullptr;
        tail_ = nullptr;
    }

    int get(int index) {
        if(index < 0 || index >= size_) return -1;
        auto node = head_;
        while (index--){
            node = node -> next;
        }
        return node -> val;
    }

    void addAtHead(int val) {
        head_ = new Node(val,head_);
        if(size_++ == 0) tail_ = head_;
    }

    void addAtTail(int val) {
        auto node = new Node(val);
        if(size_++ == 0){
            head_ = tail_ = node;
        }else{
            tail_ -> next = node;
            tail_ = tail_ -> next;
        }
    }

    void addAtIndex(int index, int val) {
        if(index < 0 || index > size_) return;
        if(index == 0) return addAtHead(val);
        if(index == size_) return addAtTail(val);
        Node dummy(0,head_);
        Node *prev = &dummy;
        while (index--) prev = prev ->next;
        prev ->next = new Node(val,prev -> next);
        ++size_;
    }

    void deleteAtIndex(int index) {
        if(index < 0 || index >= size_) return;
        Node dummy(0,head_);
        Node *prev = &dummy;
        for(int i = 0;i < index;i++) prev = prev -> next;
        Node *cur = prev -> next;
        prev -> next = prev -> next -> next;
        if(index == 0) head_ = prev -> next;
        if(index == size_ - 1) tail_ = prev;
        delete cur;
        --size_;
    }
private:
    struct Node{
        int val;
        Node *next;
        Node(int _val): Node(_val, nullptr){}
        Node(int _val,Node *_next): val(_val),next(_next){}
    };
    Node *head_;
    Node *tail_;
    int size_;
};