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
