package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.view.ProgressView;

public class Movement {
    private StringBuilder[] moveStraight;
    private final ProgressView progressView;
    public Movement(StringBuilder[] moveStraight) {
        this.moveStraight = moveStraight;
        this.progressView = new ProgressView();
    }
    public void move(int count) {
        while(count > 0) {
            count--;
            int randomNumber = 0;
            for(StringBuilder part : moveStraight) {
                //랜덤 정수
                randomNumber = Randoms.pickNumberInRange(0, 9);
                //전진
                if(randomNumber>=4){
                    part.append("-");
                }
            }
            //진행상황 출력
            progressView.print(moveStraight);
        }
    }
    public StringBuilder[] getMoveStraight() {
        return moveStraight;
    }
}
