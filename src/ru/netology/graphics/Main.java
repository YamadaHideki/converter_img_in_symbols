package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера


        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        converter.setMaxWidth(256);
        converter.setMaxHeight(144);
        // Или то же, но с сохранением в файл:

       /* PrintWriter fileWriter = new PrintWriter(new File("converted-image.txt"));
        *//*converter.setMaxWidth(200);
        converter.setMaxHeight(300);*//*
        fileWriter.write(converter.convert("https://i.ibb.co/6DYM05G/edu0.jpg"));
        //fileWriter.write(converter.convert("https://wallbox.ru/resize/800x480/wallpapers/main2/201733/15031820025998bcb258de54.79104336.jpg"));
        fileWriter.close();*/

    }
}
