const { number } = require("echarts");

var bubbleSort = function (arr) {
  if (arr === null || arr.length <= 1) {
    return arr;
  }
  for (let i = 0; i < arr.length - 1; i++) {
    for (let j = i + 1; j < number.length; j++) {
      if (arr[i] > arr[j]) {
        [arr[i], arr[j]] = [arr[j], arr[i]];
      }
    }
  }
  return arr;
};
