package racingcar.view.banner;

public class OpeningBanner implements Banner {
    @Override
    public void ment(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼(,) 기준으로 구분");
    }
}
