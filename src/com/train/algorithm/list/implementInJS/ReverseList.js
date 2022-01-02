// 反转链表
// 剑指offer 24
var reverseList = function(head) {
	if(head == null || head.next == null){
		return head;
	}
	let pre = null;
	let cur = head;
	while(cur != null){
		const next = cur.next;
		cur.next = pre;
		pre = cur;
		cur = next;
	}
	return pre;
};

// 剑指offer 24
var reverseList = function(head) {
	if(head == null || head.next == null){
		return head;
	}
	const newHead = reverseList(head.next);
	head.next.next = head;
	head.next = null;
	return newHead;
};

// leetcode-25
const reverse = function(head,tail){
	let pre = tail.next;
	let p = head;
	while(pre != tail){
		const temp = p.next;
		p.next = pre;
		pre = p;
		p = temp;
	}
	return [tail,head];
}
var reverseKGroup = function(head, k) {
	const h = new ListNode(0);
	h.next = head;
	let pre = h;
	while(head!=null){
		let tail = pre;
		for(let i = 0;i < k;i++){
			tail = tail.next;
			if(tail == null){
				return h.next;
			}
		}
		let nex = tail.next;
		[head,tail] = reverse(head,tail);
		pre.next = head;
		tail.next = nex;
		pre = tail;
		head = tail.next;
	}
	return h.next;
};