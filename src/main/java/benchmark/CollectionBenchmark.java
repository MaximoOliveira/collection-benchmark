package benchmark;

import org.apache.commons.collections4.collection.CompositeCollection;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1)
@Measurement(iterations = 5, time = 1)
@Warmup(iterations = 2, time = 1)
public class CollectionBenchmark {

    @Param({"50", "1000", "10000", "50000"})
    private int listSize;

    private Collection<String> collection1;
    private Collection<String> collection2;

    // Setup method to initialize collections before each benchmark
    @Setup
    public void setup() {
        collection1 = new ArrayList<>(listSize);
        collection2 = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            collection1.add("A" + i);
            collection2.add("B" + i);
        }
    }

    // Benchmark method for the addAll approach
    @Benchmark
    public Collection<String> benchmarkAddAll() {
        Collection<String> mergedCollection = new ArrayList<>(collection1);
        mergedCollection.addAll(collection2);
        return mergedCollection;
    }

    // Benchmark method for the CompositeCollection approach
    @Benchmark
    public Collection<String> benchmarkAddComposited() {
        CompositeCollection<String> mergedCollection = new CompositeCollection<>();
        mergedCollection.addComposited(collection1, collection2);
        return mergedCollection;
    }

    // Main method to run the benchmark
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(CollectionBenchmark.class.getSimpleName())
                .forks(1)
                .addProfiler("gc")
                .resultFormat(ResultFormatType.JSON)
                .result("benchmark-results.json")
                .build();

        new Runner(opt).run();
    }
}
