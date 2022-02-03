// 树的遍历
// leetcode-107
var levelOrderBottom = function (root) {
  let res = [],
    queue = [];
  queue.push(root);
  while (queue.length && root != null) {
    let cur = [];
    let len = queue.length;
    while (len--) {
      let node = queue.shift();
      cur.push(node.val);
      node.left && queue.push(node.left);
      node.right && queue.push(node.right);
    }
    res.push(cur);
  }
  return res.reverse();
};
