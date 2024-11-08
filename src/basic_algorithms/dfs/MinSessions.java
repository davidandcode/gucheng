package basic_algorithms.dfs;
import java.util.Arrays;

public class MinSessions {
    int res = Integer.MAX_VALUE;
    public int minSessions(int[] tasks, int sessionTime){
        Arrays.sort(tasks);
        res = tasks.length;
        int[] sessions = new int[tasks.length];
        //sessionCount starts from 0
        dfs(tasks,sessions,tasks.length-1,0,sessionTime);
        return res;
    }
    private void dfs(int[] tasks, int[] sessions, int taskId, int sessionCount, int sessionTime){
        if(taskId < 0){
            res = Math.min(res,sessionCount);
            return;
        }
        if(sessionCount >= res) return;
        for(int i =0; i < sessionCount; i++){
            if(sessions[i] + tasks[taskId] <= sessionTime){
                if(i!=0 && sessions[i] == sessions[i-1]) continue;
                sessions[i] += tasks[taskId];
                dfs(tasks,sessions, taskId-1, sessionCount, sessionTime);
                sessions[i] -= tasks[taskId];
            }
        }
        sessions[sessionCount] += tasks[taskId];
        dfs(tasks,sessions, taskId-1, sessionCount+1, sessionTime);
        sessions[sessionCount] -= tasks[taskId];
    }
}
