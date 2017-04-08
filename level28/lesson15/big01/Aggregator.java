package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;


/**
 * Created by Силя on 04.08.2016.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        HtmlView view = new HtmlView();
        Provider providerHH = new Provider(new HHStrategy());
        Provider providerMK = new Provider(new MoikrugStrategy());
        Provider providerLinkedIn = new Provider(new HHStrategy());
        Model model = new Model(view, new Provider[] {providerHH, providerLinkedIn, providerLinkedIn});
        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod();
    }
}
