package racingcar.domain;

public class ParticipantRegistry {
    private final ParticipantValidator participantValidator;
    public ParticipantRegistry() {
        participantValidator = new ParticipantValidator();
    }
    public StringBuilder[] decide(String[] participants,StringBuilder[] participantsStraight) {
        for (int i = 0; i < participants.length; i++) {
            //참가자 유효성 검사
            participantValidator.validate(participants[i]);
            StringBuilder str = new StringBuilder(participants[i]);
            participantsStraight[i] = str.append(" : "); //참가자 결정
        }
        return participantsStraight;
    }
}
