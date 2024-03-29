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

// leetcode-1027
var longestArithSeqLength = function (nums) {
  let len = nums.length;
  let dp = [];
  for (let i = 0; i < len; i++) {
    dp[i] = [];
    for (let j = 0; j < len; j++) {
      dp[i][j] = 2;
    }
  }
  let map = new Map();
  let max = 0;
  for (let i = 0; i < len; i++) {
    for (let j = i + 1; j < len; j++) {
      let target = 2 * nums[i] - nums[j];
      if (map.has(target)) {
        dp[i][j] = dp[map.get(target)][i] + 1;
      }
      max = Math.max(dp[i][j], max);
    }
    map.set(nums[i], i);
  }
  return max;
};

// leetcode-72
var minDistance = function (word1, word2) {
  let dp = new Array(word1.length + 1)
    .fill(0)
    .map(() => new Array(word2.length + 1).fill(0));
  for (let i = 1; i <= word1.length; i++) {
    dp[i][0] = i;
  }
  for (let j = 1; j <= word2.length; j++) {
    dp[0][j] = j;
  }
  for (let i = 1; i <= word1.length; i++) {
    for (let j = 1; j <= word2.length; j++) {
      if (word1[i - 1] === word2[j - 1]) {
        dp[i][j] = dp[i - 1][j - 1];
      } else {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
      }
    }
  }
  return dp[word1.length][word2.length];
};

// leetcode-97
var isInterleave = function (s1, s2, s3) {
  if (s1.length + s2.length !== s3.length) {
    return false;
  }
  const memo = new Array(s1.length + 1);
  for (let i = 0; i < memo.length; i++) {
    memo[i] = new Array(s2.length + 1);
  }
  const check = (i, j, k) => {
    if (memo[i][j] != null) {
      return memo[i][j];
    }
    if (k === s3.length) {
      return true;
    }
    let res = false;
    if (i < s1.length && s1[i] === s3[k]) {
      res = check(i + 1, j, k + 1);
    }
    if (j < s2.length && s2[j] === s3[k]) {
      res = res || check(i, j + 1, k + 1);
    }
    return (memo[i][j] = res);
  };
  return check(0, 0, 0);
};
