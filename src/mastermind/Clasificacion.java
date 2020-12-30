package mastermind;

public class Clasificacion {
	public static volcar_a_txt(File fichero)
	{
		FileWriter writer;
		int i;

		writer = new FileWriter(fichero);
		if (fichero.createNewFile())
		{
			System.out.println("File created: " + fichero.getName());
		}
		else
		{
			System.out.println("File already exists.");
		}
		while (i < Usuario.getLista_usuarios().size())
		{
			writer.write(Usuario.getLista_usuarios().sort(new WinsSorter()).get(i).toString());
			writer.write("\n");
		}
	}
}
