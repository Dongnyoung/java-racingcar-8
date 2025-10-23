package racingcar.domain;

public class ParticipantRegistry {
    public ParticipantRegistry() {

    }
    public StringBuilder[] decide(String[] participants,StringBuilder[] participantsStraight) {
        for (int i = 0; i < participants.length; i++) {
            //참가자 유효성 검사
            if(participants[i].length()>5){
                throw new IllegalArgumentException("자동차 이름은 5자이하여야함 ");
            }
            StringBuilder str = new StringBuilder(participants[i]);
            participantsStraight[i] = str.append(" : "); //참가자 결정
        }
        return participantsStraight;
    }
}
