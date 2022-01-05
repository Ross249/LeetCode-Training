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

};