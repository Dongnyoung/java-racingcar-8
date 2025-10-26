package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.Parser.InputParser;
import racingcar.view.banner.*;
import racingcar.domain.*;
import racingcar.domain.service.MoveCounter;
import racingcar.domain.service.Movement;
import racingcar.view.io.InputView;
import racingcar.view.io.WinnerView;
import racingcar.RacingController;
import racingcar.RaceService;
import java.util.HashMap;

class RacingController{
    private final BannerFacade banners;
    private final InputView inputView;
    private final InputParser inputParser;
    private final ParticipantRegistry registry;
    private final RaceService raceService;
    public RacingController(){
        this.banners = new BannerFacade();
        this.inputView = new InputView();
        this.inputParser = new InputParser();
        this.registry = new ParticipantRegistry();
        this.raceService= new RaceService();
    }
    public void run(){
        //openingMent
        banners.opening();
        //입력받기
        String participant = inputView.input();

        //입력값 처리
        String[] participants = inputParser.namesParse(participant);
        StringBuilder[] progresses = inputParser.parse(participant);

        //참가자 결정
        progresses = registry.decide(participants, progresses);

        //시도 횟수 받기
        banners.askTryCount();
        String countStr = inputView.input();
        int count = inputParser.stringToInt(countStr);
        banners.result();

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
