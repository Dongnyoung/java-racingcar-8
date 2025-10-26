package racingcar.service;

import java.util.HashMap;

public class MoveCounter {
    private final HashMap<String,Integer> straightCountMap;
    public MoveCounter(String[] participants) {
        this.straightCountMap = new HashMap<>();
        //초기화
        for(String part : participants) {
            straightCountMap.put(part,0);
        }
    }
    public void moveCheck(StringBuilder[] participantsStraight){
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
            StringBuilder p = new StringBuilder();
            for(char c : partStr.toCharArray()){
                if(c==' '){
                    break;
                }
                p.append(c);
            }
            String name = p.toString();
            straightCountMap.put(name,straightCount);
        }
    }
    public HashMap<String,Integer> getStraightCountMap() {
        return new HashMap<>(straightCountMap); //그냥 해쉬맵을 반환하면 외부에서 수정 가능할 수 있음.
    }
}
