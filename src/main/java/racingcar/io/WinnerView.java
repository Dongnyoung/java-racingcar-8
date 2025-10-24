package racingcar.io;

public class WinnerView {
    public void printWinner(StringBuilder winnerStr){
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
