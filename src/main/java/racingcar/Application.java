package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.Parser.InputParser;
import racingcar.view.banner.Banner;
import racingcar.view.banner.OpeningBanner;
import racingcar.view.banner.ResultBanner;
import racingcar.view.banner.TryCountBanner;
import racingcar.domain.*;
import racingcar.domain.service.MoveCounter;
import racingcar.domain.service.Movement;
import racingcar.view.io.InputView;
import racingcar.view.io.WinnerView;
import racingcar.RacingController;
import racingcar.RaceService;
import java.util.HashMap;

class RacingController{
    private final Banner openingBanner;
    private final Banner tryCountBanner;
    private final Banner resultBanner;
    private final InputView inputView;
    private final InputParser inputParser;
    private final ParticipantRegistry registry;
    private final RaceService raceService;
    public RacingController(){
        openingBanner = new OpeningBanner();
        tryCountBanner = new TryCountBanner();
        inputView = new InputView();
        inputParser = new InputParser();
        registry = new ParticipantRegistry();
        resultBanner = new ResultBanner();
        raceService= new RaceService();
    }
    public void run(){
        //openingMent
        openingBanner.ment();
        //입력받기
        String participant = inputView.input();

        //입력값 처리
        String[] participants = inputParser.namesParse(participant);
        StringBuilder[] progresses = inputParser.parse(participant);

        //참가자 결정
        progresses = registry.decide(participants, progresses);

        //시도 횟수 받기
        tryCountBanner.ment();
        String countStr = inputView.input();
        int count = inputParser.stringToInt(countStr);
        resultBanner.ment();

        //핵심 비즈니스 RaceService에 위임
        StringBuilder winnerStr = raceService.run(participants, progresses, count);

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
