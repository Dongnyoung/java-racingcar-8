package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import racingcar.service.MaxMoveFinder;

import java.util.HashMap;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 누적_전진체크(){
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    assertThat(output()).contains("pobi : ---", "woni : --", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD,STOP,
                MOVING_FORWARD,MOVING_FORWARD
        );
    }

    @Test
    void 최대전진_횟수체크() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pobi", 3);
        map.put("woni", 1);
        map.put("jun", 2);

        MaxMoveFinder finder = new MaxMoveFinder(map);
        String[] participants = {"pobi", "woni", "jun"};

        finder.maxCount(participants);

        assertThat(finder.getMaxStraightCount()).isEqualTo(3);
    }

    @Test
    void 동점_다중우승자(){
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "2");
                    assertThat(output()).contains("pobi : -", "woni : -", "최종 우승자 : pobi, woni");
                },
                MOVING_FORWARD, MOVING_FORWARD,
                STOP,STOP
        );
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
