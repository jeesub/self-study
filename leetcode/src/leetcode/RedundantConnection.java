package leetcode;

public class RedundantConnection {

	public int[] findRedundantConnection(int[][] edges) {
		DisjointSet disjointSet = new DisjointSet(edges.length + 1);

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			if (disjointSet.isConnected(u, v)) {
				return edge;
			}
			disjointSet.merge(u, v);
		}

		return new int[] {-1, -1};
	}

	public class DisjointSet {
		private final int[] parents;
		private final int[] rank;

		public DisjointSet(int num) {
			parents = new int[num];
			rank = new int[num];

			for (int i = 0; i < num; i++ ) {
				parents[i] = i;
				rank[i] = 1;
			}
		}

		public boolean isConnected(int u, int v) {
			return find(u) == find(v);
		}

		public int find(int u) {
			if (u == parents[u]) {
				return u;
			}

			parents[u] = find(parents[u]);
			return parents[u];
		}

		public DisjointSet merge(int u, int v) {
			int uParent = find(u);
			int vParent = find(v);

			if (uParent == vParent) {
				return this;
			}

			if (rank[uParent] > rank[vParent]) {
				parents[vParent] = uParent;
			} else {
				parents[uParent] = vParent;
			}

			if (rank[uParent] == rank[vParent]) {
				rank[vParent]++;
			}

			return this;
		}
	}

	public static void main(String[] args) {
		RedundantConnection solution = new RedundantConnection();

		int[][] input = {{0, 1}, {0, 2}, {1, 2}};
		int[] result = solution.findRedundantConnection(input);
		for (int num : result) {
			System.out.print(num + " ");
		}
		// output: 1 2

		int[][] input2 = {{16,25},{7,9},{3,24},{10,20},{15,24},{2,8},{19,21},{2,15},{13,20},{5,21},{7,11},{6,23},{7,16},{1,8},{17,20},{4,19},{11,22},{5,11},{1,16},{14,20},{1,4},{22,23},{12,20},{15,18},{12,16}};
		int[] result2 = solution.findRedundantConnection(input2);
		for (int num : result2) {
			System.out.print(num + " ");
		}
		// output: 1 4
	}
}
