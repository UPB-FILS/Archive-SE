/*license GPL
 * Se ruleaza cu java Bonjour [lista_parametri].
*/
/** Description of Bonjour 
 *
 * @author Culic Ioana
 * @author Barbatei Alexandra
 * @version 1 Build 1-OpenJDK-1.6.0_27 Mar 3, 2013.
 */
public class Bonjour 
{
	public static void main(String[] args)
	{
		//Se parcurge lista de parametri primiti.
		for(int i=0; i<args.length; i++)
		{
			PrintThread thread = new PrintThread(args[i]);
			thread.run();
		}
	}
}
