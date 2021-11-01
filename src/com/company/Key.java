package com.company;
import org.bouncycastle.util.encoders.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.Random;
public class Key {

    public int pcMove;
    private final String[] moves;
    private String key;
    private String HMAC;

    public Key(String[] moves) {
        HMAC = null;
        key = null;
        this.moves = moves;
    }

    private void generateMove() {
        Random rand = new SecureRandom();
        this.pcMove = rand.nextInt(this.moves.length);
    }

    private byte[] generateKey() {
        final int KEY_LENGTH = 128;
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[KEY_LENGTH / 16];
        random.nextBytes(bytes);
        return bytes;
    }

    private void toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%04x", b);
        }
        this.key = formatter.toString();

    }

    private void generateHMAC(String data, String key) {
        try {
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            byte[] hexBytes = new Hex().encode(rawHmac);
            this.HMAC = new String(hexBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showKey() {
        System.out.println("HMAC key: " + this.key);
    }

    public void showHMAC() {
        System.out.println("HMAC: " + this.HMAC);
    }

    public void generateAll() {
        this.generateMove();
        this.toHexString(this.generateKey());

        this.generateHMAC(this.moves[this.pcMove], this.key);
    }

}
