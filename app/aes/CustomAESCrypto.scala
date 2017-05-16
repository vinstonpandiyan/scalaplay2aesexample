package aes

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

import org.apache.commons.codec.binary.Base64

/**
  * Java Cryptographic utilities.
  * Used to encrypt or decrypt the text or content given.
  * using <b>encrypt</b> and <b>decrypt</b> methods.
  *
  * Created by vpandiyan001 on 5/12/2017.
  */
object CustomAESCrypto {

  /**
    * Default AES Encryption method.
    */
  final val AES_ENCRYPTION_METHOD = "AES/CTR/NoPadding"

  /**
    * Generates the SecretKeySpec, given the private key and the algorithm.
    */
  private def secretKeyWithSha256(privateKey: String, algorithm: String) = {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    messageDigest.update(privateKey.getBytes("utf-8"))
    val maxAllowedKeyLength = Cipher.getMaxAllowedKeyLength(algorithm) / 8
    val raw = messageDigest.digest().slice(0, maxAllowedKeyLength)
    new SecretKeySpec(raw, algorithm)
  }

  /**
    * Encrypt a String with the AES encryption standard and the supplied private key.
    *
    * @param value The String to encrypt.
    * @param privateKey The key used to encrypt.
    * @return A Base64 encrypted string.
    */
  def encrypt(value: String, privateKey: String): String = {
    val keySpec = secretKeyWithSha256(privateKey, "AES")
    val cipher = Cipher.getInstance(AES_ENCRYPTION_METHOD)
    cipher.init(Cipher.ENCRYPT_MODE, keySpec)
    val encryptedValue = cipher.doFinal(value.getBytes("utf-8"))
    Base64.encodeBase64String(cipher.getIV() ++ encryptedValue)
  }

  /**
    * Decrypt a String with the AES encryption standard.
    *
    * @param value An hexadecimal encrypted string.
    * @param privateKey The key used to encrypt.
    * @return The decrypted String.
    */
  def decrypt(value: String, privateKey: String): String = {
    decryptAES(value, privateKey)
  }

  /**
    * decryption algorithm (IV must present).
    *
    * @param value
    * @param privateKey
    * @return
    */
  private def decryptAES(value: String, privateKey: String): String = {
    val data = Base64.decodeBase64(value)
    val skeySpec = secretKeyWithSha256(privateKey, "AES")
    val cipher = Cipher.getInstance(AES_ENCRYPTION_METHOD)
    val blockSize = cipher.getBlockSize
    val iv = data.slice(0, blockSize)
    val payload = data.slice(blockSize, data.size)
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv))
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv))
    new String(cipher.doFinal(payload), "utf-8")
  }

}
