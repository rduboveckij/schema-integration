package ntu.asu.rduboveckij.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractName;
import ntu.asu.rduboveckij.model.internal.Result;

import java.util.Arrays;
import java.util.Set;
import java.util.Spliterators;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public final class CommonUtils {
    public static final String SPACE_DELIMITER = " ";

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

    public static <T> Supplier<T> atPoll(Callable<T> task) {
        ForkJoinTask<T> job = ForkJoinPool.commonPool().submit(task);
        return () -> {
            try {
                return job.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static String spaceJoin(Iterable<? extends CharSequence> elements) {
        return String.join(SPACE_DELIMITER, elements);
    }

    public static Stream<String> spaceSplit(String element) {
        return StreamSupport.stream(Splitter.on(SPACE_DELIMITER).split(element).spliterator(), false);
    }

    public static <T extends Result> Set<T> similarityFilter(Set<T> results) {
        Set<AbstractName> ignored = Sets.newHashSet();
        return results.stream()
                .sorted((r1, r2) -> Double.compare(r1.getScore(), r2.getScore()))
                .filter(result -> !ignored.contains(result.getSource()) && !ignored.contains(result.getTarget()))
                .peek(result -> ignored.addAll(Arrays.asList(result.getSource(), result.getTarget())))
                .collect(Collectors.toSet());
    }
}