package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Силя on 05.08.2016.
 */
public class MoikrugStrategy implements Strategy
{

    private final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            Document document;
            int pageCounter = 0;
            while(true)
            {
                document = getDocument(searchString, pageCounter++);
                if(document == null) break;
                Elements elements = document.getElementsByClass("job");
                if(elements.size() == 0) break;
                for(Element element : elements)
                {
                    String title = "";
                    String salary = "";
                    String city = "";
                    String company = "";
                    String siteName = "https://moikrug.ru";
                    String url = "";
                    Vacancy vacancy = new Vacancy();
                    title = element.getElementsByClass("title").select("a").text();
                    url = siteName + element.getElementsByClass("title").select("a").attr("href");
                    salary = element.getElementsByClass("count").text();
                    city = element.getElementsByClass("location").text();
                    company = element.getElementsByClass("company_name").select("a[href]").text();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(company);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
            }
        }
        catch (Exception e)
        {
        }
        return vacancies;
    }


    protected Document getDocument(String searchString, int page) throws IOException
    {
        String url = String.format(URL_FORMAT, page, searchString);
        Document document = null;
        try {
            document = Jsoup.connect(URL_FORMAT)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .referrer("http://yandex.ru/")
                    .get(); //Получаем документ по url.
        }
        catch (IOException e) {
        }
        return document;
    }
}
