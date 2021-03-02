package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LightAnimationMain {
    public static void main(String[] args) throws IOException {
        final String input = Files.readString(Path.of("input.txt"), StandardCharsets.US_ASCII);

//        final LightBoardFactory lightBoardFactory = new TwoDimensionalArrayLightBoardFactory();
        final LightBoardFactory lightBoardFactory = new BitSetLightBoardFactory();

        final PartOneLightAnimation partOneLightAnimation = new PartOneLightAnimation(lightBoardFactory, 100, input);
        System.out.println(partOneLightAnimation.animateAndCount(100)); // result: 1061

        final PartTwoLightAnimation partTwoLightAnimation = new PartTwoLightAnimation(lightBoardFactory, 100, input);
        System.out.println(partTwoLightAnimation.animateAndCount(100)); // result: 1006
    }
}
