package presentation;

import java.io.FileNotFoundException;
import javax.swing.JFrame;
import com.lowagie.text.DocumentException;


public class App extends JFrame{
	
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		
		presentation.View view = new presentation.View();
		view.setVisible(true);
		new businessLayer.ClientController(view);
		new businessLayer.ProductController(view);
		new businessLayer.OrderController(view);
		
	}

		
}
