import java.awt.datatransfer.*;
import java.awt.*;
import java.io.*;

public final class ModifPressPap implements ClipboardOwner {

  public void lostOwnership(Clipboard cb, Transferable t) {
    System.out.println("Contenu modifié");
    }

  public void setClipboardContents(String s) {
    StringSelection ss = new StringSelection(s);
    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
    cb.setContents(ss, this);
    }
  }