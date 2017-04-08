package com.javarush.test.level32.lesson15.big01;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by silya on 25.08.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;

    private File currentFile;

    public Controller(View view)
    {
        this.view = view;
    }
    public void init()
    {
        createNewDocument();
    }

    public void exit()
    {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        StringReader stringReader = new StringReader(text);
        try {
           htmlEditorKit.read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        StringWriter stringWriter = new StringWriter();
        try
        {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        }
        catch (BadLocationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }


    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int number = jFileChooser.showOpenDialog(view);
        if (number == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument()
    {
        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
            view.selectHtmlTab();
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int number = jFileChooser.showSaveDialog(view);
        if(number == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }


    }
}
