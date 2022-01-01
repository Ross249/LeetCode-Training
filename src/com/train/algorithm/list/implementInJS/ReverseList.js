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