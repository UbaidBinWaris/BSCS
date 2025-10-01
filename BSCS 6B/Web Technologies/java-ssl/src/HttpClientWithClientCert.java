import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.SecureRandom;

public class HttpClientWithClientCert {
    public static void main(String[] args) {
        String p12Path = System.getProperty("keystore", "badssl.com-client.p12");
        String p12Password = System.getProperty("keystorepass", "badssl.com");
        String proxyHost = System.getProperty("proxyHost", "");
        String proxyPortStr = System.getProperty("proxyPort", "");
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (InputStream fis = Files.newInputStream(Path.of(p12Path))) {
                ks.load(fis, p12Password.toCharArray());
            }

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, p12Password.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init((KeyStore) null);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

            var builder = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .version(HttpClient.Version.HTTP_1_1);

            if (!proxyHost.isEmpty()) {
                int proxyPort = Integer.parseInt(proxyPortStr.isEmpty() ? "0" : proxyPortStr);
                if (proxyPort > 0) {
                    builder.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)));
                    System.out.println("Using proxy " + proxyHost + ":" + proxyPort);
                } else {
                    System.out.println("proxyPort is empty or zero. Skipping proxy.");
                }
            } else {
                System.out.println("No proxy configured. Connecting directly.");
            }

            HttpClient client = builder.build();

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("https://client.badssl.com/"))
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + res.statusCode());
            System.out.println(res.body().substring(0, Math.min(800, res.body().length())));
        } catch (Exception e) {
            System.err.println("Error. See stack trace below.");
            e.printStackTrace();
        }
    }
}
