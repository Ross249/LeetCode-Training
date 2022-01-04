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

// leetcode-713
var numSubarrayProductLessThanK = function(nums, k) {
	if(k <= 1){
		return 0;
	}
	let left = 0,count = 0,start = 1;
	for(let i = 0;i < nums.length;i++){
		start *= nums[i];
		while(start >= k){
			start /= nums[left];
			left++;
		}
		count += i - left + 1;
	}
	return count;
};

// 面试题16.06.
var smallestDifference = function(a, b) {
	a.sort((a,b) => a - b);
	b.sort((a,b) => a - b);
	let n = a.length,m = b.length;
	let i = 0,j = 0;
	let res = Number.MAX_VALUE;
	while(i < n && j < m){
		let tmp = a[i] - b[j];
		res = Math.min(res,Math.abs(tmp));
		if(tmp < 0){
			i++;
		}else{
			j++;
		}
	}
	return res;
};