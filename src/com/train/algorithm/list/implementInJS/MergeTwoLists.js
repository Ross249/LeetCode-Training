// 递归

// 剑指-25
var mergeTwoLists = function(l1, l2) {
	if(l1 === null){
		return l2;
	}
	if(l2 === null){
		return l1;
	}
	let head = null;
	if(l1.val < l2.val){
		head = l1;
		head.next = mergeTwoLists(l1.next, l2);
	}else{
		head = l2;
		head.next = mergeTwoLists(l1, l2.next);
	}
	return head;
};

// 剑指-10
var fib = function(n) {
	const MOD = 1000000007;
	if(n < 2){
		return n;
	}
	let num1 = 0,num2 = 0;
	let res = 1;
	for(let i = 2;i <= n;i++){
		num1 = num2;
		num2 = res;
		res = (num1 + num2) % MOD;
	}
	return res;
};