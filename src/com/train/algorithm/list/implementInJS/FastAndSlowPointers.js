// 快慢指针
// 列表结构
function ListNode(val){
	this.val = val;
	this.next = null;
}

// leetcode-141
var hasCycle = function(head) {
	if (head == null || head.next == null){
		return false;
	}
	let a = head,b = head.next;
	while (a != b){
		if(b == null || b.next == null){
			return false;
		}
		a = a.next;
		b = b.next.next;
	}
	return true;

};

// leetcode-202
var isHappy = function(n) {
	
};