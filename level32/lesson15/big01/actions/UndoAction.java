package com.javarush.test.level32.lesson15.big01.actions;

import javax.swing.*;
import com.javarush.test.level32.lesson15.big01.View;
import java.awt.event.ActionEvent;

/**
 * Created by silya on 31.08.2016.
 */
public class UndoAction extends AbstractAction
{
    private View view;

    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.undo();
    }
}
