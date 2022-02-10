// 树的遍历(非递归)
// 前序遍历:
// 入栈 右 -> 左
// 出栈 中 -> 左 -> 右
var preorderTraversal = function (root, res = []) {
  if (!root) return res;
  const stack = [root];
  let cur = null;
  while (stack.length) {
    cur = stack.pop();
    res.push(cur.val);
    cur.right && stack.push(cur.right);
    cur.left && stack.push(cur.left);
  }
  return res;
};

// 中序遍历:
// 入栈 左 -> 右
// 出栈 左 -> 中 -> 右
var inorderTraversal = function (root, res = []) {
  const stack = [];
  let cur = root;
  while (stack.length || cur) {
    if (cur) {
      stack.push(cur);
      // 左
      cur = cur.left;
    } else {
      // --> 弹出 中
      cur = stack.pop();
      res.push(cur.val);
      // 右
      cur = cur.right;
    }
  }
  return res;
};

// 后序遍历:
// 入栈 左 -> 右
// 出栈 中 -> 右 -> 左 结果翻转
var postorderTraversal = function (root, res = []) {
  if (!root) return res;
  const stack = [root];
  let cur = null;
  do {
    cur = stack.pop();
    res.push(cur.val);
    cur.left && stack.push(cur.left);
    cur.right && stack.push(cur.right);
  } while (stack.length);
  return res.reverse();
};
