package ru.gb.telegrambotgateway.service;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    File createImage(String question) throws IOException;

}
