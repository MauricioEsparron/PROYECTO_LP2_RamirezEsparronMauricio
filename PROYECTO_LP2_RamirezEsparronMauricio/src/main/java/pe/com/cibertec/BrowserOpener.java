package pe.com.cibertec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BrowserOpener implements CommandLineRunner {

	@Override
	public void run(String... args) {
		openBrowser("http://localhost:9091/home");
	}

	@SuppressWarnings("deprecation")
	private void openBrowser(String url) {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				// Windows
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else if (os.contains("mac")) {
				// macOS
				Runtime.getRuntime().exec("open " + url);
			} else if (os.contains("nix") || os.contains("nux")) {
				// Linux
				Runtime.getRuntime().exec("xdg-open " + url);
			} else {
				System.out.println("Sistema operativo no soportado");
			}
		} catch (IOException e) {
			System.out.println("Error al abrir el navegador: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
