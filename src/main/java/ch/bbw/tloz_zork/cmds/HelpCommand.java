package ch.bbw.tloz_zork.cmds;

/**
 * Prints a list of all available commands
 * @author Yao Kaiser
 */
public class HelpCommand {
    /**
     * Prints a list of all available commands
     */
    public void help(){
        System.out.println("『 Move commands 』\nback • move <direction>\n(n)orth • (s)outh • (e)ast • (w)est");
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
        System.out.println("『 Interaction commands 』\n(m)ap • (s)core • (f)ight");
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
        System.out.println("『 Item commands 』\n(i)nventory • (g)rab • drop <item> • eat <item> • use <item>");
    }
}

