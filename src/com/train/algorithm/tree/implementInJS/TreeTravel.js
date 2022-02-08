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

// leetcode-637
var averageOfLevels = function (root) {
  let res = [],
    queue = [];
  queue.push(root);
  if (root == null) {
    return res;
  }
  while (queue.length) {
    let length = queue.length;
    let sum = 0;
    for (let i = 0; i < length; i++) {
      let node = queue.shift();
      sum += node.val;
      node.left && queue.push(node.left);
      node.right && queue.push(node.right);
    }
    res.push(sum / length);
  }
  return res;
};

// leetcode-111
var minDepth = function (root) {
  if (root == null) {
    return 0;
  }
  if (root.left == null && root.right == null) {
    return 1;
  }
  if (root.left == null) {
    return minDepth(root.right) + 1;
  }
  if (root.right == null) {
    return minDepth(root.left) + 1;
  }
  return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
};

// leetcode-112
var hasPathSum = function (root, targetSum) {
  const travel = (node, sum) => {
    if (sum == 0 && !node.left && !node.right) {
      return true;
    }
    if (!node.left && !node.right) {
      return false;
    }
    if (node.left && travel(node.left, sum - node.left.val)) {
      return true;
    }
    if (node.right && travel(node.right, sum - node.right.val)) {
      return true;
    }
    return false;
  };
  if (!root) {
    return false;
  }
  return travel(root, targetSum - root.val);
};

// leetcode-113
var pathSum = function (root, targetSum) {
  let res = [],
    cur = [];
  const travel = (node, sum) => {
    cur.push(node.val);
    sum -= node.val;
    if (node.left === null && node.right === null && sum === 0) {
      res.push(cur.slice());
    }
    node.left && travel(node.left, sum);
    node.right && travel(node.right, sum);
    let c = cur.pop();
    sum -= c;
  };
  if (root === null) {
    return res;
  }
  travel(root, targetSum);
  return res;
};
