package zxf.springboot.cache.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

@RestController
@RequestMapping("/network")
public class NetworkController {
    @GetMapping("dns")
    public ResponseEntity<String> dns(@RequestParam String host) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            return ResponseEntity.ok(String.format("DNS resolution for %s: %s", host, Arrays.asList(addresses)));
        } catch (Exception ex) {
            return ResponseEntity.status(404).body(String.format("DNS resolution error for %s: %s", host, ex.getMessage()));
        }
    }


    @GetMapping("telnet")
    public ResponseEntity<String> telnet(@RequestParam String host, @RequestParam int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 30 * 1000);
            return ResponseEntity.ok("Successfully connected to " + host + ":" + port);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return ResponseEntity.status(500).body("Failed to connect to " + host + ":" + port + ". Reason: " + ex.getMessage());
        }
    }

    @GetMapping("ssl")
    public ResponseEntity<String> ssl(@RequestParam String host, @RequestParam int port) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port)) {
                socket.startHandshake();
                return ResponseEntity.ok("Successfully established SSL connection to " + host + ":" + port);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return ResponseEntity.status(500).body("Failed to establish SSL connection to " + host + ":" + port + ". Reason: " + ex.getMessage());
        }
    }
}
