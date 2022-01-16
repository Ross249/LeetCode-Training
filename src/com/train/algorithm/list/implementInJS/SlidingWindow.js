// 滑动窗口
// leetcode-1663
var getSmallestString = function(n, k) {
	let res = Array(n).fill('a'),remain = k - n,i = n - 1;
	while(remain){
		if(remain > 25){
			remain -= 25;
			res[i] = 'z';
			i--;
		}else{
			res[i] = String.fromCharCode(97 + remain);
			remain = 0;
		}

	}
	return res.join('');
};

// leetcode-904
var totalFruit = function(fruits) {
	let l = 0,maxLen = 0,n = 0;
	let arr = [fruits[l]];

	for(let r = 0;r < fruits.length;r++){
		if(!arr.includes(fruits[r])){
			if(arr.length <= 1){
				arr[1] = fruits[r];
			}else{
				l = n;
				arr[0] = fruits[l];
				arr[1] = fruits[r];
			}
		}
		if(fruits[r] != fruits[n]){
			n = r;
		}
		maxLen = Math.max(maxLen,r - l + 1);
	}


	return maxLen;
};

// leetcode-3
var lengthOfLongestSubstring = function(s) {
	const o = new Set();
	const len = s.length;
	let right = -1,res = 0;
	for(let i = 0;i < len;i++){
		if(i != 0){
			o.delete(s.charAt(i - 1))
		}
		while(right + 1 < len && !o.has(s.charAt(right + 1))){
			o.add(s.charAt(right + 1));
			right++;
		}
		res = Math.max(res,right - i + 1);
	}
	return res;
};