import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class BotoesBarra extends AbstractAction {

    public BotoesBarra(int indiceBotao, ImageIcon imgIcone, String strDescricao) {

        super(strDescricao, imgIcone);

        this.putValue("id", indiceBotao);
        this.putValue(SHORT_DESCRIPTION, strDescricao);

    }

    public void actionPerformed(ActionEvent evt) {

       
    }

}