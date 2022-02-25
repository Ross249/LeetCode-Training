// 斐波那契数列
// leetcode-509
var fib = function (n) {
  if (n === 0) return 0;
  if (n === 1) return 1;
  return fib(n - 1) + fib(n - 2);
};

// leetcode-70
var climbStairs = function (n) {
  let p = 0,
    q = 0,
    r = 1;
  for (let i = 0; i < n; i++) {
    p = q;
    q = r;
    r = p + q;
  }
  return r;
};

// 剑指offer-10
var fib = function (n) {
  const MOD = 1000000007;
  if (n < 2) {
    return n;
  }
  let num1 = 0,
    num2 = 0;
  let res = 1;
  for (let i = 2; i <= n; i++) {
    num1 = num2;
    num2 = res;
    res = (num1 + num2) % MOD;
  }
  return res;
};

// leetcode-198
var rob = function (nums) {
  if (nums === null || nums.length === 0) {
    return 0;
  }
  let len = nums.length;
  if (len === 1) {
    return nums[0];
  }
  let dp = new Array(len);
  dp[0] = nums[0];
  dp[1] = Math.max(nums[0], nums[1]);
  for (let i = 2; i < len; i++) {
    dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
  }
  return dp[len - 1];
};
