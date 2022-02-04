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

// leetcode-103
var zigzagLevelOrder = function (root) {
  if (!root) {
    return [];
  }
  const res = [];
  const queue = [root];
  let isOrder = true;
  while (queue.length) {
    let cur = [];
    const size = queue.length;
    for (let i = 0; i < size; i++) {
      const node = queue.shift();
      if (isOrder) {
        cur.push(node.val);
      } else {
        cur.unshift(node.val);
      }
      node.left && queue.push(node.left);
      node.right && queue.push(node.right);
    }
    res.push(cur);
    isOrder = !isOrder;
  }
  return res;
};
