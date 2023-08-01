package site.achun.file.configuration.achun;

import lombok.Data;

@Data
public class AchunConfig {

    private static final String DEFAULT_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwtR4cn6aAQ8v9pVuTFi/axUEVpOm2WosZ1MdX7ywXIO1XakUxzwE2uyYBRAmN7uhAIW1n/iFMdKoycS09OM5SxZgFIykOu+P3ZILE+K7Wr1uVVfZHMG97CTAxMKJNISk4JldbEfIvbVCwoY5bQSBY0o5toTI7sISw6tQNsP3PEwIDAQAB";
    private static String DEFAULT_RSA_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALC1HhyfpoBDy/2lW5MWL9rFQRWk6bZaixnUx1fvLBcg7VdqRTHPATa7JgFECY3u6EAhbWf+IUx0qjJxLT04zlLFmAUjKQ674/dkgsT4rtavW5VV9kcwb3sJMDEwok0hKTgmV1sR8i9tULChjltBIFjSjm2hMjuwhLDq1A2w/c8TAgMBAAECgYATQtKy7QYBkiJAlg8aw77YmkqjWNPuwATMfoByCcxlAv5L2nYLXVR1CkY0uh1Ofr2LC/m4bZ9kjYzlradwNmpqtbu1ll5ZQVhH6Pl1Coh8XZ+mkiVPZ4waAczHwuSiJc3nEhWLG0L2aBY1nTZ+WWhFi0Ks/RUzT/bzEXxeBkgayQJBAOfjuacbVvzOrWQLRyvdbuSi8At0HJZqgyuaBKlV6omnvGAHUAuHAWwB4l3Wj1D+qatsdYPVXZ9ohP81hO7k2lkCQQDDFJg+gZduwPS8qxiGk7K4/9irnMqVBRwQclXApBgbeRD7RnxZEt8ywig3tXJ1ByYYDjJAJ0uT/pZN3jvUR69LAkBd/rPxZaakAQ7Nn7iwsihSKy3MJruzyOb0lJDRJw9TzxcPgcdTdN0Br93XFbKwLRoPb35O+vzhMde/Ly18PG25AkANstKGAR+bHV2SKgmM2a5BI2YZlub60+MGnKC86EjxFO1GV8q/jn2x2MRuNQWWhiv8oHKS4q+B8uynByX4YBhFAkBd76tcgadmZWPa+7Er0c9xdD8VCRMDoFreSSLj+WxOBLfF3pF2iPi+jGw8hBncWZX4eRw1FFuz6rOGJggCIrKE";
    private final static String FILE_AUTH_KEY = "THISISFILEAUTHUT";

    public static AchunConfig config = new AchunConfig();

    private String fileAuthKey = FILE_AUTH_KEY;
    private String loginRsaPublicKey = DEFAULT_RSA_PUBLIC_KEY;
    private String loginRsaPrivateKey = DEFAULT_RSA_PRIVATE_KEY;

}
