package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.banner.OpeningMent;
import racingcar.io.InputView;

import java.util.ArrayList;
import java.util.HashMap;

class RacingController{
    private final OpeningMent openingMent;
    private final InputView inputView;
    public RacingController(){
        openingMent = new OpeningMent();
        inputView = new InputView();
    }
    public void run(){
        //openingMent
        openingMent.ment();
        //입력받기
        String participant = inputView.input();

        //입력값 처리
        String[] participants = participant.split(",");
        StringBuilder[] participantsStraight = new StringBuilder[participants.length];

        //참가자 결정
        for (int i = 0; i < participants.length; i++) {
            //참가자 유효성 검사
            if(participants[i].length()>5){
                throw new IllegalArgumentException("자동차 이름은 5자이하여야함 ");
            }
            StringBuilder str = new StringBuilder(participants[i]);
            participantsStraight[i] = str.append(" : "); //참가자 결정
        }

        //시도 횟수 받기
        System.out.println("시도할 횟수는 몇 회 인가요?");
        String countStr = Console.readLine();
        int count = Integer.parseInt(countStr);
        System.out.println("실행결과");

        //전진 로직
        while(count > 0) {
            count--;
            int randomNumber = 0;
            for(StringBuilder part : participantsStraight) {
                //랜덤 정수
                randomNumber = Randoms.pickNumberInRange(0, 9);
                //전진
                if(randomNumber>=4){
                    part.append("-");
                }
            }
            //진행상황 출력
            for(StringBuilder part : participantsStraight) {
                System.out.println(part.toString());
            }
            System.out.println();

        }
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
