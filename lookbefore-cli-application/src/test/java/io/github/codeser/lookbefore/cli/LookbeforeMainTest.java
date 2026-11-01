package io.github.codeser.lookbefore.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class LookbeforeMainTest {

    @Test
    void versionOptionPrintsProductNameAndExitsWithZero() {
        StringWriter out = new StringWriter();
        CommandLine commandLine = new CommandLine(new LookbeforeMain());
        commandLine.setOut(new PrintWriter(out));

        int exitCode = commandLine.execute("--version");

        assertEquals(0, exitCode);
        assertTrue(out.toString().contains("Codeser Lookbefore"), () -> "Output: " + out);
    }
}
