package racingcar.view.banner;

/**
 * 각 Banner 구현체를 한 곳에서 관리하고,
 * 컨트롤러가 단순히 Facade의 메서드를 호출하게 함.
 */
public class BannerFacade {
    private final Banner openingBanner;
    private final Banner tryCountBanner;
    private final Banner resultBanner;

    public BannerFacade() {
        this.openingBanner = new OpeningBanner();
        this.tryCountBanner = new TryCountBanner();
        this.resultBanner = new ResultBanner();
    }

    public void opening() {
        openingBanner.ment();
    }

    public void askTryCount() {
        tryCountBanner.ment();
    }

    public void result() {
        resultBanner.ment();
    }
}
