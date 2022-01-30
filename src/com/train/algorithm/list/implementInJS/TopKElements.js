// 最K个系列
// 剑指offer-40
function partition(arr, start, end) {
  const k = arr[start];
  let left = start + 1;
  let right = end;
  while (1) {
    while (left <= right && arr[left] <= k) {
      ++left;
    }
    while (right >= start + 1 && arr[right] >= k) {
      --right;
    }
    if (left >= right) {
      break;
    }
    [arr[left], arr[right]] = [arr[right], arr[left]];
    ++left;
    --right;
  }
  [arr[right], arr[start]] = [arr[start], arr[right]];
  return right;
}

var getLeastNumbers = function (arr, k) {
  const len = arr.length;
  if (k >= len) {
    return arr;
  }
  let left = 0,
    right = len - 1;
  let index = partition(arr, left, right);
  while (index !== k) {
    if (index < k) {
      left = index + 1;
      index = partition(arr, left, right);
    } else if (index > k) {
      right = index - 1;
      index = partition(arr, left, right);
    }
  }
  return arr.slice(0, k);
};

// leetcode-973
var kClosest = function (points, k) {
  if (points.length <= k) {
    return points;
  }
  quickSelect(points, 0, points.length - 1, k);
  return points.slice(0, k);
};

function quickSelect(points, start, end, k) {
  const piv = distance(points[start]);
  let l = start,
    r = end;
  while (l <= r) {
    if (distance(points[l]) <= piv) {
      l++;
      continue;
    }
    if (distance(points[r]) > piv) {
      r--;
      continue;
    }
    [points[l], points[r]] = [points[r], points[l]];
    l++;
    r--;
  }
  [points[start], points[r]] = [points[r], points[start]];
  if (r == k) {
    return;
  } else if (r < k) {
    quickSelect(points, r + 1, end, k);
  } else {
    quickSelect(points, start, r - 1, k);
  }
}

function distance(point) {
  return point[0] * point[0] + point[1] * point[1];
}

// leetcode-347
var topKFrequent = function (nums, k) {
  const map = new Map();

  for (const num of nums) {
    map.set(num, (map.get(num) || 0) + 1);
  }

  const priorQueue = new PriorityQueue((a, b) => a[1] - b[1]);

  for (const entry of map.entries()) {
    priorQueue.push(entry);
    if (priorQueue.size() > k) {
      priorQueue.pop();
    }
  }

  const res = [];

  for (let i = priorQueue.size() - 1; i >= 0; i--) {
    res[i] = priorQueue.pop()[0];
  }

  return res;
};

function PriorityQueue(compareFn) {
  this.compareFn = compareFn;
  this.queue = [];
}

PriorityQueue.prototype.push = function (item) {
  this.queue.push(item);
  let index = this.queue.length - 1;
  let parent = Math.floor((index - 1) / 2);

  while (parent >= 0 && this.compare(parent, index) > 0) {
    // 交换
    [this.queue[index], this.queue[parent]] = [
      this.queue[parent],
      this.queue[index],
    ];
    index = parent;
    parent = Math.floor((index - 1) / 2);
  }
};

// 获取堆顶元素并移除
PriorityQueue.prototype.pop = function () {
  const ret = this.queue[0];

  // 把最后一个节点移到堆顶
  this.queue[0] = this.queue.pop();

  let index = 0;
  // 左子节点下标，left + 1 就是右子节点下标
  let left = 1;
  let selectedChild = this.compare(left, left + 1) > 0 ? left + 1 : left;

  // 下沉
  while (
    selectedChild !== undefined &&
    this.compare(index, selectedChild) > 0
  ) {
    // 交换
    [this.queue[index], this.queue[selectedChild]] = [
      this.queue[selectedChild],
      this.queue[index],
    ];
    index = selectedChild;
    left = 2 * index + 1;
    selectedChild = this.compare(left, left + 1) > 0 ? left + 1 : left;
  }

  return ret;
};

PriorityQueue.prototype.size = function () {
  return this.queue.length;
};

// 使用传入的 compareFn 比较两个位置的元素
PriorityQueue.prototype.compare = function (index1, index2) {
  if (this.queue[index1] === undefined) {
    return 1;
  }
  if (this.queue[index2] === undefined) {
    return -1;
  }

  return this.compareFn(this.queue[index1], this.queue[index2]);
};
