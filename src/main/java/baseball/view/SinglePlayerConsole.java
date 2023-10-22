package baseball.view;

import baseball.domain.BaseBallNumbers;
import exception.DuplicateBaseBallNumber;
import exception.OutOfBaseBallNumbersSize;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SinglePlayerConsole implements Player {

    private static final String RESTART_CODE = "1";
    private static final String END_CODE = "2";

    @Override
    public void startBaseballGameMessage() {
        OutputConsole.printStartMessage();
    }

    @Override
    public BaseBallNumbers readConsole() {
        String stringNumbers = InputConsole.readConsole();
        try {
            final IntStream numbers = Stream.of(stringNumbers.split("")).mapToInt(Integer::parseInt);
            return BaseBallNumbers.of(numbers);
        } catch (NumberFormatException | OutOfBaseBallNumbersSize | DuplicateBaseBallNumber e) {
            throw new IllegalStateException(String.format("올바르지 않은 입력입니다. 서로 다른 3개의 숫자를 입력해주세요. %s", stringNumbers));
        }
    }

    @Override
    public boolean checkRestart() {
        OutputConsole.requestRestartOrEndMessage();
        String input = InputConsole.readConsole();
        return isRestart(input);
    }

    private boolean isRestart(String input) {
        return switch (input) {
            case RESTART_CODE -> true;
            case END_CODE -> false;
            default -> throw new IllegalStateException("올바르지 않은 입력입니다.");
        };
    }
}