// 回文子系列
// leetcode-516
var longestPalindromeSubseq = function (s) {
  const n = s.length;
  const dp = new Array(n).fill(0).map(() => new Array(n).fill(0));
  for (let i = n - 1; i >= 0; i--) {
    dp[i][i] = 1;
    const c1 = s[i];
    for (let j = i + 1; j < n; j++) {
      const c2 = s[j];
      if (c1 === c2) {
        dp[i][j] = dp[i + 1][j - 1] + 2;
      } else {
        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
      }
    }
  }
  return dp[0][n - 1];
};

// leetcode-5
var longestPalindrome = function (s) {
  let res = "";
  for (let i = 0; i < s.length; i++) {
    const s1 = palindrome(s, i, i);
    const s2 = palindrome(s, i, i + 1);
    res = res.length > s1.length ? res : s1;
    res = res.length > s2.length ? res : s2;
  }
  return res;
};
function palindrome(s, l, r) {
  while (l >= 0 && r < s.length && s[l] === s[r]) {
    l--;
    r++;
  }
  return s.substr(l + 1, r - l - 1);
}

// leetcode-1312
var minInsertions = function (s) {
  const n = s.length;
  const dp = new Array(n).fill(0).map(() => new Array(n).fill(0));
  for (let j = 1; j < n; j++) {
    dp[j - 1][j] = s[j - 1] === s[j] ? 0 : 1;
    for (let i = j - 2; i >= 0; i--) {
      dp[i][j] =
        s[i] === s[j]
          ? dp[i + 1][j - 1]
          : Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
    }
  }
  return dp[0][n - 1];
};
