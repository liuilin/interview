package array;

/**
 * @author Daniel Liu
 * @date 2022-10-13
 */
public class MinPathSum1111 {
    public static void main(String[] args) {
        int matrx[][] = {
                {2, 4, 6, 8, 7, 9},
                {3, 8, 9, 1, 4, 5},
                {6, 9, 12, 2, 15, 11},
                {8, 7, 18, 7, 13, 2},
                {7, 2, 10, 4, 3, 7},
                {9, 5, 6, 17, 5, 1}
//                {2,4,6},
//                {3,8,9},
//                {6,9,12}
        };
        System.out.println(new MinPathSum1111().minPath1(matrx));
    }

    public int minPath1(int[][] matrix) {
        int width = matrix[0].length, high = matrix.length;
        if (high == 0 || width == 0) {
            return 0;
        }
        // 初始化
        for (int i = 1; i < high; i++) {
            matrix[i][0] += matrix[i - 1][0];
//            System.out.println(matrix[i][0]);
        }
        for (int i = 1; i < width; i++) {
            matrix[0][i] += matrix[0][i - 1];
//            System.out.println(matrix[0][i]);
        }
        for (int i = 1; i < high; i++) {
            for (int j = 1; j < width; j++) {
                matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
//                System.out.println(matrix[i][j]);
            }
        }
        return matrix[high - 1][width - 1];
    }
}
//
//    public static int minPath(int[][] matrix) {
//        return minPath(matrix, 0, 0);
//    }
//
//    public static int minPath(int[][] matrix, int i, int j) {
//        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
//            return matrix[i][j];
//        }
//        if (i == matrix.length - 1 && j != matrix[0].length - 1) {
//            return matrix[i][j] + minPath(matrix, i, j + 1);
//        }
//        if (j == matrix[0].length - 1 && i != matrix.length - 1) {
//            return matrix[i][j] + minPath(matrix, i + 1, j);
//        }
//        int right = minPath(matrix, i, j + 1);
//        int down = minPath(matrix, i + 1, j);
//        return matrix[i][j] + Math.min(right, down);
//    }
