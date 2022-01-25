// 多路归并
// leetcode-23
var mergeKLists = function(lists) {
	if(!lists.length){
		return null;
	}
	if(lists.length === 1){
		return lists[0];
	}
	lists.splice(0,2,mergeList(lists[0],lists[1]));
	return mergeKLists(lists);
};

function mergeList(l1,l2){
	let head = new ListNode(),pre = head;
	while(l1 && l2){
		if(l1.val > l2.val){
			pre.next = l2;
			l2 = l2.next;
		}else{
			pre.next = l1;
			l1 = l1.next;
		}
		pre = pre.next;
	}
	pre.next = l1 ? l1 : l2;
	return head.next;
}