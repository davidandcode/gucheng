package basic_algorithms.divide_conquer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClosestPairOfPoints {
    private class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // distance就是平方距离，减少一步平方根运算
    private long distance(Point a, Point b){
        return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
    }
    //Assume points are sorted by x
    private long divide(int left, int right, Point[] points){
        long curMinDis = Long.MAX_VALUE;
        //只有一个点 返回无穷大，保证两个单点的交叉距离就小于两个单点各自的距离
        if(left == right) return curMinDis;
        if(left + 1 == right) return distance(points[left],points[right]);
        int mid = (left + right)/2;
        //两个half各自的最小距离
        long leftMinDis = divide(left,mid,points);
        long rightMinDis = divide(mid+1,right, points);
        curMinDis = Math.min(leftMinDis, rightMinDis);
        //两个半区的交叉距离，x的搜索范围[mid-curmindis,mid+curmindis]
        List<Integer> validPointIndex = new ArrayList<>();
        for(int i = left; i<= right; i++){
            if(Math.pow(Math.abs(points[i].x - points[mid].x),2)<= curMinDis)
                validPointIndex.add(i);
        }
        //这个排序是个优化，不是必须的，按y排序有更大概率较早获取较小的curmindis，因为
        //先去计算在y方向上距离较近的点。但y方向距离近不能保证距离近，有可能y距离远的点
        // 的x方向距离更近，所以continue不是break。为什么按照y排序？因为长条形
        Collections.sort(validPointIndex,(a,b) ->points[a].y - points[b].y);
        for(int i = 0; i < validPointIndex.size() -1; i++)
            for(int j = i+1; j< validPointIndex.size(); j++){
                //区间内两点距离若大于curmindis，就没必要算了
                if(Math.pow(Math.abs(points[validPointIndex.get(i)].y
                        - points[validPointIndex.get(j)].y),2) >= curMinDis)
                    continue;
                long tempDis = distance(points[validPointIndex.get(i)],
                        points[validPointIndex.get(j)]);
                curMinDis = Math.min(tempDis,curMinDis);
            }
        return curMinDis;
    }
    public long closestPair(int[] xs, int[] ys){
        int len = xs.length;
        Point[] points = new Point[len];
        for(int i =0; i < len; i++){
            points[i] = new Point(xs[i], ys[i]);
        }
    /*这个排序是必须的 因为是按照index来分左右half的，而算法的几何基础是按照x坐标来分
    左右half，于是要按照x坐标大小进行排序。为何按照index分half？为了两部分点数量相等*/
        Arrays.sort(points,(a,b) -> a.x - b.x);
        return divide(0, len-1, points);
    }
}
