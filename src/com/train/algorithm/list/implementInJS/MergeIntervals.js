// 合并区间
// leetcode-56
var merge = function(intervals) {
	intervals.sort((a, b) => a[0] - b[0]);
	let pre = intervals[0];
	let res = [];
	for(let i = 0;i < intervals.length;i++){
		let cur = intervals[i];
		if(cur[0] > pre[1]){
			res.push(pre);
			pre = cur;
		}else{
			pre[1] = Math.max(pre[1],cur[1]);
		}
	}
	res.push(pre);
	return res;
};