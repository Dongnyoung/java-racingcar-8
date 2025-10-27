package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.view.io.ProgressView;

public class Movement {
    private final StringBuilder[] moveStraight;
    private final ProgressView progressView;

    public Movement(StringBuilder[] moveStraight) {
        this.moveStraight = moveStraight;
        this.progressView = new ProgressView();
    }

    public void move(int count) {
        while (count-- > 0) {
            moveParticipants();
            progressView.print(moveStraight);
        }
    }

    private void moveParticipants() {
        for (StringBuilder part : moveStraight) {
            if (shouldMove()) {
                part.append("-");
            }
        }
    }

    private boolean shouldMove() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= 4;
    }

    public StringBuilder[] getMoveStraight() {
        return moveStraight;
    }
}
