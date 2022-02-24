/*
Congratulations, all you have to do now, is decrypt this file in order to get my message.
You can (technically) have a brute force solution as well, but it's not elegant, and wouldn't work well with non-dictionary words. This requires the round length, regardless.

The other way I thought of doing it was to give you a file with previous rounds. This is essentially the "private key" that decrypts my message. 

This encryption algorithm is probably flawed in a lot of other ways as well. 

If you want the solution, just decode this Base64 String.
Y2xhc3MgRGVjcnlwdGVyIHsKICBwcml2YXRlIFN0cmluZyB0ZXh0OwogIHByaXZhdGUgaW50IHJvdW5kczsKICBwdWJsaWMgRGVjcnlwdGVyKFN0cmluZyB0ZXh0KSB7CiAgICB0aGlzLnRleHQgPSB0ZXh0OwogICAgdGhpcy5yb3VuZHMgPSAxMDAwOwogIH0KCiAgcHVibGljIFN0cmluZyBkZWNyeXB0KCkgdGhyb3dzIEV4Y2VwdGlvbiB7CiAgICBpbnRbXVtdIGFycmF5ID0gbmV3IGludFsxMDAwXVt0ZXh0Lmxlbmd0aCgpXTsKICAgIFNjYW5uZXIgc2Nhbm5lciA9IG5ldyBTY2FubmVyKG5ldyBGaWxlKCJkZXRhaWxzLnR4dCIpKTsKICAgIGludCBrID0gMDsKICAgIHdoaWxlKHNjYW5uZXIuaGFzTmV4dExpbmUoKSkgewogICAgICBTdHJpbmcgbGluZSA9IHNjYW5uZXIubmV4dExpbmUoKTsKICAgICAgU3RyaW5nW10gc3BsaXQgPSBsaW5lLnNwbGl0KCIgIik7CiAgICAgIGZvcihpbnQgaiA9IDA7IGogPCBzcGxpdC5sZW5ndGg7IGorKykgewogICAgICAgIGFycmF5W2tdW2pdID0gSW50ZWdlci5wYXJzZUludChzcGxpdFtqXSk7CiAgICAgIH0KICAgICAgaysrOwogICAgfQogICAgU3RyaW5nIGRlY3J5cHRlZCA9IHRoaXMudGV4dDsKICAgIGludCBsZW4gPSB0aGlzLnRleHQubGVuZ3RoKCk7CiAgICBmb3IoaW50IGkgPSByb3VuZHM7IGkgPj0gMTsgaS0tKSB7CiAgICAgIFN0cmluZyBidWlsZGVyID0gIiI7CiAgICAgIGZvcihpbnQgaiA9IGxlbiA7IGogPj0gMTsgai0tKSB7CiAgICAgICAgaW50IG9mZnNldCA9IGFycmF5W2kgLSAxXVtqIC0gMV0gLyAoaiAqIGkpOwogICAgICAgIGJ1aWxkZXIgPSAoY2hhcikob2Zmc2V0KSArIGJ1aWxkZXI7CiAgICAgIH0KICAgICAgZGVjcnlwdGVkID0gYnVpbGRlcjsKICAgIH0KICAgIHJldHVybiBkZWNyeXB0ZWQ7CiAgfQ==
*/

import java.util.*;
import java.io.*;

class Main {
  private final static String ENCRYPTED_MESSAGE = "egbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegbedcaegb";
  public static void main(String[] args) throws Exception {
    String toEncrypt = "This is an example message";
    Encrypter encrypter = new Encrypter(toEncrypt);
    System.out.println(encrypter.encrypt());
  }
}

class Encrypter {

  private String text;
  private int rounds;
  public Encrypter(String text) {
    this.text = text;
    this.rounds = 1000;
  }

  public String encrypt() throws IOException {
    String encrypted = this.text;
    int len = this.text.length();
    StringBuilder roundDetails = new StringBuilder();
    File file = new File("details.txt");
    if(!file.exists()) {
      file.createNewFile();
    }
    PrintWriter writer = new PrintWriter(file);
    for(int i = 1; i <= rounds; i++) {
      StringBuilder sb = new StringBuilder();
      for(int j = 1; j <= len; j++) {
        char c = encrypted.charAt(j - 1);
        int offset = (int)(j * i * (int)c) % ('Z' - 'a');
        c = (char)('a' + offset);
        sb.append(c);
        roundDetails.append((j * i * encrypted.charAt(j - 1)) + " ");
      }
      String build = roundDetails.toString().trim();
      if(i != rounds) {
        build += "\n";
      }
      writer.write(build);
      roundDetails = new StringBuilder();
      encrypted = sb.toString();
    }
    writer.close();
    return encrypted;
  }
}
