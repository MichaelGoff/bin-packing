package app;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.benchmark.config.XmlPlannerBenchmarkFactory;

public class PackingBenchmarkApp {
    public static void main(String[] args) {
        PlannerBenchmarkFactory plannerBenchmarkFactory =
                new XmlPlannerBenchmarkFactory("/benchmark/packingBenchmarkConfig.xml");
        PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
        plannerBenchmark.benchmark();
    }
}
