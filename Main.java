import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In case you want the solution, here it is:
 * Y2xhc3MgRGVjcnlwdGVyIHsNCiAgICBwcml2YXRlIFN0cmluZyB0ZXh0Ow0KICAgIHByaXZhdGUgaW50IHJvdW5kczsNCiAgICBwcml2YXRlIExpc3Q8SW50ZWdlcj4ga2V5TGlzdDsNCiAgICBwdWJsaWMgRGVjcnlwdGVyKFN0cmluZyB0ZXh0LCBMaXN0PEludGVnZXI+IGtleUxpc3QpIHsNCiAgICAgICAgdGhpcy5rZXlMaXN0ID0ga2V5TGlzdDsNCiAgICAgICAgdGhpcy5yb3VuZHMgPSBrZXlMaXN0LnNpemUoKTsNCiAgICAgICAgdGhpcy50ZXh0ID0gdGV4dDsNCiAgICB9DQoNCiAgICBwdWJsaWMgU3RyaW5nIGRlY3J5cHQoKSB7DQogICAgICAgIFN0cmluZyBkZWNyeXB0ZWQgPSB0ZXh0Ow0KICAgICAgICBmb3IoaW50IGkgPSAwOyBpIDwgcm91bmRzOyBpKyspIHsNCiAgICAgICAgICAgIGludCBrZXkgPSBrZXlMaXN0LmdldChyb3VuZHMgLSAoaSArIDEpKTsNCiAgICAgICAgICAgIGRlY3J5cHRlZCA9IHhvcihkZWNyeXB0ZWQsIGtleSk7DQogICAgICAgICAgICBkZWNyeXB0ZWQgPSBpc2hpZnQoZGVjcnlwdGVkLCBrZXkpOw0KICAgICAgICB9DQogICAgICAgIHJldHVybiBkZWNyeXB0ZWQ7DQogICAgfQ0KDQogICAgcHVibGljIFN0cmluZyB4b3IoU3RyaW5nIHRleHQsIGludCBrZXkpIHsNCiAgICAgICAgU3RyaW5nIGJ1aWxkZXIgPSAiIjsNCiAgICAgICAgZm9yKGludCBpID0gdGV4dC5sZW5ndGgoKSAtIDE7IGkgPj0gMDsgaS0tKSB7DQogICAgICAgICAgICBidWlsZGVyID0gU3RyaW5nLnZhbHVlT2YoKGNoYXIpKHRleHQuY2hhckF0KGkpIF4gKChpID09IHRleHQubGVuZ3RoKCkgLSAxID8gJ2wnIDogYnVpbGRlci5jaGFyQXQoMCkpKSkpICsgYnVpbGRlcjsNCiAgICAgICAgfQ0KICAgICAgICByZXR1cm4gYnVpbGRlcjsNCiAgICB9DQoNCiAgICBwdWJsaWMgU3RyaW5nIGlzaGlmdChTdHJpbmcgdGV4dCwgaW50IGtleSkgew0KICAgICAgICByZXR1cm4gdGV4dC5zdWJzdHJpbmcoMiwgdGV4dC5sZW5ndGgoKSkgKyB0ZXh0LnN1YnN0cmluZygwLCAxKTsNCiAgICB9DQp9
 * Just Base64 Decode it. To be clear, this is the code, not the "secrert message".
 */

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> list = generate(100);
        Encrypter encrypter = new Encrypter("This is an example", list);
        System.out.println(encrypter.encrypt());
    }

    public static List<Integer> generate(int amount) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= amount; i++) {
            int j = (int)(amount * i * Math.PI);
            list.add(j);
        }
        return list;
    }
}

class Encrypter {

    private String text;
    private int rounds;
    private List<Integer> keyList;
    public Encrypter(String text, List<Integer> keyList) {
        this.keyList = keyList;
        this.rounds = keyList.size();
        this.text = text;
    }

    public String encrypt() {
        String encrypted = text;
        for(int i = 0; i < rounds; i++) {
            encrypted = shift(encrypted, keyList.get(i));
            encrypted = xor(encrypted, keyList.get(i));
        }
        return encrypted;
    }

    public void printToFile() throws IOException {
        File file = new File("encrypted.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write(encrypt());
        writer.close();
    }

    public String shift(String text, int key) {
        return text.substring(text.length() - 1) + (char)(key % 73) + text.substring(0, text.length() - 1);
    }

    public String xor(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < text.length(); i++) {
            sb.append(String.valueOf((char)(text.charAt(i) ^ ((i == text.length() - 1 ? 'l' : text.charAt(i + 1))))));
        }
        return sb.toString();
    }
}
