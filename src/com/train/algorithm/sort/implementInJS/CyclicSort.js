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
