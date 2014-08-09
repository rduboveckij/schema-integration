package ntu.asu.rduboveckij.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.internal.Result;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public final class CommonUtils {
    public static final String SPACE_DELIMITER = " ";
    public static final double MAX_SCORE = 1d;
    public static final double MIN_SCORE = 0d;

    private CommonUtils() {
        throw new RuntimeException("This class is util");
    }

    @SafeVarargs
    public static double normal(Pair<Double, Double>... factorValues) {
        return Stream.of(factorValues)
                .mapToDouble(val -> val.getKey() * val.getValue())
                .sum() / Stream.of(factorValues)
                .filter(factor -> factor.getValue() != 0d)
                .mapToDouble(factor -> factor.getKey())
                .sum();
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



    public static <P extends AbstractModelItem, T extends Result<P>> Set<T> similarityFilter(Set<T> results) {
        Set<P> ignored = Sets.newHashSet();
        return results.stream()
                .sorted((r1, r2) -> Double.compare(r1.getScore(), r2.getScore()))
                .filter(result -> !ignored.contains(result.getSource()) && !ignored.contains(result.getTarget()))
                .peek(result -> ignored.addAll(Arrays.asList(result.getSource(), result.getTarget())))
                .collect(Collectors.toSet());
    }

    public static double booleanToDouble(boolean b) {
        return b ? 1d : 0d;
    }

    public static <T, R> Stream<R> eachStream(Collection<T> firsts, Collection<T> seconds, BiFunction<T, T, R> function) {
        return firsts.parallelStream().flatMap(eachMap(seconds, function));
    }

    public static <T, R> Function<T, Stream<R>> eachMap(Collection<T> seconds, BiFunction<T, T, R> function) {
        return first -> seconds.parallelStream().map(second -> function.apply(first, second));
    }
}