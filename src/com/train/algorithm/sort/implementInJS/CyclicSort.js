// 循环排序
// leetcode-448
var findDisappearedNumbers = function (nums) {
  const n = nums.length;
  for (const num of nums) {
    const x = (num - 1) % n;
    nums[x] = nums[x] + n;
  }
  const res = [];
  for (const [i, num] of nums.entries()) {
    if (num <= n) {
      res.push(i + 1);
    }
  }
  return res;
};
// leetcode-287
var findDuplicate = function (nums) {
  const n = nums.length;
  let l = 1,
    r = n - 1,
    res = -1;
  while (l <= r) {
    let mid = (r + l) >> 1;
    let cnt = 0;
    for (let i = 0; i < n; i++) {
      cnt += nums[i] <= mid;
    }
    if (cnt <= mid) {
      l = mid + 1;
    } else {
      r = mid - 1;
      res = mid;
    }
  }
  return res;
};
