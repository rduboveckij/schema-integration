package ntu.asu.rduboveckij.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.TableIndex;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

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
        return normal(Lists.newArrayList(factorValues));
    }

    public static double normal(Collection<Pair<Double, Double>> factorValues) {
        double sumOfKey = factorValues.parallelStream()
                .mapToDouble(Pair::getKey)
                .sum();
        double topSum = factorValues.parallelStream()
                .mapToDouble(val -> val.getKey() * val.getValue())
                .sum();
        return Double.isNaN(sumOfKey) || Double.isNaN(topSum) || sumOfKey == 0d || topSum == 0d ?
                MIN_SCORE: topSum / sumOfKey;
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
                .sorted((r1, r2) -> Double.compare(r1.getScore(), r2.getScore()) * -1)
                .filter(result -> !ignored.contains(result.getIndex().getSource()) && !ignored.contains(result.getIndex().getTarget()))
                .peek(result -> ignored.addAll(result.getIndex().getAll()))
                .collect(Collectors.toSet());
    }

    public static <M extends AbstractModelItem> Set<M> findTransferred(Set<TableIndex<M>> joined, Set<M> source, Set<M> target) {
        Set<M> singleMapping = Stream.concat(source.stream(), target.stream())
                .collect(Collectors.toSet());

        Set<M> results = joined.parallelStream()
                .flatMap(index -> index.getAll().stream())
                .collect(toSet());
        singleMapping.removeAll(results);
        return singleMapping;
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

    public static <T extends Comparable<T>> T requireRange(T number, Range<T> range) {
        Preconditions.checkArgument(range.contains(number), "Available value si only in range" + range);
        return number;
    }

    public static String requireNotEmpty(String value) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(value), "Available is only not null or empty string");
        return value;
    }
}