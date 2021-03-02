package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.mirwaldt.LightState.readBoardFromString;

public class LightAnimationMain {
    public static void main(String[] args) throws IOException {
        final String input = Files.readString(Path.of("input.txt"), StandardCharsets.US_ASCII);

        final DefaultLightAnimation partOneLightAnimation = new DefaultLightAnimation();
        partOneLightAnimation.init(readBoardFromString(input, 100), 100);
        System.out.println(partOneLightAnimation.animateAndCount(100)); // result: 1061


        final CornersAlwaysOnLightAnimation partTwoLightAnimation = new CornersAlwaysOnLightAnimation();
        partTwoLightAnimation.init(readBoardFromString(input, 100), 100);
        System.out.println(partTwoLightAnimation.animateAndCount(100)); // result: 1006
    }
}
