# Collection Benchmark

This repository contains a Java project to benchmark different methods of merging collections using the Java Microbenchmark Harness (JMH). Specifically, it compares the performance of `addAll` and `CompositeCollection` methods in terms of execution time and garbage collection overhead.

## Overview

Merging collections is a common task in Java programming. This project aims to evaluate the efficiency of two approaches:

1. `addAll`: The traditional method of merging collections.
2. `CompositeCollection`: A more memory-efficient approach from Apache Commons Collections.

## Benchmark Setup

The benchmark covers a range of list sizes to simulate various real-world scenarios:

- Small lists: 50 elements
- Medium lists: 1,000 elements
- Large lists: 10,000 elements
- Extra-large lists: 50,000 elements

### Parameters and Benchmark Modes

The JMH parameters and modes are configured as follows:

- **Benchmark Mode:** `Mode.SampleTime`
    - Measures the time taken for individual samples of the benchmarked method, providing a distribution of execution times.
- **Output Time Unit:** `TimeUnit.MILLISECONDS`
    - Results are reported in milliseconds for precision.
- **Forks, Warmups, and Measurements:**
    - **Fork:** 1 (the benchmark process is forked once to minimize JVM startup costs)
    - **Warmup Iterations:** 2 iterations, each lasting 1 second, to allow the JVM to optimize the code.
    - **Measurement Iterations:** 5 iterations, each lasting 1 second, to gather performance data after the warm-up phase.

## Running the Benchmark

To run the benchmark, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/collection-benchmark.git
    cd collection-benchmark
    ```

2. **Build the project:**
   Ensure you have Maven installed. Then, run:
    ```sh
    mvn clean install
    ```

3. **Run the benchmark:**
   Execute the benchmark using the following command:
    ```sh
    java -jar target/collection-benchmark-1.0-SNAPSHOT.jar
    ```

   This will run the benchmark tests and generate a `benchmark-results.json` file containing the results.

4. **View the results:**
   You can visualize the results by dragging the `benchmark-results.json` file into [jmh.morethan.io](https://jmh.morethan.io/).

## Results

For detailed results and analysis, please refer to the blog post: [Efficient List Merging in Java: CompositeCollection vs Collection](https://maximooliveira.com/2024-07-21/efficient-list-merging-in-java-compositecollection-vs-collection).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
