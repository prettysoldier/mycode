package coverage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test.CoverageSampleMethods;

/**
 * @Desc 《码出高效》P271
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 14:09
 **/
@DisplayName("覆盖率")
@Tag("coverage")
public class CoverageDemo {
    @Nested
    @DisplayName("行覆盖")
    class LineCoverage {

        @Test
        @Tag("fast")
        void testMethodTrue(){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertTrue(coverageSampleMethods.testMethod(1, 2, 0));
        }
    }

    @Nested
    @DisplayName("分支覆盖")
    class BranchCoverage {
        @ParameterizedTest
        @CsvSource({
                "1, 2, 0",
        })
        @DisplayName("条件组合覆盖，结果是true")
        void testMethodTrue(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertTrue(coverageSampleMethods.testMethod(a, b, c));
        }
        @ParameterizedTest
        @CsvSource({
                "1, 0, 0",
        })
        @DisplayName("条件组合覆盖，结果是true")
        void testMethodFalse(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertFalse(coverageSampleMethods.testMethod(a, b, c));
        }
    }

    @Nested
    @DisplayName("条件判定覆盖")
    class ConditonDecisionCoverage {
        @ParameterizedTest
        @CsvSource({
                "1, 0, 3",
                "0, 2, 3",
        })
        @DisplayName("条件组合覆盖，结果是true")
        void testConditionDecisionCoverageTrue(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertTrue(coverageSampleMethods.testMethod(a, b, c));
        }

        @ParameterizedTest
        @CsvSource({
                "0, 0, 0",
        })
        @DisplayName("条件组合覆盖，结果是false")
        void testConditionDecisionCoverageFalse(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertFalse(coverageSampleMethods.testMethod(a, b, c));
        }
    }


    @Nested
    @DisplayName("条件组合覆盖")
    class MultipleConditionCoverage{
        @ParameterizedTest
        @CsvSource({
                "1, 2, 3",
                "1, 2, 0",
                "1, 0, 3",
                "0, 2, 3",
                "0, 0, 3",
        })
        @DisplayName("条件组合覆盖，结果是true")
        @Disabled
        void testConditionDecisionCoverageTrue(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertTrue(coverageSampleMethods.testMethod(a, b, c));
        }

        @ParameterizedTest
        @CsvSource({
                "1, 0, 0",
                "0, 0, 0",
                "0, 2, 0",
        })
        @DisplayName("条件组合覆盖，结果是false")
        void testConditionDecisionCoverageFalse(int a, int b, int c){
            CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();
            Assertions.assertFalse(coverageSampleMethods.testMethod(a, b, c));
        }
    }


}
