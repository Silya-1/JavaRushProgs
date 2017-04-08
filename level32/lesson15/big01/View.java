package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by silya on 25.08.2016.
 */
public class View extends JFrame implements ActionListener
{


    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    private Controller controller;

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        switch (command) {

            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }

    }

    public void init()
    {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }
        catch (InstantiationException e)
        {
            ExceptionHandler.log(e);
        }
        catch (IllegalAccessException e)
        {
            ExceptionHandler.log(e);
        }
        catch (UnsupportedLookAndFeelException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlPain = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", htmlPain);

        JScrollPane textPain = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", textPain);

        tabbedPane.setPreferredSize(new Dimension(800, 800));

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged()
    {
       if(isHtmlTabSelected())
       {
            controller.setPlainText(plainTextPane.getText());
       }else
       {
           plainTextPane.setText(controller.getPlainText());
       }
       resetUndo();
    }

    public void undo()
    {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo()
    {
        try {
            undoManager.redo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public boolean isHtmlTabSelected()
    {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(this,"Message", "Inform" ,JOptionPane.INFORMATION_MESSAGE);
    }

}
