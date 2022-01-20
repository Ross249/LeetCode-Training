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

// leetcode-57
var insert = function(intervals, newInterval) {
	const res = [];
	let i = 0;
	const len = intervals.length;
	while(i < len && intervals[i][1] < newInterval[0]){
		res.push(intervals[i]);
		i++;
	}
	while(i < len && intervals[i][0] <= newInterval[1]){
		newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
		newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
		i++;
	}
	res.push(newInterval);
	while(i < len){
		res.push(intervals[i]);
		i++;
	}
	return res;
};

// leetcode-986
var intervalIntersection = function(firstList, secondList) {
	const res = [];
	let i = 0 ,j = 0;
	while(i < firstList.length && j < secondList.length){
		const start = Math.max(firstList[i][0],secondList[j][0]);
		const end = Math.min(firstList[i][1],secondList[j][1]);
		if(start <= end){
			res.push([start,end]);
		}
		if(firstList[i][1] < secondList[j][1]){
			i++;
		}else{
			j++;
		}
	}
	return res;
};