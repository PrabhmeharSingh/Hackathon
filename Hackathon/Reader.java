import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Reader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner fin = null;
		PrintWriter fout = null;
		String line = null;
		String[] attributes = new String[5];
		int linectr = 0;
		try {
			fin = new Scanner(new FileInputStream("File.txt"));
			fout = new PrintWriter(new FileOutputStream("directory.html"));
			fout.println("<html>\n"
					+ "<head>\n <title>\n Availalble medicines \n </title> \n <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" charset=\"UTF-8\">\n <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\"/> <link href=\"nav.css\" type=\"text/css\" rel=\"stylesheet\"/> <link rel=\"icon\" href=\"logo.jpg\"> </head> <body> <header class=\"header\">   <nav class=\"site-nav\" style=\"margin:0px; \">  <ul class=\"group\"  style=\"background-color: #fff;\">   <li ><a class=\"navbar-brand\" href=\"index.html\" style=\"color: orange;background-color: #fff;\"><img src=\"logo.jpg\" width=50px alt=\"Logo image\">Medica</a></li>    <li style=\"float: right;\"><a class=\"nav-link sign-in\" style=\"color: orange;background-color: #fff; margin: 20px 0px;\" href=\"p5.html\"><i class=\"fa-solid fa-arrow-right-to-bracket\"></i><Strong> Sign in</strong></a></li>            <li style=\"float: right;\"><a href=\"p6.html\" style=\"color:orange; background-color: #fff; margin: 20px 0px;\">Sign up</a></li>        <li style=\"float: right\"><a href=\"\" style=\"color:orange ;background-color: #fff; margin: 20px 0px;\">Contact us</a> </li>      </ul>  </nav></header><h1 style=\"text-align:center; background-color: orange;\"> Available Medicines</h1><div style=\"padding:5%;\">");
			while (fin.hasNextLine()) {
				if (linectr % 4 == 0)
					fout.println("<div class=\"row\">");
				line = fin.nextLine();
				for (int i = 0; i < 5; i++) {
					if (i < 4)
						attributes[i] = line.substring(0, line.indexOf(';'));
					else
						attributes[i] = line;
					line = line.substring(line.indexOf(';') + 1);
				}
				fout.println("<span class=\"col-sm-3 item\">");
				fout.println("<img width=100% src=\"" + attributes[4] + "\">");
				fout.println("<h2 class=\"text-break text-center\">" + attributes[1] + "</h2>");
				fout.println("<h6 class=\"text-break text-center\">" + attributes[0] + "</h6>");
				// fout.println("<h4 class=\"text-break text-center\">"+attributes[2]+"</h4>");
				fout.println("</span>");
				if (linectr % 4 == 3)
					fout.println("</div>");
				linectr++;
			}
			fout.println("</div></body></html>");
			fin.close();
			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error");
		}

	}

}
