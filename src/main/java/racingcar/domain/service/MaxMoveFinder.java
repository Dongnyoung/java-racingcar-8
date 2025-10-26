package racingcar.domain.service;

import java.util.HashMap;

public class MaxMoveFinder {
    private int maxStraightCount;
    private final HashMap<String,Integer> straightCountMap;
    public MaxMoveFinder(HashMap<String,Integer> straightCountMap) {
        this.straightCountMap= straightCountMap;
    }
    public void maxCount(String[] participants) {
        int maxCount =0;
        for(String part : participants) {
            maxCount = Math.max(maxCount, straightCountMap.get(part));
        }
        maxStraightCount = maxCount;
    }
    public int getMaxStraightCount() {
        return maxStraightCount;
    }

}
