package classics.parentheses;

public class MinAddToMakeParenthesesValid {
//为何以左为基准，因为后续的右可以抵消之前的左
//如果以右为基准，后续的左无法抵消先前的右
//这个题的本质是 right这个参数是可加可减(以左为基准)
//而不是以left参数可加可减，left只可以加
    public int minAdd(String s){
//left需要的左，right需要的右
        int left=0,right=0;
        for(char c:s.toCharArray()){
            if(c == '(') right++;
            if(c==')')
                if(right>0) right--;
                else left++;
        }
        return left+right;
    }
}
