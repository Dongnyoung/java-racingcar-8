package racingcar.service;

import java.util.HashMap;

public class WinnerDecider {
    private int maxStraightCount;
    private HashMap<String,Integer> straightCountMap;
    private StringBuilder winnerStrBuilder;
    public WinnerDecider(int maxStraightCount, HashMap<String,Integer> straightCountMap) {
        this.maxStraightCount = maxStraightCount;
        this.straightCountMap = straightCountMap;
    }
    public void decide(String[] participants){
        //우승자 결정
        winnerStrBuilder = new StringBuilder();
        for(String part : participants) {
            if(maxStraightCount ==straightCountMap.get(part)){
                winnerStrBuilder.append(part);
                winnerStrBuilder.append(", ");
            }
        }
        winnerStrBuilder.delete(winnerStrBuilder.length()-2,winnerStrBuilder.length());
    }
    public StringBuilder getWinnerStrBuilder() {
        return winnerStrBuilder;
    }
}
