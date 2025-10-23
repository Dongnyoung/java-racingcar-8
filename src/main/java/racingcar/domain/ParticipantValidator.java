package racingcar.domain;

public class ParticipantValidator {
    public void validate(String participant) {
        if(participant.length()>5){
            throw new IllegalArgumentException();
        }
    }
}
