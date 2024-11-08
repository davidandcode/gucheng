package basic_algorithms.prefix_sum;

public class RangeSumQuery2D {
    int[][] sum;
/*左上角00右下角ij的矩形的sum表示为sum[i+1][j+1]
* */
    public RangeSumQuery2D(int[][] matrix) {
        sum = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 0; i< matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++){
                sum[i+1][j+1] = sum[i][j+1] + sum[i+1][j]+ matrix[i][j]
                        - sum[i][j];
            }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2+1][col2+1] -sum[row1][col2+1]
                - sum[row2+1][col1] + sum[row1][col1];

    }
}
