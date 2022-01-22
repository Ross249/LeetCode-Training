// 子集问题
// leetcode-78
var subsets = function(nums) {
	const temp = [];
	const res = [];
	const len = nums.length;
	const dfs = (cur) => {
		if(cur === len){
			res.push(temp.slice());
			return;
		}
		temp.push(nums[cur]);
		dfs(cur + 1,nums);
		temp.pop(temp.length - 1);
		dfs(cur + 1,nums);
	}
	dfs(0,nums);
	return res;
};