package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Силя on 26.06.2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader (ImageTypes s )
    {
        if(s == ImageTypes.JPG)
            return  new JpgReader();
             else if(s == ImageTypes.BMP)
                return new BmpReader();
                else if(s == ImageTypes.PNG)
                    return new PngReader();
                    else throw new IllegalArgumentException();

    }
}
