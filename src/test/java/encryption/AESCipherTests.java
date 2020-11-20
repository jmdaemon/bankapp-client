package test.crypt.cipher.aes;

import app.crypt.utils.*;
import app.crypt.cipher.aes.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AESCipherTests {
  private AESCipher cipher = new AESCipher();

  @Test
  public void genKey_AES_ReturnAESKey() throws NoSuchAlgorithmException {
    assertNotNull(cipher.genKey(), "AES Key should be initialized");
  }

  @Test
  public void genKeyPswd_AES_ReturnAESKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
    assertNotNull(cipher.genPswdKey("This is the user password", cipher.genSalt()), "AES Key should be initialized");
  }
  
  @Test
  public void encrypt_Plaintext_ReturnAESCiphertext() throws Exception {
    byte[] res = cipher.encrypt("This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_IVPlaintext_ReturnAESCiphertext() throws Exception {
    byte[] res = cipher.encryptWithIV( "This is the plaintext".getBytes(cipher.UTF_8), cipher.genIV(), cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  @Test
  public void encrypt_SaltPlaintext_ReturnAESCiphertext() throws Exception {
    byte[] salt = cipher.genSalt();
    byte[] res = cipher.encryptWithSalt( cipher.genPswdHash("This is the plaintext", salt), cipher.genIV(), salt, cipher.genKey() );
    assertNotNull(res, "Ciphertext should be initialized");
    assertNotEquals("This is the plaintext", new String (res, cipher.UTF_8), "Ciphertext should not equal plaintext");
  }

  //public void decrypt_Salt_(){
  //}

  //public void encrypt_IV_(){
  //}

  //public void decryptIV_(){
  //}

  //public void encrypt_IVSalt_(){
  //}

  //public void decryptIVSALT_(){
  //}

}
