import java.util.ArrayList;


public class Bonjour extends Thread
{
ArrayList<String> args;

public Bonjour(ArrayList<String> args) {
	super();
	this.args = args;
}

public void run()
{
	if(this.args.size()<=3)
	{
		for(int i=0;i<this.args.size();i++)
			System.out.println("Bonjour a toi, "+ this.args.get(i));
	}
	else
	{
		System.out.println("Bonjour a tous!");
	}

}

}