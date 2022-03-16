package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public class JwtUtil {
    public static final Base64 base64 = new Base64();
    public static final HmacUtils hm256 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Environment.SECRET.getBytes());

    public static String generate(String username) {
        long exp = System.currentTimeMillis() + 1000*60*60*24*7;
        String header = "{\"alg\": \"HS256\", \"typ\": \"JWT\"}";
        String payload = "{\"sub\": \"" + username + "\", \"exp\": " + exp + "}";

        String encoded = new String(base64.encode(header.getBytes())) +
                "." + new String(base64.encode(payload.getBytes()));
        String hashed = hm256.hmacHex(encoded);

        return encoded + "." + hashed;
    }
}
