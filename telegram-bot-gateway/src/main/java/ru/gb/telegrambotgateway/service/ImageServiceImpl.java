package ru.gb.telegrambotgateway.service;

import ij.ImagePlus;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.service.inter.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final int MAX_CHARS_IN_LINE = 25;
    private final ResourceLoader resourceLoader;

    @Override
    public File createImage(String question) throws IOException {
        List<String> questionList = parseQuestion(question);

        ImagePlus image = new ImagePlus();
//        image.setImage(ImageIO.read(new ClassPathResource("templates/template.png").getInputStream()));
        image.setImage(ImageIO.read(resourceLoader.getResource("classpath:templates/template.png").getInputStream()));

        Font font = new Font(/*"Bahnschrift SemiBold"*/"Times New Roman", Font.BOLD, 64);
        int sizeBetweenRows = font.getSize();
        Graphics g = image.getImage().getGraphics();
        FontMetrics ruler = g.getFontMetrics(font);
        GlyphVector vector = font.createGlyphVector(ruler.getFontRenderContext(), questionList.stream().max(Comparator.comparing(String::length)).get());
        Shape outline = vector.getOutline(0, 0);
        double expectedWidth = outline.getBounds().getWidth();
        double expectedHeight = (outline.getBounds().getHeight() + sizeBetweenRows) * questionList.size();

        double widthBasedFontSize = (font.getSize2D() * (image.getWidth() - 400)) / expectedWidth;
        double heightBasedFontSize = (font.getSize2D() * (image.getHeight() - 200)) / expectedHeight;
        double newFontSize = Math.min(widthBasedFontSize, heightBasedFontSize);
        font = font.deriveFont(font.getStyle(), (float) newFontSize);
        sizeBetweenRows = font.getSize();
        int textHeight = (font.getSize() + sizeBetweenRows) * questionList.size() - sizeBetweenRows;

        for (int i = 0; i < questionList.size(); i++) {
            AttributedString attributedText = new AttributedString(questionList.get(i));
            attributedText.addAttribute(TextAttribute.FONT, font);
            attributedText.addAttribute(TextAttribute.FOREGROUND, new Color(0, 166, 147));
            g.drawString(attributedText.getIterator(), (image.getWidth() - g.getFontMetrics(font).stringWidth(questionList.get(i))) / 2, (image.getHeight() - textHeight) / 2 + (sizeBetweenRows + font.getSize()) * i + sizeBetweenRows/2);
        }

        BufferedImage bufferedImage = image.getBufferedImage();
        File output = new File("/tmp/output.png");
        ImageIO.write(bufferedImage, "png", output);
        return output;
    }

    private List<String> parseQuestion(String question) {
        String questionText = question.replace("\u2063", "");
        String[] questionTextArray = questionText.split(" ");
        List<String> questionTextList = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < questionTextArray.length; i++) {
            line.append(questionTextArray[i]).append(" ");
            if (line.length() > MAX_CHARS_IN_LINE) {
                questionTextList.add(line.toString());
                line = new StringBuilder();
            } else if (i == questionTextArray.length - 1) {
                questionTextList.add(line.toString());
            }
        }
        return questionTextList;
    }
}
