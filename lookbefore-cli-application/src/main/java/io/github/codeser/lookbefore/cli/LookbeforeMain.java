package io.github.codeser.lookbefore.cli;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

/**
 * Điểm vào của công cụ dòng lệnh {@code lookbefore}.
 *
 * <p>Hiện chỉ là bộ khung chạy được (walking skeleton) để chứng minh build và đóng gói.
 */
@Command(
        name = "lookbefore",
        mixinStandardHelpOptions = true,
        versionProvider = LookbeforeMain.VersionProvider.class,
        description = "Phân tích tác động của một thay đổi code trước khi merge.")
public final class LookbeforeMain {

    private static final String PRODUCT_NAME = "Codeser Lookbefore";
    private static final String VERSION_RESOURCE = "/lookbefore-version.properties";
    private static final String VERSION_KEY = "lookbefore.version";

    @Spec
    private CommandSpec spec;

    public static void main(String[] args) {
        System.exit(new CommandLine(new LookbeforeMain()).execute(args));
    }

    @Command(
            name = "analyze",
            mixinStandardHelpOptions = true,
            description = "Phân tích tác động của thay đổi so với nhánh gốc.")
    int analyze(
            @Option(
                    names = "--base",
                    paramLabel = "<ref>",
                    defaultValue = "main",
                    description = "Nhánh hoặc commit gốc để so sánh (mặc định: ${DEFAULT-VALUE}).")
            String base) {
        spec.commandLine().getErr().println("Chưa triển khai");
        return 1;
    }

    static final class VersionProvider implements IVersionProvider {

        @Override
        public String[] getVersion() {
            Properties properties = new Properties();
            try (InputStream in = LookbeforeMain.class.getResourceAsStream(VERSION_RESOURCE)) {
                if (in == null) {
                    throw new IllegalStateException("Không tìm thấy " + VERSION_RESOURCE);
                }
                properties.load(in);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return new String[] {PRODUCT_NAME + " " + properties.getProperty(VERSION_KEY)};
        }
    }
}
