package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.Parser.InputParser;
import racingcar.view.banner.Banner;
import racingcar.view.banner.OpeningBanner;
import racingcar.view.banner.ResultBanner;
import racingcar.view.banner.TryCountBanner;
import racingcar.domain.*;
import racingcar.domain.service.MaxMoveFinder;
import racingcar.domain.service.MoveCounter;
import racingcar.domain.service.Movement;
import racingcar.view.io.InputView;
import racingcar.view.io.WinnerView;

import java.util.HashMap;

class RacingController{
    private final Banner openingBanner;
    private final Banner tryCountBanner;
    private final Banner resultBanner;
    private final InputView inputView;
    private final InputParser inputParser;
    private final ParticipantRegistry registry;
    private Movement movement;
    public RacingController(){
        openingBanner = new OpeningBanner();
        tryCountBanner = new TryCountBanner();
        inputView = new InputView();
        inputParser = new InputParser();
        registry = new ParticipantRegistry();
        resultBanner = new ResultBanner();
    }
    public void run(){
        //openingMent
        openingBanner.ment();
        //입력받기
        String participant = inputView.input();

        //입력값 처리
        String[] participants = inputParser.namesParse(participant);
        StringBuilder[] participantsStraight = inputParser.parse(participant);

        //참가자 결정
        participantsStraight = registry.decide(participants, participantsStraight);

        //시도 횟수 받기
        tryCountBanner.ment();
        String countStr = inputView.input();
        int count = inputParser.stringToInt(countStr);
        resultBanner.ment();

        //전진 로직
        movement = new Movement(participantsStraight);
        movement.move(count);
        participantsStraight = movement.getMoveStraight();

        //참가자들의 전진횟수를 저장하는 map,초기화
        MoveCounter moveCount = new MoveCounter(participants);

        //직진 개수 체크
        moveCount.moveCheck(participantsStraight);
        HashMap<String,Integer> straightCountMap = moveCount.getStraightCountMap();

        //가장 많이 전진한 횟수 체크
        MaxMoveFinder maxMoveFinder = new MaxMoveFinder(straightCountMap);
        maxMoveFinder.maxCount(participants);
        int maxStraightCount = maxMoveFinder.getMaxStraightCount();

        //우승자 결정
        WinnerDecider winnerDecider = new WinnerDecider(maxStraightCount,straightCountMap);
        winnerDecider.decide(participants);
        StringBuilder winnerStr = winnerDecider.getWinnerStrBuilder();

        //우승자 출력
        WinnerView winnerView = new WinnerView();
        winnerView.printWinner(winnerStr);
    }
}

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            new RacingController().run();
        }
        finally {
            Console.close();  //자원정리
        }

    }
}
