// 快慢指针

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
function deliverSum(n) {
	return n.toString().split('').map(i => i ** 2).reduce((a,b)=>a+b);
}
var isHappy = function(n) {
	let fast = n,slow = n;
	while(slow != fast && fast != 1){
		slow = deliverSum(slow);
		fast = deliverSum(deliverSum(fast));
	}
	return slow == 1;
};

// leetcode-876
var middleNode = function(head) {
	// if(head == null || head.next == null){
	// 	return head;
	// }
	let a = head,b = head;
	while(b != null && b.next != null){
		a = a.next;
		b = b.next.next;
	}
	return a;
};