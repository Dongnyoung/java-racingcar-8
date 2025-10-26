package racingcar;

import racingcar.domain.WinnerDecider;
import racingcar.domain.service.MaxMoveFinder;
import racingcar.domain.service.MoveCounter;
import racingcar.domain.service.Movement;

import java.util.HashMap;

public class RaceService {

    /**
     * @param participants 참가자 이름 배열 (원본 유지)
     * @param progresses   각 참가자의 진행 표시(StringBuilder) 배열
     * @param tryCount     시도 횟수
     * @return 최종 우승자 문자열(StringBuilder, 기존 WinnerView 호환)
     */
    public StringBuilder run(String[] participants,
                             StringBuilder[] progresses,
                             int tryCount) {
        // 1) 전진
        Movement movement = new Movement(progresses);
        movement.move(tryCount);
        progresses = movement.getMoveStraight();

        // 2) 전진 수 집계
        MoveCounter moveCounter = new MoveCounter(participants);
        moveCounter.moveCheck(progresses);
        HashMap<String, Integer> straightCountMap = moveCounter.getStraightCountMap();

        // 3) 최대 전진횟수
        MaxMoveFinder maxMoveFinder = new MaxMoveFinder(straightCountMap);
        maxMoveFinder.maxCount(participants);
        int maxStraightCount = maxMoveFinder.getMaxStraightCount();

        // 4) 우승자 결정
        WinnerDecider winnerDecider = new WinnerDecider(maxStraightCount, straightCountMap);
        winnerDecider.decide(participants);

        // WinnerView 호환 위해 StringBuilder 그대로 반환
        return winnerDecider.getWinnerStrBuilder();
    }
}
