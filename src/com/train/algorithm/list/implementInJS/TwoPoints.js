// 双指针
// leetcode-剑指offer-57
var twoSum = function(nums, target) {
	let left = 0,right = nums.length - 1;
	while(left <= right){
		if(nums[left] + nums[right] == target){
			return [nums[left],nums[right]];
		}else if(nums[left] + nums[right] > target){
			right--;
		}else{
			left++;
		}
	}
	return null;
};

// 面试题02.01.
var removeDuplicateNodes = function(head) {
	let cur = head;
	while(cur != null){
		let next = cur;
		while(next.next != null){
			if(next.next.val == cur.val){
				next.next = next.next.next;
			}else{
				next = next.next;
			}
		}
		cur = cur.next;
	}
	return head;
};

// leetcode-977
var sortedSquares = function(nums) {
	let res = [];
	for(let i = 0,j = nums.length - 1;i <= j;){
		const left = Math.abs(nums[i]);
		const right = Math.abs(nums[j]);
		if(right > left){
			res.unshift(right * right);
			j--;
		}else{
			res.unshift(left * left);
			i++;
		}
	}
	return res;
}