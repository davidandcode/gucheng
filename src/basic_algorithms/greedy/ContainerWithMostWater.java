package basic_algorithms.greedy;

public class ContainerWithMostWater {
    public int maxArea(int[] height){
        int res=0;
        int left=0;
        int right=height.length-1;
        while(left < right){
            res = Math.max(res, Math.min(height[left],height[right])*(right-left));
            //if不能改成while，因为每移动一次挡板，都要和当前最大值比较一下
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return res;
    }
}
