package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Силя on 04.08.2016.
 */
public class HtmlView implements View
{
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void updateFile(String fileContent) {
        try {
            BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath));
            fWriter.write(fileContent);
            fWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document doc = getDocument();
        try
        {
            Element element = doc.getElementsByClass("template").first();
            Element cloneElement = element.clone();
            cloneElement.removeClass("template").removeAttr("style");
            doc.getElementsByAttributeValue("class","vacancy").remove();
            for (Vacancy vacancy : vacancies)
            {
                Element vac = cloneElement.clone();
                vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = vac.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(vac.outerHtml());
            }
        }catch (NullPointerException a){System.out.println("Some exception occurred");}
        return doc.html();
    }

    protected  Document getDocument() throws IOException
    {
        Document doc = Jsoup.parse(new File(filePath),"UTF-8");
        return doc;
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Odessa");
    }
}
