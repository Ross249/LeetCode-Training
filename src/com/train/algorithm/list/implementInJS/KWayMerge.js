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

// leetcode-378
const countMatrix = (matrix,midVal) => {
	const n = matrix.length;
	let count = 0;
	let row = 0;
	let col = n-1;
	while(row < n && col >= 0){
		if(midVal >= matrix[row][col]){
			count = count + col + 1;
			row++;
		}else{
			col--;
		}
	}
	return count;
}
var kthSmallest = function(matrix, k) {
	const n = matrix.length;
	let low = matrix[0][0];
	let high = matrix[n-1][n-1];
	while(low <= high){
		let midVal = low + ((high - low) >>> 1);
		let count = countMatrix(matrix,midVal);
		if(count < k){
			low = midVal + 1;
		}else{
			high = midVal - 1;
		}
	}
	return low;
};