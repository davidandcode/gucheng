package basic_algorithms.dfs;
import java.util.Arrays;

public class MinTimeRequired {
    int res = Integer.MAX_VALUE;
    public int minTimeRequired(int[] jobs, int k){
        Arrays.sort(jobs);
        dfs(jobs,jobs.length-1, new int[k]);
        return res;
    }
    private void dfs(int[] jobs, int jobId, int[] workers){
        // update res first to make res small as early as possible
        if(jobId < 0){
            res = Math.min(Arrays.stream(workers).max().getAsInt(),res);
            return;
        }
        if(Arrays.stream(workers).max().getAsInt() >= res) return;
        for(int i =0; i < workers.length; i++){
            if(i != 0 && workers[i] == workers[i-1]) continue;
            workers[i] += jobs[jobId];
            dfs(jobs,jobId-1, workers);
            workers[i] -= jobs[jobId];
        }
    }
}
