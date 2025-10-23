package racingcar.Parser;

public class InputParser {
    public String[] namesParse(String participants){
        String[] names = participants.split(",");
        return names;
    }
    public StringBuilder[] parse(String participants) {
        String[] parts = namesParse(participants);
        return new StringBuilder[parts.length];
    }
}
