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

//leetcode-46
var permute = function(nums) {
	const temp = [],res = [];
	const len = nums.length;
	dfs(nums,len,[]);
	return res;


	function dfs(n,k,used){
		if(temp.length === k){
			res.push(temp.slice());
			return;
		}
		for(let i = 0;i < k;i++){
			if(used[i]){
				continue;
			}
			temp.push(n[i]);
			used[i] = true;
			dfs(n,k,used);
			temp.pop();
			used[i] = false;
		}
	}
			
};

// leetcode-784
var letterCasePermutation = function(s) {
	const ans = [], n = s.length;   
	function dfs(index, tmp) {
		if (index === n) return ans.push(tmp);
		if (isNaN(s[index])) {
			dfs(index + 1, tmp + s[index].toLowerCase());
			dfs(index + 1, tmp + s[index].toUpperCase());
		} else {
			dfs(index + 1, tmp + s[index]);
		}
	}
	dfs(0, '');
	return ans;
};