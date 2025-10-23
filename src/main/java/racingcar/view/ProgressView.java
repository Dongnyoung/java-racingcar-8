package racingcar.view;

public class ProgressView {
    public void print(StringBuilder[] moveStraight) {
        //진행상황 출력
        for(StringBuilder part : moveStraight) {
            System.out.println(part.toString());
        }
        System.out.println();
    }
}
