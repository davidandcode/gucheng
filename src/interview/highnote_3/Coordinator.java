package interview.highnote_3;

import java.util.*;
import java.util.concurrent.*;

public class Coordinator {
    private List<Participant> participants = new ArrayList<>();
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public boolean prepare() {
        List<ParticipantPrepareTask> prepareTasks = new ArrayList<>();
        for (Participant participant : participants) {
            prepareTasks.add(new ParticipantPrepareTask(participant));
        }

        try {
            List<Future<Boolean>> results = executor.invokeAll(prepareTasks);
            for (Future<Boolean> result : results) {
                if (!result.get()) {
                    return false;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    public void commit() {
        List<ParticipantCommitTask> commitTasks = new ArrayList<>();
        for (Participant participant : participants) {
            commitTasks.add(new ParticipantCommitTask(participant));
        }
        try{
            executor.invokeAll(commitTasks);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void abort() {
        List<ParticipantAbortTask> abortTasks = new ArrayList<>();
        for (Participant participant : participants) {
            abortTasks.add(new ParticipantAbortTask(participant));
        }
        try{
            executor.invokeAll(abortTasks);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args){
        Coordinator coordinator = new Coordinator();
        coordinator.addParticipant(new Participant("Participant1"));
        coordinator.addParticipant(new Participant("Participant2"));

        // Phase 1: Prepare
        if (coordinator.prepare()) {
            // Phase 2: Commit
            coordinator.commit();
        } else {
            // Phase 2: Abort
            coordinator.abort();
        }

        coordinator.shutdown();
    }
}

class Participant {
    private String name;

    public Participant(String name) {
        this.name = name;
    }

    public boolean prepare() {
        // Simulate prepare logic
        return true;
    }

    public void commit() {
        // Actual commit logic
    }

    public void abort() {
        // Actual abort logic
    }

    public String getName() {
        return name;
    }
}

class ParticipantPrepareTask implements Callable<Boolean> {
    private Participant participant;

    public ParticipantPrepareTask(Participant participant) {
        this.participant = participant;
    }

    @Override
    public Boolean call() {
        return participant.prepare();
    }
}

class ParticipantCommitTask implements Callable<Void> {
    private Participant participant;

    public ParticipantCommitTask(Participant participant) {
        this.participant = participant;
    }

    @Override
    public Void call() {
        participant.commit();
        return null;
    }
}

class ParticipantAbortTask implements Callable<Void> {
    private Participant participant;

    public ParticipantAbortTask(Participant participant) {
        this.participant = participant;
    }

    @Override
    public Void call() {
        participant.abort();
        return null;
    }
}

