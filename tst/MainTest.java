import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @ParameterizedTest
    @MethodSource("provideArgsForTestConsoleOutput")
    public void testConsoleOutput(String[] args, String expected) {
        Main.main(args);
        assertEquals(expected, outContent.toString().trim());
    }

    private static Stream<Arguments> provideArgsForTestConsoleOutput() {
        return Stream.of(
            Arguments.of(new String[]{"9,223,372,036,854,775,807 10 36"},
                    "Num: 9223372036854775807, Base: 10\nNum: 1Y2P0IJ32E8E7, Base: 36"),
            // Add more test cases here
            Arguments.of(new String[]{"255 10 16"},
                    "Num: 255, Base: 10\nNum: FF, Base: 16")
        );
    }
}