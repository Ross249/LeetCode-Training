// 最长子字符串系列
// leetcode-1143
var longestCommonSubsequence = function (text1, text2) {
  const m = text1.length,
    n = text2.length;
  const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
  for (let i = 1; i <= m; i++) {
    const c1 = text1[i - 1];
    for (let j = 1; j <= n; j++) {
      const c2 = text2[j - 1];
      if (c1 === c2) {
        dp[i][j] = dp[i - 1][j - 1] + 1;
      } else {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }
  return dp[m][n];
};

// leetcode-300
var lengthOfLIS = function (nums) {
  if (nums.length === 0) {
    return 0;
  }
  const dp = new Array(nums.length).fill(1);
  let res = 0;
  for (let i = 0; i < nums.length; i++) {
    for (let j = 0; j < i; j++) {
      if (nums[j] < nums[i]) {
        dp[i] = Math.max(dp[i], dp[j] + 1);
      }
    }
    res = Math.max(dp[i], res);
  }
  return res;
};

// 剑指offer-42
var maxSubArray = function (nums) {
  let res = nums[0];
  for (let i = 1; i < nums.length; i++) {
    nums[i] += Math.max(nums[i - 1], 0);
    res = Math.max(res, nums[i]);
  }
  return res;
};

// leetcode-801
var minSwap = function (nums1, nums2) {
  let n1 = 0,
    s1 = 1,
    len = nums1.length;
  for (let i = 1; i < len; i++) {
    let n2 = Number.MAX_VALUE,
      s2 = Number.MAX_VALUE;
    if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
      n2 = Math.min(n2, n1);
      s2 = Math.min(s2, s1 + 1);
    }
    if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
      n2 = Math.min(n2, s1);
      s2 = Math.min(s2, n1 + 1);
    }
    n1 = n2;
    s1 = s2;
  }
  return Math.min(n1, s1);
};
