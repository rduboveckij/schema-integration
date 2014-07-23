package ntu.asu.rduboveckij.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public final class CommonUtils {
    private CommonUtils() {
        throw new RuntimeException("This class is util");
    }

    @SafeVarargs
    public static double normal(Pair<Double, Double>... factorValues) {
        double top = Stream.of(factorValues)
                .mapToDouble(factor -> factor.getKey())
                .average()
                .getAsDouble();
        return top / Stream.of(factorValues)
                .mapToDouble(val -> val.getKey() * val.getValue())
                .average()
                .getAsDouble();
    }

    public <T> Supplier<T> atPoll(Callable<T> task) {
        ForkJoinTask<T> job = ForkJoinPool.commonPool().submit(task);
        return () -> {
            try {
                return job.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
