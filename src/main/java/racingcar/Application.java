package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.Parser.InputParser;
import racingcar.banner.Banner;
import racingcar.banner.OpeningBanner;
import racingcar.banner.ResultBanner;
import racingcar.banner.TryCountBanner;
import racingcar.domain.Movement;
import racingcar.domain.ParticipantRegistry;
import racingcar.io.InputView;

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

        //참가자들의 전친횟수를 저장하는 map
        HashMap<String,Integer> straightCountMap = new HashMap<>();
        //초기화
        for(String part : participants) {
            straightCountMap.put(part,0);
        }
        //직진 개수 체크
        for(StringBuilder part : participantsStraight) {
            String partStr = part.toString();
            //System.out.println(partStr);
            int straightCount =0;
            for(char c : partStr.toCharArray()) {
                if (c == '-') {
                    straightCount++;
                }

            }
            String p = "";
            for(char c : partStr.toCharArray()){
                if( c==' '){
                    break;
                }
                p+=String.valueOf(c);
            }
            straightCountMap.put(p,straightCount);
        }
        //디버깅
        /*
        for(String part : participants) {

            System.out.println(straightCountMap.get(part));
        }

         */
        //가장 많이 전진한 횟수 체크
        int maxStraightCount = 0;
        for(String part : participants) {
            maxStraightCount = Math.max(maxStraightCount, straightCountMap.get(part));
        }
        /*
        if(maxStraightCount==0){
            System.out.println("최종우승자 : ");
            return;
        }

         */

        //우승자 결정
        StringBuilder winnerStr = new StringBuilder();
        for(String part : participants) {
            if(maxStraightCount ==straightCountMap.get(part)){
                winnerStr.append(part);
                winnerStr.append(", ");
            }
        }
        winnerStr.delete(winnerStr.length()-2,winnerStr.length());

        //우승자 출력
        String[] answer = winnerStr.toString().split(",");
        System.out.print("최종 우승자 : ");
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i].trim()); //앞뒤공백제거
            if (i < answer.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
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
