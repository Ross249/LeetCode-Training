from collections import defaultdict
class Solution(object):

    def shortestBridge(self, grid):
        directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]  # right, left, up, down

        n = len(grid)
        m = len(grid[0])

        def one_land(grid):
            for in_x, row in enumerate(grid):
                for in_y, val in enumerate(row):
                    if val:
                        return (in_x, in_y)

        in_x, in_y = one_land(grid)
        queue = [(in_x, in_y)]
        first_land = [(in_x, in_y)]


        visited = defaultdict(lambda: False, {})
        visited[(in_x, in_y)] = True

        while first_land:
            x, y = first_land.pop(0)
            for dir_x, dir_y in directions:
                nei_x = x + dir_x
                nei_y = y + dir_y
                if nei_x in range(n) and nei_y in range(m):
                    if grid[nei_x][nei_y]:
                        if not visited[(nei_x, nei_y)]:
                            first_land.append((nei_x, nei_y))
                            queue.append((nei_x, nei_y))
                            visited[(nei_x, nei_y)] = True

        cost = 0
        layer_size = 0
        while queue:
            layer_size = len(queue)
            for i in range(layer_size):
                x, y = queue.pop(0)
                for dir_x, dir_y in directions:
                    nei_x = x + dir_x
                    nei_y = y + dir_y
                    if nei_x in range(n) and nei_y in range(m):
                        if not visited[(nei_x, nei_y)]:
                            if grid[nei_x][nei_y]:
                                return cost
                            else:
                                queue.append((nei_x, nei_y))
                                visited[(nei_x, nei_y)] = True
            cost += 1
        return None